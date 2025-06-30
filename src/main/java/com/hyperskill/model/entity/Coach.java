package com.hyperskill.model.entity;

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

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }


    public void setTeam(Team team) {
        // Remove from old team
        if (this.team != null) {
            this.team.setCoach(null);
        }

        this.team = team;

        // Add to new team
        if (team != null) {
            team.setCoach(this);
        }
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