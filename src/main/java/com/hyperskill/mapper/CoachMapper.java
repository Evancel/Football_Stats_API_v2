package com.hyperskill.mapper;

import com.hyperskill.dto.CoachDTO;
import com.hyperskill.entity.Coach;

public class CoachMapper {
    public static CoachDTO toDTO(Coach coach) {
        CoachDTO dto = new CoachDTO();
        dto.setId(coach.getId());
        dto.setFirstName(coach.getFirstName());
        dto.setLastName(coach.getLastName());
        dto.setTeamName(coach.getTeam().getName());
        return dto;
    }
}
