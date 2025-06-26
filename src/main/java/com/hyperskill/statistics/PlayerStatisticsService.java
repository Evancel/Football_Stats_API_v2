package com.hyperskill.statistics;

import com.hyperskill.entity.Match;
import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;
import com.hyperskill.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatisticsService {
    private final PlayerService playerService;

    public PlayerStatisticsService(PlayerService playerService){
        this.playerService = playerService;
    }

    public List<Player> getTopPlayersByGoalsScored(int n) {
        List<Player> players = playerService.findPlayers();
        players.sort((p1, p2) -> Integer.compare(p2.getGoals(), p1.getGoals()));
        return getNTopPlayersFromList(players, n);
    }

    public List<Player> getTopPlayersByMatchesPlayed(int n) {
        var players = playerService.findPlayers();
        players.sort((p1, p2) -> Integer.compare(p2.getPlayedMatches(), p1.getPlayedMatches()));
        return getNTopPlayersFromList(players, n);
    }

    public int goalsScoredPerYear(Player player, int year) {
        int amountGoals = 0;
        Team currTeam = player.getTeam();
        if (currTeam == null) {
            return 0;
        }
        for (Match match : currTeam.getAllMatches()) {
            if (match.getMatchDate().getYear() == year) {
                for (Goal goal : match.getGoals()) {
                    if (goal.getPlayer().equals(player)) {
                        amountGoals++;
                    }
                }
            }
        }
        return amountGoals;
    }

    private List<Player> getNTopPlayersFromList(List<Player> players, int n) {
        List<Player> topPlayers = new ArrayList<>();
        for (int i = 0; i < Math.min(n, players.size()); i++) {
            topPlayers.add(players.get(i));
        }
        return topPlayers;
    }
}
