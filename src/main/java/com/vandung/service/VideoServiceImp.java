package com.vandung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.model.Video;
import com.vandung.repository.VideoRepository;

@Service("VideoService")
@Transactional
public class VideoServiceImp implements VideoService{

	@Autowired
	private VideoRepository videoRepository;
	
	@Override
	public Video findById(Long id_video) {
		return videoRepository.getOne(id_video);
	}

	@Override
	public List<Video> findAll() {
		return videoRepository.findAll();
	}

	@Override
	public void save(Video video) {
		videoRepository.save(video);
	}

	@Override
	public void update(Video video, Long id_video) {
		Video videoDB = videoRepository.getOne(id_video);
		videoDB.setLink_video(video.getLink_video());
		videoDB.setTitle_video(video.getTitle_video());
		videoRepository.save(videoDB);
	}

	@Override
	public void deleteById(Long id_video) {
		videoRepository.deleteById(id_video);
	}

}
