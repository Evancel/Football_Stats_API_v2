package com.hyperskill.entity;

public class PlayerMapper {
    public static PlayerDTO toDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setFirstName(player.getFirstName());
        dto.setLastName(player.getLastName());
        dto.setTeamName(player.getTeam().getName());
        return dto;
    }

    public static Player toEntity(PlayerDTO dto) {
        return new Player(dto.getFirstName(), dto.getLastName(), new Team(dto.getTeamName()));
    }
}
