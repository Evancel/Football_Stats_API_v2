package com.hyperskill.mapper;

import com.hyperskill.dto.PlayerDTO;
import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;

public class PlayerMapper {
    public static PlayerDTO toDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setFirstName(player.getFirstName());
        dto.setLastName(player.getLastName());
        dto.setTeamName(player.getTeam().getName());
        return dto;
    }

    public static Player toEntity(PlayerDTO dto) {
        Team team = new Team();
        team.setName(dto.getTeamName());
        return new Player(dto.getFirstName(), dto.getLastName(), team);
    }
}
