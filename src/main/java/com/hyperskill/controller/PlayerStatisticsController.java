package com.hyperskill.controller;

import com.hyperskill.model.dto.PlayerStatisticsResponseDTO;
import com.hyperskill.model.dto.PlayerStatsDTO;
import com.hyperskill.service.PlayerStatisticsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats/players")
public class PlayerStatisticsController {
    private final PlayerStatisticsService playerStatisticsService;

    public PlayerStatisticsController(PlayerStatisticsService playerStatisticsService) {
        this.playerStatisticsService = playerStatisticsService;
    }

    /**
     * Retrieves statistics for a specific player.
     *
     * @param playerId the ID of the player
     * @return ResponseEntity with player's stats (goals, matches, goals per match)
     */

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerStatisticsResponseDTO> getPlayerStats(@PathVariable Long playerId) {
        return ResponseEntity.ok(playerStatisticsService.getStatisticsForPlayer(playerId));
    }

    @GetMapping("/top")
    public Page<PlayerStatsDTO> getTopStats(
            @RequestParam String metric,
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sort) {

        return playerStatisticsService.getTopStats(metric, year, page, size, sort);
    }
}
