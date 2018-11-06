package com.vandung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vandung.model.Player;

@Repository
public interface PlayerProfileRepository extends JpaRepository<Player, Long> {

}
