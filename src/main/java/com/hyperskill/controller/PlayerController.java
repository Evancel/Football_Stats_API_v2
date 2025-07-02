package com.hyperskill.controller;

import com.hyperskill.model.dto.PlayerRequestDTO;
import com.hyperskill.model.dto.PlayerResponseDTO;
import com.hyperskill.model.entity.Player;
import com.hyperskill.service.PlayerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Creates a new player.
     *
     * @param request the player data transfer object containing the player information
     * @return ResponseEntity with HTTP status 201 (Created) and location header pointing to the new resource
     */
    @PostMapping()
    public ResponseEntity<Void> add(@Valid @RequestBody PlayerRequestDTO request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }

        Player savedPlayer = playerService.save(request);

        // Create a URI for the newly created team
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPlayer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Retrieves a specific player by its ID.
     *
     * @param id the ID of the player to retrieve
     * @return ResponseEntity containing the requested player with HTTP status 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.findById(id));
    }


    /**
     * Retrieves all players from the database.
     *
     * @return ResponseEntity containing a list of all players with HTTP status 200 (OK)
     */
    @GetMapping()
    public ResponseEntity<Page<PlayerResponseDTO>> findAll(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        return ResponseEntity.ok(playerService.findAll(page, size));
    }

    /**
     * Updates an existing player.
     *
     * @param id      the ID of the player to update
     * @param request the player data transfer object containing the updated player information
     * @return ResponseEntity containing the updated player with HTTP status 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody PlayerRequestDTO request) {
        PlayerResponseDTO updated = playerService.updatePlayer(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * Deletes a player by its ID.
     *
     * @param id the ID of the player to delete
     * @return ResponseEntity with HTTP status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Searches for a player by first name and optional last name.
     *
     * @param firstName the first name of the player
     * @param lastName  the last name of the player (optional)
     * @return ResponseEntity with the matching player
     */
    @GetMapping("/search")
    public ResponseEntity<PlayerResponseDTO> searchByName(
            @RequestParam String firstName,
            @RequestParam(required = false) String lastName) {
        return ResponseEntity.ok(playerService.searchDTOByName(firstName, lastName));
    }
}
