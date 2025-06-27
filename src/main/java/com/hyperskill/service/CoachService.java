package com.hyperskill.service;

import com.hyperskill.dto.CoachDTO;
import com.hyperskill.entity.Coach;
import com.hyperskill.entity.Team;
import com.hyperskill.exception.CoachNotFoundException;
import com.hyperskill.mapper.CoachMapper;
import com.hyperskill.repository.CoachRepository;
import com.hyperskill.repository.GoalRepository;
import com.hyperskill.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation of CoachService
 * Provides business logic for Coach entity
 */
@Service
public class CoachService {
    private final CoachRepository coachRepository;
    private final TeamRepository teamRepository;
    private final GoalRepository goalRepository;

    public CoachService(CoachRepository coachRepository, TeamRepository teamRepository, GoalRepository goalRepository) {
        this.coachRepository = coachRepository;
        this.teamRepository = teamRepository;
        this.goalRepository = goalRepository;
    }
    /**
     * Helper method to get a coach by ID
     *
     * @param id the ID of the coach to find
     * @return the coach entity
     * @throws com.hyperskill.exception.CoachNotFoundException if coach not found
     */
    private Coach getCoachById(Long id) {
        return coachRepository.findById(id)
                .orElseThrow(() -> new CoachNotFoundException("Coach with id: " + id + " not found"));
    }

    /**
     * Create a new coach from DTO
     * @param coachDTO the coach DTO to create
     * @return the created coach as DTO
     */
    public CoachDTO createCoachFromDTO(CoachDTO coachDTO) {
        // Convert DTO to entity
        Coach coach = CoachMapper.toEntity(coachDTO);

        // Set team if teamName is provided
        if (coachDTO.getTeamName() != null && !coachDTO.getTeamName().isEmpty()) {
            Team team = teamRepository.findByName(coachDTO.getTeamName())
                    .orElseThrow(() -> new IllegalArgumentException("Team with name: " + coachDTO.getTeamName() + " not found"));
            coach.setTeam(team);
        }

        // Save the entity
        Coach savedCoach = coachRepository.save(coach);

        // Convert back to DTO and return
        return CoachMapper.toDTO(savedCoach);
    }

    /**
     * Get all coaches as DTOs
     * @return list of all coaches as DTOs
     */
    public List<CoachDTO> getAllCoachesAsDTO() {
        List<Coach> coaches = coachRepository.findAll();
        return CoachMapper.toDTOList(coaches);
    }

