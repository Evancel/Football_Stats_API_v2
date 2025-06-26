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
        return new Player(dto.getFirstName(), dto.getLastName(), new Team(dto.getTeamName()));
    }

    public static List<PlayerDTO> listToDTO(List<Player> players) {
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for(Player player: players){
            PlayerDTO dto = new PlayerDTO();
            dto.setFirstName(player.getFirstName());
            dto.setLastName(player.getLastName());
            dto.setTeamName(player.getTeam().getName());
            playerDTOS.add(dto);
        }

        return playerDTOS;
    }

    public static List<Player> toEntity(List<PlayerDTO> playerDTOs) {
        List<Player> players = new ArrayList<>();
        for(PlayerDTO dto:playerDTOs){
            players.add(new Player(dto.getFirstName(), dto.getLastName(), new Team(dto.getTeamName())));
        }
        return players;
    }
}
