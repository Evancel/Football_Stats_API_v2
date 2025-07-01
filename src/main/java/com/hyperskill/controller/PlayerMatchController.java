package com.hyperskill.controller;

import com.hyperskill.model.dto.PlayerMatchRequestDTO;
import com.hyperskill.model.dto.PlayerMatchResponseDTO;
import com.hyperskill.service.PlayerMatchService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player-matches")
public class PlayerMatchController {

    private final PlayerMatchService playerMatchService;

    public PlayerMatchController(PlayerMatchService playerMatchService) {
        this.playerMatchService = playerMatchService;
    }

    @PostMapping("/match/{matchId}")
    public ResponseEntity<Void> registerPlayers(
            @PathVariable Long matchId,
            @RequestBody List<Long> playerIds) {
        playerMatchService.registerPlayersForMatch(matchId, playerIds);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerMatchResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(playerMatchService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<PlayerMatchResponseDTO>> getAll() {
        return ResponseEntity.ok(playerMatchService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerMatchResponseDTO> update(@PathVariable Long id,
                                                 @RequestBody @Valid PlayerMatchRequestDTO requestDTO) {
        return ResponseEntity.ok(playerMatchService.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerMatchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

