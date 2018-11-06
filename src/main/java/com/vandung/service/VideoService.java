package com.vandung.service;

import java.util.List;

import com.vandung.model.Video;

public interface VideoService {

	Video findById(Long id_video);
	List<Video> findAll();
	void save(Video video);
	void update(Video video, Long id_video);
	void deleteById(Long id_video);
}
