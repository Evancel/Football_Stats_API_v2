package com.hyperskill.mapper;

import com.hyperskill.dto.MatchDTO;
import com.hyperskill.entity.Match;
import com.hyperskill.entity.Team;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between Match entities and DTOs
 */
public class MatchMapper {

    /**
     * Convert a Match entity to a MatchDTO
     * @param match the Match entity
     * @return the MatchDTO
     */
    public static MatchDTO toDTO(Match match) {
        if (match == null) {
            return null;
        }

        MatchDTO dto = new MatchDTO();
        dto.setId(match.getId());
        dto.setAwayTeamName(match.getAwayTeam().getName());
        dto.setHomeTeamName(match.getHomeTeam().getName());
        dto.setAwayTeamScore(match.getAwayScore());
        dto.setHomeTeamScore(match.getHomeScore());
        dto.setMatchDate(match.getMatchDate());
        return dto;
    }

    /**
     * Convert a list of Match entities to a list of MatchDTOs
     * @param matches the list of Match entities
     * @return the list of MatchDTOs
     */
    public static List<MatchDTO> listToDTO(List<Match> matches) {
        if (matches == null) {
            return null;
        }

        return matches.stream()
                .map(MatchMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert a MatchDTO to a Match entity
     * Note: This method requires Team entities to be provided separately
     * @param dto the MatchDTO
     * @param homeTeam the home Team entity
     * @param awayTeam the away Team entity
     * @return the Match entity
     */
    public static Match toEntity(MatchDTO dto, Team homeTeam, Team awayTeam) {
        if (dto == null) {
            return null;
        }

        Match match = new Match(
                homeTeam,
                awayTeam,
                dto.getHomeTeamScore(),
                dto.getAwayTeamScore(),
                dto.getMatchDate()
        );

        return match;
    }
}
