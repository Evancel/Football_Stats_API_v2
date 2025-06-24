package com.hyperskill.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TeamRequestDTO {
    @NotBlank
    @Size(min = 3, max = 100, message = "Name should be less than 100 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
