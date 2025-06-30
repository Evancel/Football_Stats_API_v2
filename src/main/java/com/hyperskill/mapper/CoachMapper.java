
package com.hyperskill.mapper;

import com.hyperskill.dto.CoachDTO;
import com.hyperskill.entity.Coach;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CoachMapper {
    public static CoachDTO toDTO(Coach coach) {
        if (coach == null) {
            return null;
        }

        CoachDTO dto = new CoachDTO();
        dto.setFirstName(coach.getFirstName());
        dto.setLastName(coach.getLastName());

        // Handle null team case and null team name
        if (coach.getTeam() != null && coach.getTeam().getName() != null) {
            dto.setTeamName(coach.getTeam().getName());
        }

        return dto;
    }

    public static Coach toEntity(CoachDTO dto) {
        if (dto == null) {
            return null;
        }

        // Validate required fields to prevent constraint violations
        if (dto.getFirstName() == null || dto.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (dto.getLastName() == null || dto.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }

        Coach coach = new Coach();
        coach.setFirstName(dto.getFirstName().trim());
        coach.setLastName(dto.getLastName().trim());

        // Note: Team entity must be set separately using a service layer
        // as DTO only contains teamName string, not the actual Team entity
        // The team relationship should be handled at the service level where
        // the Team entity can be retrieved by name and properly associated

        return coach;
    }

    public static List<CoachDTO> toDTOList(List<Coach> coaches) {
        if (coaches == null) {
            return Collections.emptyList();
        }

        return coaches.stream()
                .map(CoachMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Coach> toEntityList(List<CoachDTO> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(CoachMapper::toEntity)
                .collect(Collectors.toList());
    }
}