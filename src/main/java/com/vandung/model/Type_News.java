package com.vandung.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="type_news")
public class Type_News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_type", nullable=false)
	private Long id_type;
	
	@Column(name="types", nullable = true)
	private String types;
	
	public Long getId_type() {
		return id_type;
	}

	public void setId_type(Long id_type) {
		this.id_type = id_type;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
}
