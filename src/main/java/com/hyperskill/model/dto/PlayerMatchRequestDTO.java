package com.hyperskill.model.dto;

import jakarta.validation.constraints.NotNull;

public class PlayerMatchRequestDTO {
    @NotNull
    private Long playerId;

    @NotNull
    private Long matchId;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
}
