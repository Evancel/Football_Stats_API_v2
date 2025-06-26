package com.hyperskill.service;

import com.hyperskill.dto.TeamRequestDTO;
import com.hyperskill.dto.TeamResponseDTO;
import com.hyperskill.entity.Team;
import com.hyperskill.exception.TeamAlreadyExistsException;
import com.hyperskill.exception.TeamNotFoundException;
import com.hyperskill.mapper.TeamMapper;
import com.hyperskill.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * Create a new team
     */
    public TeamRequestDTO createTeam(TeamRequestDTO teamRequestDTO) {
        if (teamRepository.existsByName(teamRequestDTO.getName())) {
            throw new TeamAlreadyExistsException("A team with same name already exists: " + teamRequestDTO.getName());
        }
        return teamRepository.save(new Team(teamRequestDTO.getName()));
    }

    /**
     * Get a team by ID
     */
    public List<TeamResponseDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(TeamMapper::toDTO).toList();
    }

    /**
     * Get a team by ID
     */
    public TeamResponseDTO getTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException("Team with id: " + id + " not found"));
    }

    /**
     * Update a team
     */
    public TeamResponseDTO updateTeam(Long id, TeamRequestDTO teamRequestDTO) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException("Team with id: " + id + " not found"));
        if (teamRepository.existsByNameAndIdNot(teamRequestDTO.getName(), id)) {
            throw new TeamAlreadyExistsException("A team with same name already exists: " + teamRequestDTO.getName());
        }
        team.setName(teamRequestDTO.getName());
        return teamRepository.save(team);
    }

    /**
     * Delete a team
     */
    public void deleteTeamById(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new TeamNotFoundException("Team with id: " + id + " not found");
        }
        teamRepository.deleteById(id);
    }
}
