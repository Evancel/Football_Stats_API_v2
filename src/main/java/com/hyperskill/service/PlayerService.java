package com.hyperskill.service;

import com.hyperskill.exception.PlayerNotFoundException;
import com.hyperskill.exception.TeamNotFoundException;
import com.hyperskill.model.dto.PlayerRequestDTO;
import com.hyperskill.model.dto.PlayerResponseDTO;
import com.hyperskill.model.entity.Player;
import com.hyperskill.model.entity.Team;
import com.hyperskill.model.mapper.PlayerMapper;
import com.hyperskill.repository.PlayerRepository;
import com.hyperskill.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository,
                         TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Player save(PlayerRequestDTO request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Team with id: " + request.getTeamId() + " not found"));
        Player savedPlayed = PlayerMapper.toEntity(request, team);

        return playerRepository.save(savedPlayed);
    }

    @Transactional
    public PlayerResponseDTO updatePlayer(Long playerId, PlayerRequestDTO request) {
        //if the player with such id doesn't exist
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: " + playerId + " not found"));

        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Team with id: " + request.getTeamId() + " not found"));

        // Update player fields
        player.setFirstName(request.getFirstName());
        player.setLastName(request.getLastName());
        player.setTeam(team);
        player = playerRepository.save(player);

        return PlayerMapper.toDTO(player);
    }

    public PlayerResponseDTO findById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: " + id + " not found"));
        return PlayerMapper.toDTO(player);
    }

    public PlayerResponseDTO searchDTOByName(String firstName, String lastName) {
        Player player = playerRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));

        return PlayerMapper.toDTO(player);
    }

    public Page<PlayerResponseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastName").and(Sort.by("firstName")));
        Page<Player> players = playerRepository.findAll(pageable);
        return players.map(PlayerMapper::toDTO);
    }

    public void deleteById(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new PlayerNotFoundException("Player with id: " + id + " not found");
        }
        playerRepository.deleteById(id);
    }
}
