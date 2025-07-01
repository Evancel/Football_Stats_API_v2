package com.hyperskill.model.mapper;

import com.hyperskill.model.dto.PlayerMatchResponseDTO;
import com.hyperskill.model.entity.PlayerMatch;

public class PlayerMatchMapper {
    public static PlayerMatchResponseDTO toDTO(PlayerMatch pm) {
        PlayerMatchResponseDTO dto = new PlayerMatchResponseDTO();
        dto.setId(pm.getId());
        dto.setPlayerId(pm.getPlayer().getId());
        dto.setMatchId(pm.getMatch().getId());
        dto.setPlayerName(pm.getPlayer().getFirstName() + " " + pm.getPlayer().getLastName());
        dto.setMatchDate(pm.getMatch().getMatchDate());
        return dto;
    }
}
