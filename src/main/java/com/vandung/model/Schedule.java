package com.vandung.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_schedule", nullable = false)
	private Long id_schedule;
	
	@Column(name = "team_home")
	private String team_home;
	
	@Column(name = "team_customer")
	private String team_customer;
	
	@Column(name = "time")
	private String time;

	public Long getId_schedule() {
		return id_schedule;
	}

	public void setId_schedule(Long id_schedule) {
		this.id_schedule = id_schedule;
	}

	public String getTeam_home() {
		return team_home;
	}

	public void setTeam_home(String team_home) {
		this.team_home = team_home;
	}

	public String getTeam_customer() {
		return team_customer;
	}

	public void setTeam_customer(String team_customer) {
		this.team_customer = team_customer;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
