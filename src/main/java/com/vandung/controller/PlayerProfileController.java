package com.vandung.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vandung.model.Player;
import com.vandung.service.PlayerProfileService;

@Controller
public class PlayerProfileController {

	@Autowired
	private PlayerProfileService playerProfileService;
	
	@RequestMapping(value = "/arsenal/playerprofile", method = RequestMethod.GET)
	public String defaultPage() {
		return "playerprofilePage";
	}
	
	@RequestMapping(value = "/arsenal/playerprofile/{id}", method = RequestMethod.GET)
	public String playerProfileByID(@RequestParam String id) {
		return "aaa";
	}
	
	@RequestMapping(value = "/admin/PlayerProfile", method = RequestMethod.GET)
	public String viewsAdminPlayerProfile() {
		return "adminAddPlayerProfile";
	}
	
	@RequestMapping(value = "/admin/addPlayerProfile", method = RequestMethod.POST)
	public String addPlayerProfile(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(dataJson);
		JsonNode jsonNode;
		Player player = new Player();
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String name_player = jsonNode.get("name_player").asText();
			int squadnumber = jsonNode.get("squadnumber").asInt();
			String born_player = jsonNode.get("born_player").asText();  
			String country_player = jsonNode.get("country_player").asText();
			String image_player = ApiController.nameFile;
			String location_player = jsonNode.get("location_player").asText();
			System.out.println(name_player);
			System.out.println(squadnumber);
			System.out.println(born_player);
			System.out.println(country_player);
			System.out.println(image_player);
			System.out.println(location_player);
			if(name_player != "" && squadnumber != 0 && born_player != null && country_player != "" && image_player != "" && location_player != "" ) {
				player.setName_player(name_player);
				player.setSquadnumber(squadnumber);
				player.setBorn_player(born_player);
				player.setCountry_player(country_player);
				player.setImage_player(image_player);
				player.setLocation_player(location_player);
				playerProfileService.save(player);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "adminAddPlayerProfile";
	}
	
	@RequestMapping(value = "/admin/getListPlayerProfile", method = RequestMethod.GET)
	public String getListPlayerProfile(ModelMap modelMap) {
		List<Player> listPlayer = playerProfileService.findAll();
		listPlayer.forEach((p)->{
			p.setImage_player("/img/core-img/" + p.getImage_player());
		});
		modelMap.addAttribute("listPlayer", listPlayer);
		return "adminUpdatePlayerProfile";
	}
	
	@RequestMapping(value = "/admin/updatePlayerProfile", method = RequestMethod.POST)
	public String updatePlayerProfile(@RequestParam String dataJson, @RequestParam String idPlayer) {
		Long id_player = Long.parseLong(idPlayer);
		ObjectMapper objectMapper = new ObjectMapper();
		Player player = new Player();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String name_player = jsonNode.get("name_player").asText();
			int squadnumber = jsonNode.get("squadnumber").asInt();
			String born_player = jsonNode.get("born_player").asText();  
			String country_player = jsonNode.get("country_player").asText();
			String location_player = jsonNode.get("location_player").asText();
			if(name_player != "" && squadnumber != 0 && born_player != null && country_player != "" && location_player != "" ) {
				player.setName_player(name_player);
				player.setSquadnumber(squadnumber);
				player.setBorn_player(born_player);
				player.setCountry_player(country_player);
				player.setLocation_player(location_player);
				playerProfileService.update(player, id_player);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "adminUpdatePlayerProfile";
	}
	
	@RequestMapping(value = "/admin/deletePlayerProfile", method = RequestMethod.POST)
	public String deletePlayerProfile(@RequestParam String idPlayer) {
		Long id_player = Long.parseLong(idPlayer);
		playerProfileService.deleteById(id_player);
		return "adminUpdatePlayerProfile";
	}
}
