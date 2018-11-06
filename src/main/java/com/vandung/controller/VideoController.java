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
import com.vandung.model.Video;
import com.vandung.service.VideoService;

@Controller
public class VideoController {
	
	@Autowired
	private VideoService videoService;

	@RequestMapping(value = "/arsenal/videopage", method = RequestMethod.GET)
	public String defaultPage() {
		return "videopage";
	}
	
	@RequestMapping(value = "/admin/Video", method = RequestMethod.GET)
	public String viewAdminVideo() {
		return "adminAddVideo";
	}
	
	@RequestMapping(value="/admin/addVideo", method = RequestMethod.POST)
	public String addVideo(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		Video video = new Video();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String title_video = jsonNode.get("title_video").asText();
			if(title_video != "") {
				video.setLink_video(ApiController.nameFile);
				video.setTitle_video(title_video);
				videoService.save(video);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "adminAddVideo";
	}
	
	@RequestMapping(value = "/admin/getListVideo", method = RequestMethod.GET)
	public String getListVideo(ModelMap modelMap) {
		List<Video> listVideo = videoService.findAll();
		listVideo.forEach((n)->{
			n.setLink_video("/img/core-img/" + n.getLink_video());
		});
		modelMap.addAttribute("listVideo", listVideo);
		return "adminUpdateVideo";
	}
	
	@RequestMapping(value = "/admin/updateVideo", method = RequestMethod.POST)
	public String updateVideo(@RequestParam String dataJson, @RequestParam String idVideo) {
		Long id_video = Long.parseLong(idVideo);
		ObjectMapper objectMapper = new ObjectMapper();
		Video video = new Video();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String title_video = jsonNode.get("title_videoChange").asText();
			if(title_video != "") {
				video.setLink_video(ApiController.nameFile);
				video.setTitle_video(title_video);
				videoService.update(video, id_video);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "adminUpdateVideo";
	}
	
	@RequestMapping(value = "/admin/deleteVideo", method = RequestMethod.POST)
	public String deleteVideo(@RequestParam String idVideo) {
		Long id_video = Long.parseLong(idVideo);
		videoService.deleteById(id_video);
		return "adminUpdateVideo";
	}
	
}
