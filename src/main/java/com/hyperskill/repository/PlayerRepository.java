package com.hyperskill.repository;

import com.hyperskill.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findById(Long id);

    List<Player> findByTeam_Name(String teamName);

    Optional<Player> findByFirstNameAndLastName(String firstName, String lastName);

    List<Player> findAll();

    void deleteById(Long id);
}
