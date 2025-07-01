package com.hyperskill.service;

import com.hyperskill.exception.MatchNotFoundException;
import com.hyperskill.exception.PlayerMatchNotFoundException;
import com.hyperskill.exception.PlayerNotFoundException;
import com.hyperskill.model.dto.PlayerMatchRequestDTO;
import com.hyperskill.model.dto.PlayerMatchResponseDTO;
import com.hyperskill.model.entity.Match;
import com.hyperskill.model.entity.Player;
import com.hyperskill.model.entity.PlayerMatch;
import com.hyperskill.model.mapper.PlayerMatchMapper;
import com.hyperskill.repository.MatchRepository;
import com.hyperskill.repository.PlayerMatchRepository;
import com.hyperskill.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerMatchService {
    private final PlayerMatchRepository playerMatchRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    public PlayerMatchService(PlayerMatchRepository playerMatchRepository,
                              PlayerRepository playerRepository,
                              MatchRepository matchRepository) {
        this.playerMatchRepository = playerMatchRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    @Transactional
    public void registerPlayersForMatch(Long matchId, List<Long> playerIds) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match with id " + matchId + " not found"));

        List<PlayerMatch> participationList = new ArrayList<>();
        for (Long playerId : playerIds) {
            Player player = playerRepository.findById(playerId)
                    .orElseThrow(() -> new IllegalArgumentException("Player with id " +playerId +  " not found"));
            PlayerMatch playerMatch = new PlayerMatch();
            playerMatch.setPlayer(player);
            playerMatch.setMatch(match);
            participationList.add(playerMatch);
        }

        playerMatchRepository.saveAll(participationList);
    }

    public PlayerMatchResponseDTO getById(Long id) {
        PlayerMatch pm = playerMatchRepository.findById(id)
                .orElseThrow(() -> new PlayerMatchNotFoundException("PlayerMatch not found"));
        return PlayerMatchMapper.toDTO(pm);
    }

    public List<PlayerMatchResponseDTO> getAll() {
        return playerMatchRepository
                .findAll()
                .stream().map(PlayerMatchMapper::toDTO)
                .toList();
    }

    public PlayerMatchResponseDTO update(Long id, PlayerMatchRequestDTO dto) {
        PlayerMatch pm = playerMatchRepository.findById(id)
                .orElseThrow(() -> new PlayerMatchNotFoundException("PlayerMatch not found"));

        Player player = playerRepository.findById(dto.getPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));
        Match match = matchRepository.findById(dto.getMatchId())
                .orElseThrow(() -> new MatchNotFoundException("Match not found"));

        pm.setPlayer(player);
        pm.setMatch(match);

        return PlayerMatchMapper.toDTO(playerMatchRepository.save(pm));
    }

    public void delete(Long id) {
        playerMatchRepository.deleteById(id);
    }
}
