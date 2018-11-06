package com.vandung.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_player", nullable = false)
	private Long id_player;
	
	@Column(name = "name_player")
	private String name_player;
	
	@Column(name = "squadnumber")
	private int squadnumber;
	
	@Column(name = "born_player")
	private Date born_player;
	
	@Column(name = "country_player")
	private String country_player;
	
	@Column(name = "image_player")
	private String image_player;
	
	@Column(name = "location_player")
	private String location_player;

	public Long getId_player() {
		return id_player;
	}

	public void setId_player(Long id_player) {
		this.id_player = id_player;
	}

	public String getName_player() {
		return name_player;
	}

	public void setName_player(String name_player) {
		this.name_player = name_player;
	}

	public int getSquadnumber() {
		return squadnumber;
	}

	public void setSquadnumber(int squadnumber) {
		this.squadnumber = squadnumber;
	}

	public Date getBorn_player() {
		return born_player;
	}

	public void setBorn_player(Date born_player) {
		this.born_player = born_player;
	}

	public String getCountry_player() {
		return country_player;
	}

	public void setCountry_player(String country_player) {
		this.country_player = country_player;
	}

	public String getImage_player() {
		return image_player;
	}

	public void setImage_player(String image_player) {
		this.image_player = image_player;
	}

	public String getLocation_player() {
		return location_player;
	}

	public void setLocation_player(String location_player) {
		this.location_player = location_player;
	}
}
