package com.hyperskill.service;

import com.hyperskill.dto.*;
import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;
import com.hyperskill.exception.PlayerNotFoundException;
import com.hyperskill.exception.TeamNotFoundException;
import com.hyperskill.mapper.PlayerMapper;
import com.hyperskill.repository.PlayerRepository;
import com.hyperskill.repository.TeamRepository;
import com.hyperskill.statistics.PlayerStatisticsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerStatisticsService playerStatisticsService;

    public PlayerService(PlayerRepository playerRepository,
                         TeamRepository teamRepository,
                         PlayerStatisticsService playerStatisticsService) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.playerStatisticsService = playerStatisticsService;
    }

    @Transactional
    public Player save(PlayerRequestDTO request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Team with id: " + request.getTeamId() + " not found"));
        Player savedPlayed = PlayerMapper.toEntity(request, team);

        return playerRepository.save(savedPlayed);
    }

    @Transactional
    public PlayerResponseDTO updatePlayer(Long playerId, PlayerRequestDTO request) {
        //if the player with such id doesn't exist
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: " + playerId + " not found"));

        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Team with id: " + request.getTeamId() + " not found"));

        // Update player fields
        player.setFirstName(request.getFirstName());
        player.setLastName(request.getLastName());
        player.setTeam(team);
        player = playerRepository.save(player);

        return PlayerMapper.toDTO(player);
    }

    public PlayerResponseDTO findById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: " + id + " not found"));
        return PlayerMapper.toDTO(player);
    }

    public PlayerResponseDTO searchDTOByName(String firstName, String lastName) {
        Player player = playerRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));

        return PlayerMapper.toDTO(player);
    }

    public Page<PlayerResponseDTO> findAll(Pageable pageable) {
        Page<Player> players = playerRepository.findAll(pageable);
        return players.map(PlayerMapper::toDTO);
    }

    public void deleteById(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new PlayerNotFoundException("Player with id: " + id + " not found");
        }
        playerRepository.deleteById(id);
    }

    private Team findOrCreateTeam(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findByName(teamName);
        Team team = new Team();
        team.setName(teamName);
        return optionalTeam.orElseGet(() ->
                teamRepository.save(team));
    }

    //statistics
    public Page<PlayerResponseDTO> getTopPlayersByScoredGoals(int page, int size) {
        return playerStatisticsService.getTopPlayersByScoredGoals(page, size);
    }

    public Page<PlayerResponseDTO> getTopPlayersByMatches(int page, int size) {
        return playerStatisticsService.getTopPlayersByMatches(page, size);
    }

    public PlayerGoalsResponse getScoredGoalsPerYear(Long id, Integer year) {
        return playerStatisticsService.getScoredGoalsPerYear(id, year);
    }

    public PlayerMatchesResponce getMatchesPerYear(Long id, Integer year) {
        return playerStatisticsService.getMatchesPerYear(id, year);
    }

    public PlayerAvgGoalsResponse getAverageScoredGoals(Long id) {
        return playerStatisticsService.getAverageScoredGoals(id);
    }

    public PlayerAvgGoalsResponse getAverageScoredGoalsPerYear(Long id, Integer year) {
        return playerStatisticsService.getAverageScoredGoalsPerYear(id, year);
    }
}
