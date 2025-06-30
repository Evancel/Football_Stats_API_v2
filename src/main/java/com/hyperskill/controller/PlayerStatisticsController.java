package com.hyperskill.controller;

import com.hyperskill.model.dto.PlayerAvgGoalsResponse;
import com.hyperskill.model.dto.PlayerGoalsResponse;
import com.hyperskill.model.dto.PlayerMatchesResponce;
import com.hyperskill.model.dto.PlayerResponseDTO;
import com.hyperskill.service.PlayerService;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats/players")
public class PlayerStatisticsController {
    private final PlayerService playerService;

    public PlayerStatisticsController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/top-by-goals")
    public ResponseEntity<Page<PlayerResponseDTO>> getTopPlayersByScoredGoals(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        return ResponseEntity.ok(playerService.getTopPlayersByScoredGoals(page, size));
    }

    @GetMapping("/top-by-matches")
    public ResponseEntity<Page<PlayerResponseDTO>> getTopPlayersByMatches(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        return ResponseEntity.ok(playerService.getTopPlayersByMatches(page, size));
    }

    @GetMapping("/{id}/goals-per-year")
    public ResponseEntity<PlayerGoalsResponse> getScoredGoalsPerYear(@PathVariable Long id,
                                                                     @RequestParam(required = false) Integer year) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(playerService.getScoredGoalsPerYear(id, year));
    }

    @GetMapping("/{id}/matches-per-year")
    public ResponseEntity<PlayerMatchesResponce> getMatchesPerYear(@PathVariable Long id,
                                                                   @RequestParam(required = false) Integer year) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(playerService.getMatchesPerYear(id, year));
    }

    @GetMapping("/{id}/avg-goals-total")
    public ResponseEntity<PlayerAvgGoalsResponse> getAverageScoredGoals(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(playerService.getAverageScoredGoals(id));
    }

    @GetMapping("/{id}/avg-goals-per-year")
    public ResponseEntity<PlayerAvgGoalsResponse> getAverageScoredGoalsPerYear(@PathVariable Long id,
                                                                               @RequestParam(required = false) Integer year) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(playerService.getAverageScoredGoalsPerYear(id, year));
    }
}
