package com.hyperskill.controller;

import com.hyperskill.entity.Player;
import com.hyperskill.dto.PlayerDTO;
import com.hyperskill.mapper.PlayerMapper;
import com.hyperskill.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody PlayerDTO request){
        if(request == null){
            return ResponseEntity.badRequest().build();
        }

        Player playerToSave = PlayerMapper.toEntity(request);
        playerService.save(playerToSave);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Player> player = playerService.findById(id);
        if(player.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
        }

        PlayerDTO dto = PlayerMapper.toDTO(player.get());
        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public ResponseEntity<List<PlayerDTO>> findAll(){
        List<Player> players = playerService.findPlayers();
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for(Player player: players){
            playerDTOS.add(PlayerMapper.toDTO(player));
        }
        return ResponseEntity.ok(playerDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PlayerDTO dto) {
        try {
            Player updated = playerService.updatePlayer(id, dto.getFirstName(), dto.getLastName(), dto.getTeamName());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = playerService.deleteById(id); // returns true if deleted
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<PlayerDTO>> findByTeam(@PathVariable String teamName) {
        List<Player> players = playerService.findByTeamName(teamName);
        List<PlayerDTO> dtos = new ArrayList<>();
        for(Player player: players){
            dtos.add(PlayerMapper.toDTO(player));
        }
        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/{id}/stats")
    public ResponseEntity<?> getStats(@PathVariable Long id) {
        Optional<Player> optionalPlayer = playerService.findById(id);
        if (optionalPlayer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
        }

        Player player = optionalPlayer.get();
        Map<String, Object> stats = new HashMap<>();
        stats.put("goals", player.getGoals());
        stats.put("matches", player.getPlayedMatches());
        stats.put("goalsPerMatch", player.getPlayedMatches() > 0 ?
            (double) player.getGoals() / player.getPlayedMatches() : 0);

        return ResponseEntity.ok(stats);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PlayerDTO>> searchByName(
            @RequestParam String firstName,
            @RequestParam(required = false) String lastName) {

        List<Player> players = playerService.searchByName(firstName, lastName);
        List<PlayerDTO> dtos = new ArrayList<>();
        for(Player player: players){
            dtos.add(PlayerMapper.toDTO(player));
        }
        return ResponseEntity.ok(dtos);
    }
}
