package com.hyperskill.model.mapper;

import com.hyperskill.model.dto.PlayerRequestDTO;
import com.hyperskill.model.dto.PlayerResponseDTO;
import com.hyperskill.model.entity.Player;
import com.hyperskill.model.entity.Team;

public class PlayerMapper {
    public static PlayerResponseDTO toDTO(Player player) {
        PlayerResponseDTO dto = new PlayerResponseDTO();
        dto.setId(player.getId());
        dto.setFirstName(player.getFirstName());
        dto.setLastName(player.getLastName());
        dto.setTeamName(player.getTeam().getName());
        dto.setGoals(player.getGoals().size());
        dto.setPlayerMatches(player.getPlayerMatches().size());
        return dto;
    }

    public static Player toEntity(PlayerRequestDTO dto, Team team) {
        Player player = new Player();
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        player.setTeam(team);
        return player;
    }
}
