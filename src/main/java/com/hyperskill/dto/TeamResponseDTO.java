package com.hyperskill.dto;

import java.util.List;
import java.util.Set;

public class TeamResponseDTO {
    private Long id;
    private String name;
    private List<PlayerDTO> players;
    private CoachDTO coach;
    private int goalScored;
    private Set<MatchDTO> matches;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoalScored() {
        return goalScored;
    }

    public void setGoalScored(int goalScored) {
        this.goalScored = goalScored;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public CoachDTO getCoach() {
        return coach;
    }

    public void setCoach(CoachDTO coach) {
        this.coach = coach;
    }

    public Set<MatchDTO> getMatches() {
        return matches;
    }

    public void setMatches(Set<MatchDTO> matches) {
        this.matches = matches;
    }
}
