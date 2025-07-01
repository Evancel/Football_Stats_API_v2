package com.hyperskill.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    private int homeScore;
    private int awayScore;

    private LocalDateTime matchDate;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> goals = new ArrayList<>();

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerMatch> playerMatches = new ArrayList<>();

    public Match() {}

    public Match(Team homeTeam, Team awayTeam, LocalDateTime matchDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.goals = new ArrayList<>();
        this.matchDate = matchDate;
    }

    public Match(Team homeTeam, Team awayTeam, int homeScore, int awayScore, LocalDateTime matchDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.matchDate = matchDate;
        this.goals = new ArrayList<>();
    }

    // New constructor that takes a list of goals
    public Match(Team homeTeam, Team awayTeam, int homeScore, int awayScore, List<Goal> goals, LocalDateTime matchDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.goals = new ArrayList<>(goals);
        this.matchDate = matchDate;
    }

    public Long getId() {
        return id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public boolean isDraw() {
        return homeScore == awayScore;
    }

    public Team getWinner() {
        if (isDraw()) {
            return null;
        }
        return homeScore > awayScore ? homeTeam : awayTeam;
    }

    public Team getLoser() {
        if (isDraw()) {
            return null;
        }
        return homeScore < awayScore ? homeTeam : awayTeam;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public List<PlayerMatch> getPlayerMatches() {
        return playerMatches;
    }

    public void setPlayerMatches(List<PlayerMatch> playerMatches) {
        this.playerMatches = playerMatches;
    }

    /*
    public void updateStats() {
        // Update team statistics
        updateTeamStats();

        // Update player statistics
        updatePlayerStats();

        // Update coach statistics
        updateCoachStats();
    }

    private void updateTeamStats() {// Add this match to each team's match collection
        homeTeam.addMatch(this);
        awayTeam.addMatch(this);

        homeTeam.addGoals(homeScore);
        awayTeam.addGoals(awayScore);

    }

    private void updatePlayerStats() {
        for (Player player : homeTeam.getPlayers()) {
            player.incrementMatchesPlayed();
        }

        for (Player player : awayTeam.getPlayers()) {
            player.incrementMatchesPlayed();
        }

        // Count goals per player from the goals list
        for (Goal goal : goals) {
            Player player = goal.getPlayer();
            player.addGoals(1); // Each goal object represents one goal
        }
    }

    private void updateCoachStats() {
        Coach homeCoach = homeTeam.getCoach();
        Coach awayCoach = awayTeam.getCoach();

        if (homeCoach != null) {
            homeCoach.incrementMatchesPlayed();
        }

        if (awayCoach != null) {
            awayCoach.incrementMatchesPlayed();
        }

    }

    public String getMatchSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Match Date: ").append(matchDate).append("\n");
        summary.append(String.format("%s %d - %d %s\n",
                homeTeam.getName(), homeScore, awayScore, awayTeam.getName()));
        summary.append("Goal Scorers:\n");

        // Group goals by player to count how many each player scored
        java.util.Map<Player, Integer> goalsByPlayer = new java.util.HashMap<>();
        for (Goal goal : goals) {
            Player player = goal.getPlayer();
            goalsByPlayer.put(player, goalsByPlayer.getOrDefault(player, 0) + 1);
        }

        for (java.util.Map.Entry<Player, Integer> entry : goalsByPlayer.entrySet()) {
            summary.append(String.format("- %s %s: %d goals\n",
                    entry.getKey().getFirstName(),
                    entry.getKey().getLastName(),
                    entry.getValue()));
        }

        return summary.toString();
    }


    // Helper method to add a goal
    public void addGoal(Goal goal) {
        goals.add(goal);
        goal.setMatch(this);
    }

 */

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    @Override
    public String toString() {
        return "Match{" +
                "homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", homeScore=" + homeScore +
                ", awayScore=" + awayScore +
                ", matchDate=" + matchDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id);
    }
}
