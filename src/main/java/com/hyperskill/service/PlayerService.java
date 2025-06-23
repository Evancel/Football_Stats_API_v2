package com.hyperskill.service;

import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;
import com.hyperskill.repository.PlayerRepository;
import com.hyperskill.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository){
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public Player add(Player player){
        List<Team> teams = teamRepository.findAll();
        if(!teams.contains(player.getTeam())){
            //TODO add exception
            return null;
        }

        playerRepository.save(player);
        return player;
    }

    public void addAll(List<Player> players){
        playerRepository.saveAll(players);
    }

    public Optional<Player> findById(Long id){
        //TODO add exception
        return playerRepository.findById(id);
    }

    public Player findByFullName(String firstName, String lastName){
        //TODO add exception
        return playerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Player> findPlayers(){
        //TODO add pagination and sorting
        return playerRepository.findAll();
    }
/*
    public Collection<Player> findPlayersByTeam(Team team){
        return team.getPlayers();
    }

 */

    public Player deleteById(Long id){
        if(!playerRepository.existsById(id)){
            //TODO add exception
            return null;
        }

        Optional<Player> player = playerRepository.findById(id);
        playerRepository.deleteById(id);
        return player.get();
    }
}
