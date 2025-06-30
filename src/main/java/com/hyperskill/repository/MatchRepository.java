package com.hyperskill.repository;

import com.hyperskill.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    // Find matches by team (both home and away)
    List<Match> findByHomeTeam(Team team);
    List<Match> findByAwayTeam(Team team);
    List<Match> findByHomeTeamOrAwayTeam(Team homeTeam, Team awayTeam);

    // Find matches by date range
    List<Match> findByMatchDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find matches between specific teams
    List<Match> findByHomeTeamAndAwayTeam(Team homeTeam, Team awayTeam);

    // Find matches with specific score criteria
    List<Match> findByHomeScoreAndAwayScore(int homeScore, int awayScore);

    // Find recent matches for statistics
    List<Match> findByMatchDateAfterOrderByMatchDateDesc(LocalDateTime date);
    List<Match> findByMatchDateBeforeOrderByMatchDateDesc(LocalDateTime date);

    // Find matches by team name
    List<Match> findByHomeTeam_NameOrAwayTeam_Name(String homeTeamName, String awayTeamName);

}
