package com.hyperskill.model.dto;

public class PlayerStatisticsResponseDTO {
    private Long playerId;
    private String fullName;
    private int totalGoals;
    private int totalMatches;
    private double avgGoalsPerMatch;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(int totalGoals) {
        this.totalGoals = totalGoals;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public double getAvgGoalsPerMatch() {
        return avgGoalsPerMatch;
    }

    public void setAvgGoalsPerMatch(double avgGoalsPerMatch) {
        this.avgGoalsPerMatch = avgGoalsPerMatch;
    }
}
