package com.hyperskill.repository;

import com.hyperskill.model.entity.PlayerMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, Long> {
}
