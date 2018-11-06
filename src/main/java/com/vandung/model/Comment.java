package com.vandung.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comment", nullable = false)
	private Long id_comment;
	
	@Column(name = "email_comment")
	private String email_comment;
	
	@Column(name = "content_comment")
	private String content_comment;
	
	public Long getId_comment() {
		return id_comment;
	}

	public void setId_comment(Long id_comment) {
		this.id_comment = id_comment;
	}

	public String getEmail_comment() {
		return email_comment;
	}

	public void setEmail_comment(String email_comment) {
		this.email_comment = email_comment;
	}

	public String getContent_comment() {
		return content_comment;
	}

	public void setContent_comment(String content_comment) {
		this.content_comment = content_comment;
	}
}
