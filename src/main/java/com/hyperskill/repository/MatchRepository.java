package com.hyperskill.repository;


import com.hyperskill.entity.Match;
import com.hyperskill.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    Optional<Match> findMatchByTeam(String team);
    Optional<Match> findMatchByHomeTeamAndAwayTeam(String homeTeam, String awayTeam);
    Optional<Match> findMatchByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    Optional<Match> findMatchWithHighestScore();
    Optional<Match> findMatchWithSpecificScore(int homeScore, int awayScore);
    Optional<Match> findMatchWithSpecificHomeScore(int homeScore);
    Optional<Match> findMatchWithSpecificAwayScore(int awayScore);
    Optional<Match> findRecentMatch(LocalDateTime startDate);


}

