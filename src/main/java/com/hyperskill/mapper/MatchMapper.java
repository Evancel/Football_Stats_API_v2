package com.hyperskill.mapper;

import com.hyperskill.dto.MatchDTO;
import com.hyperskill.entity.Match;

public class MatchMapper {
    public static MatchDTO toDTO(Match match) {
        MatchDTO dto = new MatchDTO();
        dto.setId(match.getId());
        dto.setAwayTeamName(match.getAwayTeam().getName());
        dto.setHomeTeamName(match.getHomeTeam().getName());
        dto.setAwayTeamScore(match.getAwayScore());
        dto.setHomeTeamScore(match.getHomeScore());
        return dto;
    }
}
