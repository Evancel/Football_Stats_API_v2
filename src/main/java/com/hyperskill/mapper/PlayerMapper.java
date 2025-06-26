package com.hyperskill.mapper;

import com.hyperskill.dto.PlayerDTO;
import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;

import java.util.ArrayList;
import java.util.List;

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

        Player player = new Player();
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        player.setTeam(team);
        return player;
    }

    public static List<PlayerDTO> listToDTO(List<Player> players) {
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for (Player player : players) {
            playerDTOS.add(toDTO(player));
        }

        return playerDTOS;
    }

    public static List<Player> toEntity(List<PlayerDTO> playerDTOs) {
        List<Player> players = new ArrayList<>();
        for (PlayerDTO dto : playerDTOs) {
            players.add(toEntity(dto));
        }
        return players;
    }
}
