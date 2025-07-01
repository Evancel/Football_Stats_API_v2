package com.hyperskill.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PlayerRequestDTO {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 30)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull(message = "Team id is required")
    private Long teamId;

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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
