package com.hyperskill.service;

import com.hyperskill.dto.PlayerDTO;
import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;
import com.hyperskill.exception.PlayerAlreadyExistsException;
import com.hyperskill.exception.PlayerNotFoundException;
import com.hyperskill.mapper.PlayerMapper;
import com.hyperskill.repository.PlayerRepository;
import com.hyperskill.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Player save(PlayerDTO playerDto) {
        Optional<Player> playerAlreadyExists = playerRepository.findByFirstNameAndLastName(
                playerDto.getFirstName(), playerDto.getLastName());

        if (playerAlreadyExists.isPresent()) {
            throw new PlayerAlreadyExistsException(
                    "Player already exists: " + playerDto.getFirstName() + " " + playerDto.getLastName());
        }

        String currTeamName = playerDto.getTeamName();
        Team team = findOrCreateTeam(currTeamName);
        Player playerToSave = PlayerMapper.toEntity(playerDto);
        playerToSave.setTeam(team);

        return playerRepository.save(playerToSave);
    }

    @Transactional
    public PlayerDTO updatePlayer(Long playerId, String firstName, String lastName, String teamName) {
        //if the player with such firstName and lastName already exist
        Optional<Player> playerAlreadyExists = playerRepository.findByFirstNameAndLastName(
                firstName, lastName);

        if (playerAlreadyExists.isPresent()) {
            throw new PlayerAlreadyExistsException(
                    "Player already exists: " + firstName + " " + lastName);
        }

        //if the player with such id doesn't exist
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));

        // Update player fields
        player.setFirstName(firstName);
        player.setLastName(lastName);

        // Find or create team
        Team team = findOrCreateTeam(teamName);
        player.setTeam(team);
        player = playerRepository.save(player);

        return PlayerMapper.toDTO(player);
    }

    public Player findById(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player not found"));
    }

    public PlayerDTO findDTOById(Long id) {
        Player player = findById(id);
        return PlayerMapper.toDTO(player);
    }

    public PlayerDTO searchDTOByName(String firstName, String lastName) {
        Player player = playerRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));

        return PlayerMapper.toDTO(player);
    }

    public List<PlayerDTO> findDTOs() {
        //TODO add pagination and sorting
        List<Player> players = playerRepository.findAll();
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for (Player player : players) {
            playerDTOS.add(PlayerMapper.toDTO(player));
        }
        return playerDTOS;
    }

    public void deleteById(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new PlayerNotFoundException("Player with id: " + id + " not found");
        }
        playerRepository.deleteById(id);
    }

    public List<PlayerDTO> findDTOsByTeamName(String teamName) {
        List<Player> players = playerRepository.findByTeam_Name(teamName);
        List<PlayerDTO> dtos = new ArrayList<>();
        for (Player player : players) {
            dtos.add(PlayerMapper.toDTO(player));
        }
        return dtos;
    }

    private Team findOrCreateTeam(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findByName(teamName);
        return optionalTeam.orElseGet(() ->
                teamRepository.save(new Team(teamName)));
    }
}
