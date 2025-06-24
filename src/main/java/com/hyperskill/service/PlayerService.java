package com.hyperskill.service;

import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;
import com.hyperskill.repository.PlayerRepository;
import com.hyperskill.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository){
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Player save(Player player) {
        String currTeamName = player.getTeam().getName();

        Team team = findOrCreateTeam(currTeamName);
        player.setTeam(team);

        return playerRepository.save(player);
    }

    @Transactional
    public Player updatePlayer(Long playerId, String firstName, String lastName, String teamName) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        // Update player fields
        player.setFirstName(firstName);
        player.setLastName(lastName);

        // Find or create team
        Team team = findOrCreateTeam(teamName);
        player.setTeam(team);

        return playerRepository.save(player);
    }


    public void addAll(List<Player> players){
        playerRepository.saveAll(players);
    }

    public Optional<Player> findById(Long id){
        return playerRepository.findById(id);
    }

    public List<Player> searchByName(String firstName, String lastName){
        return playerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Player> findPlayers(){
        //TODO add pagination and sorting
        return playerRepository.findAll();
    }

    public boolean deleteById(Long id){
        if (!playerRepository.existsById(id)) {
            return false;
        }
        playerRepository.deleteById(id);
        return true;
    }

    public List<Player> findByTeamName(String teamName){
        return playerRepository.findByTeam_Name(teamName);
    }

    private Team findOrCreateTeam(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findByName(teamName);
        return optionalTeam.orElseGet(() ->
                teamRepository.save(new Team(teamName)));
    }
}
