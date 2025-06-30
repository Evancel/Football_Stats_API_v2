package com.hyperskill.statistics;

import com.hyperskill.dto.*;
import com.hyperskill.entity.Goal;
import com.hyperskill.entity.Match;
import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;
import com.hyperskill.exception.PlayerNotFoundException;
import com.hyperskill.exception.TeamNotFoundException;
import com.hyperskill.mapper.PlayerMapper;
import com.hyperskill.repository.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class PlayerStatisticsService {
    private final PlayerRepository playerRepository;

    public PlayerStatisticsService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Page<PlayerResponseDTO> getTopPlayersByScoredGoals(int page, int size) {
        Pageable sorted = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "goals"));
        return playerRepository.findAll(sorted).map(PlayerMapper::toDTO);
    }

    public Page<PlayerResponseDTO> getTopPlayersByMatches(int page, int size) {
        Pageable sorted = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "playedMatches"));
        return playerRepository.findAll(sorted).map(PlayerMapper::toDTO);
    }

    public PlayerGoalsResponse getScoredGoalsPerYear(Long id, Integer year) {
        if (year == null) {
            year = LocalDate.now().getYear();
        }

        int amountGoals = 0;

        Player player = playerRepository
                .findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: " + id + " not found"));

        Team currTeam = player.getTeam();
        if (currTeam == null) {
            throw new TeamNotFoundException("Team of player with id: " + id + " not found");
        }

        //counting goals scored in HomeMatches
        for (Match match : getTotalMatches(currTeam)) {
            if (match.getMatchDate().getYear() == year) {
                for (Goal goal : match.getGoals()) {
                    if (goal.getPlayer().equals(player)) {
                        amountGoals++;
                    }
                }
            }
        }
        return new PlayerGoalsResponse(id, year, amountGoals);
    }

    public PlayerMatchesResponce getMatchesPerYear(Long id, Integer year) {
        if (year == null) {
            year = LocalDate.now().getYear();
        }

        int amountPlayedMatches = 0;

        Player player = playerRepository
                .findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: " + id + " not found"));

        Team currTeam = player.getTeam();
        if (currTeam == null) {
            throw new TeamNotFoundException("Team of player with id: " + id + " not found");
        }

        for (Match match : getTotalMatches(currTeam)) {
            if (match.getMatchDate().getYear() == year) {
                amountPlayedMatches++;
            }
        }
        return new PlayerMatchesResponce(id, year, amountPlayedMatches);
    }


    public PlayerAvgGoalsResponse getAverageScoredGoals(Long id) {
        Player player = playerRepository
                .findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: " + id + " not found"));
        int matches = player.getPlayedMatches();
        double avgGoals = matches == 0 ? 0.0 : (double) player.getGoals().size() / matches;
        return new PlayerAvgGoalsResponse(id, 0, avgGoals);
    }

    public PlayerAvgGoalsResponse getAverageScoredGoalsPerYear(Long id, Integer year) {
        if (year == null) {
            year = LocalDate.now().getYear();
        }

        int matchesPerYear = getMatchesPerYear(id, year).matches();
        double avgGoalsPerYear = matchesPerYear == 0 ? 0.0
                : (double) getScoredGoalsPerYear(id, year).goals() / matchesPerYear;
        return new PlayerAvgGoalsResponse(id, year, avgGoalsPerYear);
    }

    private Set<Match> getTotalMatches(Team team) {
        Set<Match> matches = new HashSet<>();
        matches.addAll(team.getHomeMatches());
        matches.addAll(team.getAwayMatches());
        return matches;
    }
}