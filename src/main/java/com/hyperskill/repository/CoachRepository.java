package com.hyperskill.repository;

import com.hyperskill.model.entity.Coach;
import com.hyperskill.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Coach entity
 * Extends JpaRepository to provide CRUD operations
 */
@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    /**
     * Find a coach by team
     * @param team the current team
     * @return list of coaches for the given team
     */
    List<Coach> findByTeam(Team team);

    /**
     * Find a coach by first name and last name
     * @param firstName the first name of the coach
     * @param lastName the last name of the coach
     * @return the coach with the given first name and last name
     */
    Optional<Coach> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Find coaches by first name
     * @param firstName the first name of the coach
     * @return list of coaches with the given first name
     */
    List<Coach> findByFirstName(String firstName);

    /**
     * Find coaches by last name
     * @param lastName the last name of the coach
     * @return list of coaches with the given last name
     */
    List<Coach> findByLastName(String lastName);

}

