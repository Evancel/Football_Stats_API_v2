package com.hyperskill.service;

import com.hyperskill.entity.Match;
import com.hyperskill.entity.Team;
import com.hyperskill.repository.MatchRepository;
import com.hyperskill.repository.TeamRepository;
import org.springframework.stereotype.Service;

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



    private Team findOrCreateTeam(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findByName(teamName);
        return optionalTeam.orElseGet(() ->
                teamRepository.save(new Team(teamName)));
    }
}
