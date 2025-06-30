package com.hyperskill.model.mapper;

import com.hyperskill.model.dto.CoachDTO;
import com.hyperskill.model.dto.PlayerResponseDTO;
import com.hyperskill.model.dto.TeamRequestDTO;
import com.hyperskill.model.dto.TeamResponseDTO;
import com.hyperskill.model.entity.Match;
import com.hyperskill.model.entity.Team;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TeamMapper {
    public static TeamResponseDTO toDTO(Team team) {
        TeamResponseDTO teamDTO = new TeamResponseDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        if (team.getCoach() != null){
            CoachDTO coachDTO = CoachMapper.toDTO(team.getCoach());
            teamDTO.setCoach(coachDTO);
        }
        teamDTO.setGoalScored(team.getGoalScored());

        // Combine home and away matches
        Set<Match> allMatches = new HashSet<>();
        allMatches.addAll(team.getHomeMatches());
        allMatches.addAll(team.getAwayMatches());

        teamDTO.setMatches(allMatches.stream().map(MatchMapper::toDTO).collect(Collectors.toSet()));

        List<PlayerResponseDTO> players = team.getPlayers().stream()
                .map(PlayerMapper::toDTO)
                .toList();

        teamDTO.setPlayers(players);
        return teamDTO;
    }

    public static Team toModel(TeamRequestDTO teamRequestDTO) {
        Team team = new Team();
        team.setName(teamRequestDTO.getName());
        return team;
    }
}
