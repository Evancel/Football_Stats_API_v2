package com.hyperskill.mapper;

import com.hyperskill.dto.PlayerRequestDTO;
import com.hyperskill.dto.PlayerResponseDTO;
import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;

public class PlayerMapper {
    public static PlayerResponseDTO toDTO(Player player) {
        PlayerResponseDTO dto = new PlayerResponseDTO();
        dto.setId(player.getId());
        dto.setFirstName(player.getFirstName());
        dto.setLastName(player.getLastName());
        dto.setTeamName(player.getTeam().getName());
        dto.setGoals(player.getGoals().size());
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
