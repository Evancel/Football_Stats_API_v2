package com.hyperskill.mapper;

import com.hyperskill.dto.TeamResponseDTO;
import com.hyperskill.entity.Team;

public class TeamMapper {
    public static TeamResponseDTO toDTO(Team team) {
        TeamResponseDTO teamDTO = new TeamResponseDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setCoach(team.getCoach());
        teamDTO.setGoalScored(team.getGoalScored());
        teamDTO.setMatches(team.getAllMatches());
        teamDTO.setPlayers(team.getPlayers());
        return teamDTO;
    }
}
