package com.hyperskill.service;

import com.hyperskill.dto.MatchDTO;
import com.hyperskill.dto.PlayerDTO;
import com.hyperskill.entity.Match;
import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;
import com.hyperskill.mapper.MatchMapper;
import com.hyperskill.mapper.PlayerMapper;
import com.hyperskill.repository.MatchRepository;
import com.hyperskill.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository){
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Match save(MatchDTO matchDTO) {

        Team homeTeam = findOrCreateTeam(matchDTO.getHomeTeamName());
        Team awayTeam = findOrCreateTeam(matchDTO.getAwayTeamName());

        Match matchToSave = new Match(homeTeam, awayTeam, matchDTO.getMatchDate());

        return matchRepository.save(matchToSave);
    }




    private Team findOrCreateTeam(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findByName(teamName);
        return optionalTeam.orElseGet(() ->
                teamRepository.save(new Team(teamName)));
    }
}
