package com.hyperskill.repository;

import com.hyperskill.entity.Goal;
import com.hyperskill.entity.Match;
import com.hyperskill.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Goal entity
 */
@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    /**
     * Find all goals scored in a match
     * @param match the match
     * @return list of goals
     */
    List<Goal> findGoalsByMatch(Match match);

    /**
     * Find all goals scored by a player
     * @param player the player
     * @return list of goals
     */
    List<Goal> findGoalsByPlayer(Player player);

    /**
     * Find all goals scored by a player in a match
     * @param match the match
     * @param player the player
     * @return list of goals
     */
    List<Goal> findGoalsByMatchAndPlayer(Match match, Player player);

    /**
     * Count the number of goals scored in a match
     * @param match the match
     * @return the number of goals
     */
    long countGoalsByMatch(Match match);

    /**
     * Count the number of goals scored by a player
     * @param player the player
     * @return the number of goals
     */
    long countGoalsByPlayer(Player player);
}