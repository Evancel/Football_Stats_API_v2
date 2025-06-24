package com.hyperskill.repository;

import com.hyperskill.entity.Player;
import com.hyperskill.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAll();
    Optional<Team> findByName(String name);
    boolean existsByName(String name);
}
