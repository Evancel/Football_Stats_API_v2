package com.hyperskill.service;

import com.hyperskill.model.dto.MatchDTO;
import com.hyperskill.model.entity.Goal;
import com.hyperskill.model.entity.Match;
import com.hyperskill.model.entity.Team;
import com.hyperskill.exception.MatchNotFoundException;
import com.hyperskill.model.mapper.MatchMapper;
import com.hyperskill.repository.GoalRepository;
import com.hyperskill.repository.MatchRepository;
import com.hyperskill.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Match entities.
 */
@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final GoalRepository goalRepository;

    /**
     * Constructor for dependency injection.
     * @param matchRepository the match repository
     * @param teamRepository the team repository
     * @param goalRepository the goal repository
     */
    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository, GoalRepository goalRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.goalRepository = goalRepository;
    }

    /**
     * Create a new match
     * @param matchDTO the match data transfer object
     * @return the created match entity
     */
    @Transactional
    public Match save(MatchDTO matchDTO) {
        Team homeTeam = findOrCreateTeam(matchDTO.getHomeTeamName());
        Team awayTeam = findOrCreateTeam(matchDTO.getAwayTeamName());

        Match matchToSave = new Match(
                homeTeam, 
                awayTeam, 
                matchDTO.getHomeTeamScore(), 
                matchDTO.getAwayTeamScore(), 
                matchDTO.getMatchDate()
        );

        return matchRepository.save(matchToSave);
    }

    /**
     * Update an existing match
     * @param id the match ID
     * @param matchDTO the updated match data
     * @return the updated match DTO
     */
    @Transactional
    public MatchDTO update(Long id, MatchDTO matchDTO) {
        Match match = findById(id);

        Team homeTeam = findOrCreateTeam(matchDTO.getHomeTeamName());
        Team awayTeam = findOrCreateTeam(matchDTO.getAwayTeamName());

        // Update match fields
        match = new Match(
                homeTeam,
                awayTeam,
                matchDTO.getHomeTeamScore(),
                matchDTO.getAwayTeamScore(),
                matchDTO.getMatchDate()
        );

        match = matchRepository.save(match);
        return MatchMapper.toDTO(match);
    }

    /**
     * Find a match by its ID
     * @param id the match ID
     * @return the match entity
     */
    public Match findById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + id));
    }

    /**
     * Find a match by its ID and return as DTO
     * @param id the match ID
     * @return the match DTO
     */
    public MatchDTO findDTOById(Long id) {
        Match match = findById(id);
        return MatchMapper.toDTO(match);
    }

    /**
     * Find all matches
     * @return list of all matches
     */
    public List<Match> findMatches() {
        return matchRepository.findAll();
    }

    /**
     * Find all matches and return as DTOs
     * @return list of all match DTOs
     */
    public List<MatchDTO> findDTOs() {
        return MatchMapper.listToDTO(findMatches());
    }

    /**
     * Delete a match by its ID
     * @param id the match ID
     */
    @Transactional
    public void deleteById(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new MatchNotFoundException("Match not found with id: " + id);
        }
        matchRepository.deleteById(id);
    }

    /**
     * Find matches by team
     * @param teamName the team name
     * @return list of matches involving the team
     */
    public List<MatchDTO> findMatchesByTeam(String teamName) {
        List<Match> matches = matchRepository.findByHomeTeam_NameOrAwayTeam_Name(teamName, teamName);
        return MatchMapper.listToDTO(matches);
    }

    /**
     * Find matches within a date range
     * @param startDate the start date
     * @param endDate the end date
     * @return list of matches within the date range
     */
    public List<MatchDTO> findMatchesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Match> matches = matchRepository.findByMatchDateBetween(startDate, endDate);
        return MatchMapper.listToDTO(matches);
    }

    /**
     * Find matches between specific teams
     * @param homeTeamName the home team name
     * @param awayTeamName the away team name
     * @return list of matches between the teams
     */
    public List<MatchDTO> findMatchesBetweenTeams(String homeTeamName, String awayTeamName) {
        Team homeTeam = findTeamByName(homeTeamName);
        Team awayTeam = findTeamByName(awayTeamName);

        List<Match> matches = matchRepository.findByHomeTeamAndAwayTeam(homeTeam, awayTeam);
        return MatchMapper.listToDTO(matches);
    }

    /**
     * Find matches with a specific score
     * @param homeScore the home team score
     * @param awayScore the away team score
     * @return list of matches with the specific score
     */
    public List<MatchDTO> findMatchesWithScore(int homeScore, int awayScore) {
        List<Match> matches = matchRepository.findByHomeScoreAndAwayScore(homeScore, awayScore);
        return MatchMapper.listToDTO(matches);
    }

    /**
     * Find draw matches
     * @return list of matches that ended in a draw
     */
    public List<MatchDTO> findDrawMatches() {
        // Get all matches
        List<Match> allMatches = matchRepository.findAll();

        // Filter to find only matches that ended in a draw using the Match.isDraw() method
        List<Match> drawMatches = allMatches.stream()
                .filter(Match::isDraw)
                .toList();

        return MatchMapper.listToDTO(drawMatches);
    }

    /**
     * Find high-scoring matches
     * @param minTotalScore the minimum total score
     * @return list of matches with a total score at or above the minimum
     */
    public List<MatchDTO> findHighScoringMatches(int minTotalScore) {
        // Get all matches
        List<Match> allMatches = matchRepository.findAll();

        // Filter to find only matches with a total score at or above the minimum
        List<Match> highScoringMatches = allMatches.stream()
                .filter(match -> (match.getHomeScore() + match.getAwayScore()) >= minTotalScore)
                .toList();

        return MatchMapper.listToDTO(highScoringMatches);
    }

    /**
     * Find recent matches
     * @param limit the maximum number of matches to return
     * @return list of recent matches
     */
    public List<MatchDTO> findRecentMatches(int limit) {
        // Use current date/time for finding recent matches
        List<Match> matches = matchRepository.findByMatchDateBeforeOrderByMatchDateDesc(LocalDateTime.now());
        // Apply limit if needed
        if (matches.size() > limit) {
            matches = matches.subList(0, limit);
        }
        return MatchMapper.listToDTO(matches);
    }

    /**
     * Find matches won by a team
     * @param teamName the team name
     * @return list of matches won by the team
     */
    public List<MatchDTO> findWinsByTeam(String teamName) {
        Team team = findTeamByName(teamName);

        // Get all matches involving the team
        List<Match> allTeamMatches = matchRepository.findByHomeTeamOrAwayTeam(team, team);

        // Filter to find only matches where the team won
        List<Match> winMatches = allTeamMatches.stream()
                .filter(match -> {
                    Team winner = match.getWinner();
                    return winner != null && winner.equals(team);
                })
                .toList();

        return MatchMapper.listToDTO(winMatches);
    }

    /**
     * Find matches lost by a team
     * @param teamName the team name
     * @return list of matches lost by the team
     */
    public List<MatchDTO> findLossesByTeam(String teamName) {
        Team team = findTeamByName(teamName);

        // Get all matches involving the team
        List<Match> allTeamMatches = matchRepository.findByHomeTeamOrAwayTeam(team, team);

        // Filter to find only matches where the team lost
        List<Match> lossMatches = allTeamMatches.stream()
                .filter(match -> {
                    Team loser = match.getLoser();
                    return loser != null && loser.equals(team);
                })
                .toList();

        return MatchMapper.listToDTO(lossMatches);
    }

    /**
     * Add a goal to a match
     * @param matchId the match ID
     * @param goal the goal to add
     * @return the updated match
     */
    @Transactional
    public Match addGoal(Long matchId, Goal goal) {
        Match match = findById(matchId);

        // Set the match for the goal
        goal.setMatch(match);

        // Save the goal
        goalRepository.save(goal);

        // Update match scores based on the goal
        if (goal.getPlayer().getTeam().equals(match.getHomeTeam())) {
            match = updateScores(matchId, match.getHomeScore() + 1, match.getAwayScore());
        } else if (goal.getPlayer().getTeam().equals(match.getAwayTeam())) {
            match = updateScores(matchId, match.getHomeScore(), match.getAwayScore() + 1);
        }

        return match;
    }

    /**
     * Update match scores
     * @param matchId the match ID
     * @param homeScore the home team score
     * @param awayScore the away team score
     * @return the updated match
     */
    @Transactional
    public Match updateScores(Long matchId, int homeScore, int awayScore) {
        Match match = findById(matchId);

        // Create a new match with updated scores
        Match updatedMatch = new Match(
                match.getHomeTeam(),
                match.getAwayTeam(),
                homeScore,
                awayScore,
                match.getMatchDate()
        );

        return matchRepository.save(updatedMatch);
    }

    /**
     * Get the winner of a match
     * @param matchId the match ID
     * @return the winning team, or null if the match was a draw
     */
    public Team getMatchWinner(Long matchId) {
        Match match = findById(matchId);
        return match.getWinner();
    }

    /**
     * Get the loser of a match
     * @param matchId the match ID
     * @return the losing team, or null if the match was a draw
     */
    public Team getMatchLoser(Long matchId) {
        Match match = findById(matchId);
        return match.getLoser();
    }

    /**
     * Check if a match ended in a draw
     * @param matchId the match ID
     * @return true if the match was a draw, false otherwise
     */
    public boolean isMatchDraw(Long matchId) {
        Match match = findById(matchId);
        return match.isDraw();
    }

    /**
     * Helper method to find a team by name or create it if it doesn't exist.
     * @param teamName the team name
     * @return the team entity
     */
    private Team findOrCreateTeam(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findByName(teamName);
        return optionalTeam.orElseGet(() -> {
            Team team = new Team();
            team.setName(teamName);
            return teamRepository.save(team);
        });
    }

    /**
     * Helper method to find a team by name.
     * @param teamName the team name
     * @return the team entity
     * @throws com.hyperskill.exception.TeamNotFoundException if the team is not found
     */
    private Team findTeamByName(String teamName) {
        return teamRepository.findByName(teamName)
                .orElseThrow(() -> new com.hyperskill.exception.TeamNotFoundException("Team not found with name: " + teamName));
    }
}
