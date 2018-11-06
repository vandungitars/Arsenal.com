package com.vandung.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_news", nullable = false)
	private Long id_news;
	
	@Column(name = "title_news")
	private String title_news;
	
	@Column(name = "timeupdate_news")
	private String timeupdate_news;
	
	@Column(name = "description_news")
	private String description_news;
	
	@Column(name = "content_news")
	private String content_news;
	
	@Column(name = "image_news")
	private String image_news;
	
	@Column(name = "description_image")
	private String description_image;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
	private Type_News type_news;
	
	public Long getId_news() {
		return id_news;
	}

	public void setId_news(Long id_news) {
		this.id_news = id_news;
	}

	public String getTimeupdate_news() {
		return timeupdate_news;
	}

	public void setTimeupdate_news(String timeupdate_news) {
		this.timeupdate_news = timeupdate_news;
	}

	public String getDescription_news() {
		return description_news;
	}

	public void setDescription_news(String description_news) {
		this.description_news = description_news;
	}

	public String getContent_news() {
		return content_news;
	}

	public void setContent_news(String content_news) {
		this.content_news = content_news;
	}

	public String getTitle_news() {
		return title_news;
	}

	public void setTitle_news(String title_news) {
		this.title_news = title_news;
	}

	public String getImage_news() {
		return image_news;
	}

	public void setImage_news(String image_news) {
		this.image_news = image_news;
	}

	public String getDescription_image() {
		return description_image;
	}

	public void setDescription_image(String description_image) {
		this.description_image = description_image;
	}

	public Type_News getType() {
		return type_news;
	}

	public void setType(Type_News type) {
		this.type_news = type;
	}
}