    /**
     * Get a coach by ID as DTO
     * @param id the ID of the coach
     * @return the coach with the given ID as DTO
     * @throws com.hyperskill.exception.CoachNotFoundException if coach not found
     */
    public CoachDTO getCoachByIdAsDTO(Long id) {
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new CoachNotFoundException("Coach with id: " + id + " not found"));
        return CoachMapper.toDTO(coach);
    }

    /**
     * Get coaches by team name as DTOs
     * @param teamName the name of the team
     * @return list of coaches for the given team as DTOs
     */
    public List<CoachDTO> getCoachesByTeamNameAsDTO(String teamName) {
        Team team = teamRepository.findByName(teamName)
                .orElseThrow(() -> new IllegalArgumentException("Team with name: " + teamName + " not found"));
        List<Coach> coaches = coachRepository.findByTeam(team);
        return CoachMapper.toDTOList(coaches);
    }

    /**
     * Get a coach by first name and last name as DTO
     * @param firstName the first name of the coach
     * @param lastName the last name of the coach
     * @return the coach with the given first name and last name as DTO
     * @throws com.hyperskill.exception.CoachNotFoundException if coach not found
     */
    public CoachDTO getCoachByFullNameAsDTO(String firstName, String lastName) {
        Coach coach = coachRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new CoachNotFoundException(
                        "Coach with name: " + firstName + " " + lastName + " not found"));
        return CoachMapper.toDTO(coach);
    }

    /**
     * Get coaches by first name as DTOs
     * @param firstName the first name of the coach
     * @return list of coaches with the given first name as DTOs
     */
    public List<CoachDTO> getCoachesByFirstNameAsDTO(String firstName) {
        List<Coach> coaches = coachRepository.findByFirstName(firstName);
        return CoachMapper.toDTOList(coaches);
    }

    /**
     * Get coaches by last name as DTOs
     * @param lastName the last name of the coach
     * @return list of coaches with the given last name as DTOs
     */
    public List<CoachDTO> getCoachesByLastNameAsDTO(String lastName) {
        List<Coach> coaches = coachRepository.findByLastName(lastName);
        return CoachMapper.toDTOList(coaches);
    }

    /**
     * Update a coach using DTO
     * @param id the ID of the coach to update
     * @param coachDTO the updated coach data as DTO
     * @return the updated coach as DTO
     * @throws com.hyperskill.exception.CoachNotFoundException if coach not found
     */
    public CoachDTO updateCoachFromDTO(Long id, CoachDTO coachDTO) {
        Coach existingCoach = getCoachById(id);

        // Update fields
        existingCoach.setFirstName(coachDTO.getFirstName());
        existingCoach.setLastName(coachDTO.getLastName());

        // Validate that the team exists if provided
        if (coachDTO.getTeamName() != null && !coachDTO.getTeamName().isEmpty()) {
            Team team = teamRepository.findByName(coachDTO.getTeamName())
                    .orElseThrow(() -> new IllegalArgumentException("Team with name: " + coachDTO.getTeamName() + " not found"));
            existingCoach.setTeam(team);
        }

        Coach updatedCoach = coachRepository.save(existingCoach);
        return CoachMapper.toDTO(updatedCoach);
    }

    /**
     * Delete a coach by ID
     *
     * @param id the ID of the coach to delete
     * @throws com.hyperskill.exception.CoachNotFoundException if coach not found
     */
    public void deleteCoachById(Long id) {
        if (!coachRepository.existsById(id)) {
            throw new CoachNotFoundException("Coach with id: " + id + " not found");
        }
        coachRepository.deleteById(id);
    }

    /**
     * Calculate goals scored by the coach's current team using DTO
     * @param coachDTO the coach DTO
     * @return number of goals scored
     */
    public int calculateGoalsScoredCurrentTeamDTO(CoachDTO coachDTO) {
        // Find the coach by name and team
        Coach coach = coachRepository.findByFirstNameAndLastName(coachDTO.getFirstName(), coachDTO.getLastName())
                .orElseThrow(() -> new CoachNotFoundException("Coach with name: " +
                        coachDTO.getFirstName() + " " + coachDTO.getLastName() + " not found"));

        // TODO: Implement in future revision when MatchRepository is available
        // This method should calculate actual goals from match data
        // For now, return 0 as a placeholder
        return 0;
    }

    /**
     * Get total matches played by the coach using DTO
     * @param coachDTO the coach DTO
     * @return number of matches played
     */
    public int getPlayedMatchesTotalDTO(CoachDTO coachDTO) {
        // TODO: Implement in future revision when MatchRepository is available
        // This method should calculate actual matches played from match data
        // For now, return 0 as a placeholder
        return 0;
    }

    /**
     * Calculate average goals scored per match using DTO
     * @param coachDTO the coach DTO
     * @return average goals scored
     */
    public double calculateAverageGoalsScoredDTO(CoachDTO coachDTO) {
        int playedMatches = getPlayedMatchesTotalDTO(coachDTO);
        if (playedMatches == 0) {
            return 0.0;
        }
        return (double) calculateGoalsScoredCurrentTeamDTO(coachDTO) / playedMatches;
    }

    /**
     * Find top coaches by number of victories as DTOs
     * @param n number of coaches to return
     * @return list of top coaches as DTOs
     */
    public List<CoachDTO> findTopCoachesByVictoriesAsDTO(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Parameter n must be a positive integer");
        }

        // TODO: Implement in future revision when MatchRepository is available
        // This method should calculate actual victories from match data
        // and return top N coaches sorted by number of victories
        throw new UnsupportedOperationException("Method will be implemented in future revision");
    }

    /**
     * Find top coaches by goals scored as DTOs
     * @param n number of coaches to return
     * @return list of top coaches as DTOs
     */
    public List<CoachDTO> findTopCoachesByGoalsScoredAsDTO(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Parameter n must be a positive integer");
        }

        // TODO: Implement in future revision when MatchRepository is available
        // This method should calculate goals scored from match data
        // and return top N coaches sorted by goals scored by their teams
        throw new UnsupportedOperationException("Method will be implemented in future revision");
    }

    /**
     * Find top coaches by win percentage as DTOs
     * @param n number of coaches to return
     * @return list of top coaches as DTOs
     */
    public List<CoachDTO> findTopCoachesByWinPercentageAsDTO(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Parameter n must be a positive integer");
        }

        // TODO: Implement in future revision when MatchRepository is available
        // This method should calculate win percentage from match data
        // and return top N coaches sorted by win percentage
        throw new UnsupportedOperationException("Method will be implemented in future revision");
    }
}