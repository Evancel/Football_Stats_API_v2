package com.hyperskill.statistics;

import com.hyperskill.dto.PlayerDTO;
import com.hyperskill.entity.Player;
import com.hyperskill.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerStatisticsService {
    private final PlayerService playerService;
    public PlayerStatisticsService(PlayerService playerService){
        this.playerService = playerService;
    }

    public List<PlayerDTO> getTopPlayersByGoalsScored(int n) {
        List<Player> players = playerService.findPlayers();
        players.sort((p1, p2) -> Integer.compare(p2.getGoals(), p1.getGoals()));
        players = getNTopPlayersFromList(players, n);
        List<PlayerDTO> dtos = new ArrayList<>();
        return dtos;
    }

    private List<Player> getNTopPlayersFromList(List<Player> players, int n) {
        List<Player> topPlayers = new ArrayList<>();
        for (int i = 0; i < Math.min(n, players.size()); i++) {
            topPlayers.add(players.get(i));
        }
        return topPlayers;
    }
}
