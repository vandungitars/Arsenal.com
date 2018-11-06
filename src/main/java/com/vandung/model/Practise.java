package com.vandung.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="practise")
public class Practise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_practise", nullable = false)
	private Long id_practise;
	
	@Column(name="image")
	private String image;

	public Long getId_practise() {
		return id_practise;
	}

	public void setId_practise(Long id_practise) {
		this.id_practise = id_practise;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
