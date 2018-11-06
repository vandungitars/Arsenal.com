package com.vandung.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "video")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_video", nullable = false)
	private Long id_video;
	
	@Column(name = "link_video")
	private String link_video;
	
	@Column(name = "title_video")
	private String title_video;

	public Long getId_video() {
		return id_video;
	}

	public void setId_video(Long id_video) {
		this.id_video = id_video;
	}

	public String getLink_video() {
		return link_video;
	}

	public void setLink_video(String link_video) {
		this.link_video = link_video;
	}

	public String getTitle_video() {
		return title_video;
	}

	public void setTitle_video(String title_video) {
		this.title_video = title_video;
	}
}
