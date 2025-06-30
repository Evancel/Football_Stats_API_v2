package com.hyperskill.config;
/*
import com.hyperskill.entity.Coach;
import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;
import com.hyperskill.repository.PlayerRepository;
import com.hyperskill.repository.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public DataSeeder(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) {
        Team city = new Team();
        city.setName("Manchester City");
        city = teamRepository.save(city);

        List<Player> manchesterCityPlayers = List.of(
                new Player("Ederson", "Moraes", city),
                new Player("Ruben", "Dias", city),
                new Player("Joao", "Cancelo", city),
                new Player("Rodri", "Hernandez", city),
                new Player("Kevin", "De Bruyne", city),
                new Player("Phil", "Foden", city),
                new Player("Jack", "Grealish", city),
                new Player("Ilkay", "Gundogan", city),
                new Player("Erling", "Haaland", city),
                new Player("Riyad", "Mahrez", city),
                new Player("Bernardo", "Silva", city)
        );
        playerRepository.saveAll(manchesterCityPlayers);

        Coach manchesterCityCoach = new Coach("Ruben", "Amorim", city);
    }
}

 */

