package com.hyperskill.service;

import com.hyperskill.exception.PlayerNotFoundException;
import com.hyperskill.model.dto.PlayerStatisticsResponseDTO;
import com.hyperskill.model.dto.PlayerStatsDTO;
import com.hyperskill.model.entity.Player;
import com.hyperskill.repository.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PlayerStatisticsService {
    private final PlayerRepository playerRepository;

    public PlayerStatisticsService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerStatisticsResponseDTO getStatisticsForPlayer(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id = " + playerId + " not found"));

        int goals = player.getGoals().size();
        int matches = player.getPlayerMatches().size();
        double avg = matches == 0 ? 0.0 : (double) goals / matches;

        PlayerStatisticsResponseDTO dto = new PlayerStatisticsResponseDTO();
        dto.setPlayerId(player.getId());
        dto.setFullName(player.getFirstName() + " " + player.getLastName());
        dto.setTotalGoals(goals);
        dto.setTotalMatches(matches);
        dto.setAvgGoalsPerMatch(avg);

        return dto;
    }

    public Page<PlayerStatsDTO> getTopStats(String metric, Integer year, int page, int size, String sortDir) {
        List<Player> players = playerRepository.findAll();
        List<PlayerStatsDTO> result = new ArrayList<>();

        for (Player player : players) {
            int value = switch (metric) {
                case "goals" -> (year == null) ?
                        player.getGoals().size() :
                        (int) player.getGoals().stream()
                                .filter(goal -> goal.getMatch().getMatchDate().getYear() == year)
                                .count();
                case "matches" -> (year == null) ?
                        player.getPlayerMatches().size() :
                        (int) player.getPlayerMatches().stream()
                                .filter(pm -> pm.getMatch().getMatchDate().getYear() == year)
                                .count();
                case "avgGoals" -> {
                    long goals = (year == null) ? player.getGoals().size() :
                            player.getGoals().stream()
                                    .filter(goal -> goal.getMatch().getMatchDate().getYear() == year)
                                    .count();
                    long matches = (year == null) ? player.getPlayerMatches().size() :
                            player.getPlayerMatches().stream()
                                    .filter(pm -> pm.getMatch().getMatchDate().getYear() == year)
                                    .count();
                    yield (matches == 0) ? 0 : (int) ((double) goals / matches * 100); // preserve precision
                }
                default -> throw new IllegalArgumentException("Invalid metric: " + metric);
            };

            if (value > 0) {
                PlayerStatsDTO dto = new PlayerStatsDTO();
                dto.setPlayerId(player.getId());
                dto.setFullName(player.getLastName() + " " + player.getFirstName());
                dto.setValue(value);

                result.add(dto);
            }
        }

        Comparator<PlayerStatsDTO> comparator = Comparator.comparing(PlayerStatsDTO::getValue);
        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }
        comparator = comparator.thenComparing(dto -> dto.getFullName().toLowerCase());

        result.sort(comparator);

        int start = Math.min(page * size, result.size());
        int end = Math.min(start + size, result.size());
        List<PlayerStatsDTO> pageContent = result.subList(start, end);

        return new PageImpl<>(pageContent, PageRequest.of(page, size), result.size());
    }
}