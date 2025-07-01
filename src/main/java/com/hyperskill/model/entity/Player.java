package com.hyperskill.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Player (base abstract class: Person)
 * <p>
 * Attributes: String firstName, String lastName, String team
 * Methods: display/update stats (goals scored, matches played, average goals scored, etc.) by year and total, etc.
 */
@Entity
public class Player extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // Prevent circular references in JSON
    private List<Goal> goals = new ArrayList<>();
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerMatch> playerMatches = new ArrayList<>();

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public String getLastName() {
        return super.getLastName();
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    @Override
    public String toString() {
        return "Player {" + "firstName = " + super.getFirstName()
                + ", lastName = " + super.getLastName()
                + ", team = " + team.getName()
                + ", goals = " + goals.size()
                + ", matches = " + playerMatches.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
