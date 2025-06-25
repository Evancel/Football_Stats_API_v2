package com.hyperskill.controller;

import com.hyperskill.dto.PlayerDTO;
import com.hyperskill.entity.Player;
import com.hyperskill.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Void> add(@Valid @RequestBody PlayerDTO request) {
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
    public ResponseEntity<PlayerDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.findDTOById(id));
    }


    /**
     * Retrieves all players from the database.
     *
     * @return ResponseEntity containing a list of all players with HTTP status 200 (OK)
     */
    @GetMapping()
    public ResponseEntity<List<PlayerDTO>> findAll() {
        return ResponseEntity.ok(playerService.findDTOs());
    }

    /**
     * Updates an existing player.
     *
     * @param id  the ID of the player to update
     * @param dto the player data transfer object containing the updated player information
     * @return ResponseEntity containing the updated player with HTTP status 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> update(@PathVariable Long id, @Valid @RequestBody PlayerDTO dto) {
        PlayerDTO updated = playerService.updatePlayer(id, dto.getFirstName(), dto.getLastName(), dto.getTeamName());
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
     * Retrieves statistics for a specific player.
     *
     * @param id the ID of the player
     * @return ResponseEntity with player's stats (goals, matches, goals per match)
     */
    @GetMapping("/{id}/stats")
    public ResponseEntity<Map<String, Object>> getStats(@PathVariable Long id) {
        Player player = playerService.findById(id);
        Map<String, Object> stats = new HashMap<>();
        stats.put("goals", player.getGoals());
        stats.put("matches", player.getPlayedMatches());
        stats.put("goalsPerMatch", player.getPlayedMatches() > 0 ?
                (double) player.getGoals() / player.getPlayedMatches() : 0);

        return ResponseEntity.ok(stats);
    }

    /**
     * Searches for a player by first name and optional last name.
     *
     * @param firstName the first name of the player
     * @param lastName  the last name of the player (optional)
     * @return ResponseEntity with the matching player
     */
    @GetMapping("/search")
    public ResponseEntity<PlayerDTO> searchByName(
            @RequestParam String firstName,
            @RequestParam(required = false) String lastName) {
        return ResponseEntity.ok(playerService.searchDTOByName(firstName, lastName));
    }
}
