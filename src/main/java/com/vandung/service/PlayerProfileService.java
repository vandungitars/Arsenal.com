package com.vandung.service;

import java.util.List;

import com.vandung.model.Player;

public interface PlayerProfileService {

	Player findByID(Long id_player);
	Player findByName(String name_player);
	List<Player> findAll();
	void save(Player player);
	void update(Player player, Long id_player);
	void deleteById(Long id_player);
}
