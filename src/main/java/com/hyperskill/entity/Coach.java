package com.hyperskill.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Coach (base abstract class: Person)
 * <p>
 * Attributes: String firstName, String lastName, String team
 * Methods: display/update stats (goals scored, matches played, average goals scored, etc.)
 */
@Entity
public class Coach extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private int playedMatches;

    public Coach() {}

    public Coach(String firstName, String lastName, Team team) {
        super(firstName, lastName);
        this.team = team;
    }

    // Constructor for backward compatibility
    public Coach(String firstName, String lastName, String teamName) {
        super(firstName, lastName);
        // This constructor is kept for backward compatibility
        // The team will be set later using setTeam method
    }

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public String getTeamName() {
        return team != null ? team.getName() : null;
    }

    public int getPlayedMatches() {
        return playedMatches;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setTeamName(String teamName) {
        // This method is kept for backward compatibility
        // It doesn't do anything as we now use Team object
    }

    public void incrementMatchesPlayed() {
        this.playedMatches++;
    }

    @Override
    public String toString() {
        return "Coach{" + "firstName = " + super.getFirstName() +
                ", lastName = " + super.getLastName()
                + ", team=" + (team != null ? team.getName() : "null") + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(id, coach.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
