package com.vandung.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vandung.model.Player;
import com.vandung.repository.PlayerProfileRepository;

@Service("PlayerProfileService")
@Transactional
public class PlayerProfileServiceImp implements PlayerProfileService{

	@Autowired
	private PlayerProfileRepository playerProfileRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Player findByID(Long idPlayer) {
		return playerProfileRepository.getOne(idPlayer);
	}

	@Override
	public Player findByName(String namePlayer) {
		TypedQuery<Player> query = entityManager.createQuery("FROM Player WHERE name_player=" + namePlayer, Player.class);
		return query.getSingleResult();
	}
	
	@Override
	public List<Player> findAll() {
		playerProfileRepository.findAll().sort((p1, p2)->{
			if(p1.getName_player().compareTo(p2.getName_player()) > 0)
				return 1;
			else if(p1.getName_player().compareTo(p2.getName_player()) < 0)
				return -1;
			else {
				return p1.getSquadnumber() - p2.getSquadnumber();
			}
		});
		return playerProfileRepository.findAll();
	}

	@Override
	public void save(Player player) {
		playerProfileRepository.save(player);
	}

	@Override
	public void update(Player player, Long id_player) {
		Player playerDB = playerProfileRepository.getOne(id_player);
		playerDB.setName_player(player.getName_player());
		playerDB.setBorn_player(player.getBorn_player());
		playerDB.setSquadnumber(player.getSquadnumber());
		playerDB.setCountry_player(player.getCountry_player());
		playerProfileRepository.save(playerDB);
	}

	@Override
	public void deleteById(Long idPlayer) {
		playerProfileRepository.deleteById(idPlayer);
	}

}
