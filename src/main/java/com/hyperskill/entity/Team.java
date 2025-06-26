package com.hyperskill.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.*;

/**
 * Team
 * <p>
 * Attributes: String name, List players, Coach coach
 * Methods: display/update stats (percentage of wins/losses/draws by year
 * /total, average/total goal score by year, total wins/losses/draws, etc.)
 */
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Coach coach;

    // This is a transient field that will be populated programmatically
    // It includes both matches where the team is homeTeam and awayTeam
    @Transient
    private Set<Match> allMatches = new HashSet<>();
    private int goalScored;

    public Team() {}

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, Coach coach, List<Player> playersTeam) {
        this.name = name;
        this.coach = coach;
        this.players = playersTeam;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            return;
        }
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Set<Match> getAllMatches() {
        return allMatches;
    }

    public void setAllMatches(Set<Match> allMatches) {
        this.allMatches = allMatches;
    }

    public int getGoalScored() {
        return goalScored;
    }

    public void setGoalScored(int goalScored) {
        this.goalScored = goalScored;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
