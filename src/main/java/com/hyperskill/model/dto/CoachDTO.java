package com.hyperskill.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CoachDTO {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 30)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 30)
    private String lastName;

    @NotBlank(message = "Team name is required")
    @Size(min = 3, max = 100)
    private String teamName;

    private int playedMatches;


    public CoachDTO() {
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

    public int getPlayedMatches() {
        return playedMatches;
    }

    public void setPlayedMatches(int playedMatches) {
        this.playedMatches = playedMatches;
    }

}