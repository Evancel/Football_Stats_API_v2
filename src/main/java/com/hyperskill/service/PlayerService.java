package com.hyperskill.service;

import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;
import com.hyperskill.exception.PlayerAlreadyExistsException;
import com.hyperskill.exception.PlayerNotFoundException;
import com.hyperskill.repository.PlayerRepository;
import com.hyperskill.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Player save(Player player) {
        Optional<Player> existing = playerRepository.findByFirstNameAndLastName(
                player.getFirstName(), player.getLastName());

        if (existing.isPresent()) {
            throw new PlayerAlreadyExistsException(
                    "Player already exists: " + player.getFirstName() + " " + player.getLastName());
        }

        String currTeamName = player.getTeam().getName();

        Team team = findOrCreateTeam(currTeamName);
        player.setTeam(team);

        return playerRepository.save(player);
    }

    @Transactional
    public Player updatePlayer(Long playerId, String firstName, String lastName, String teamName) {
        //if the player with such firstName and lastName already exist
        Optional<Player> existing = playerRepository.findByFirstNameAndLastName(
                firstName, lastName);

        if (existing.isPresent()) {
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

        return playerRepository.save(player);
    }


    public void addAll(List<Player> players) {
        playerRepository.saveAll(players);
    }

    public Player findById(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player not found"));
    }

    public Player searchByName(String firstName, String lastName) {
        return playerRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));
    }

    public List<Player> findPlayers() {
        //TODO add pagination and sorting
        return playerRepository.findAll();
    }

    public void deleteById(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new PlayerNotFoundException("Player with id: " + id + " not found");
        }
        playerRepository.deleteById(id);
    }

    public List<Player> findByTeamName(String teamName) {
        return playerRepository.findByTeam_Name(teamName);
    }

    private Team findOrCreateTeam(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findByName(teamName);
        return optionalTeam.orElseGet(() ->
                teamRepository.save(new Team(teamName)));
    }
}
