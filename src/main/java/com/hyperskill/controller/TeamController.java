package com.hyperskill.controller;

import com.hyperskill.model.dto.TeamRequestDTO;
import com.hyperskill.model.dto.TeamResponseDTO;
import com.hyperskill.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    /**
     * Retrieves all teams from the database.
     *
     * @return ResponseEntity containing a list of all teams with HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    /**
     * Retrieves a specific team by its ID.
     *
     * @param id the ID of the team to retrieve
     * @return ResponseEntity containing the requested team with HTTP status 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    /**
     * Creates a new team.
     *
     * @param teamRequestDTO the team data transfer object containing the team information
     * @return ResponseEntity with HTTP status 201 (Created) and location header pointing to the new resource
     */
    @PostMapping
    public ResponseEntity<Void> createTeam(@Valid @RequestBody TeamRequestDTO teamRequestDTO) {
        TeamResponseDTO teamResponseDTO = teamService.createTeam(teamRequestDTO);

        // Create a URI for the newly created team
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(teamResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Deletes a team by its ID.
     *
     * @param id the ID of the team to delete
     * @return ResponseEntity with HTTP status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing team.
     *
     * @param id             the ID of the team to update
     * @param teamRequestDTO the team data transfer object containing the updated team information
     * @return ResponseEntity containing the updated team with HTTP status 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> updateTeam(@PathVariable Long id, @Valid @RequestBody TeamRequestDTO teamRequestDTO) {
        TeamResponseDTO teamResponseDTO = teamService.updateTeam(id, teamRequestDTO);
        return ResponseEntity.ok(teamResponseDTO);
    }

    /**
     * Retrieves a specific team by its name using a query parameter.
     *
     * @param name the name of the team to retrieve
     * @return ResponseEntity containing the requested team with HTTP status 200 (OK)
     */
    @GetMapping("/search")
    public ResponseEntity<TeamResponseDTO> searchTeamByName(@RequestParam String name) {
        return ResponseEntity.ok(teamService.getTeamByName(name));
    }
}
