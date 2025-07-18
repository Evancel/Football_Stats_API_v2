package com.hyperskill.model.dto;

public class PlayerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String teamName;
    private int goals;
    private int playerMatches;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getPlayerMatches() {
        return playerMatches;
    }

    public void setPlayerMatches(int playerMatches) {
        this.playerMatches = playerMatches;
    }
}
