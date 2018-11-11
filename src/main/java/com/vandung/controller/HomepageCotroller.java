package com.vandung.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vandung.model.News;
import com.vandung.model.Practise;
import com.vandung.model.Schedule;
import com.vandung.service.NewsService;
import com.vandung.service.PractiseService;
import com.vandung.service.ScheduleService;

@Controller
public class HomepageCotroller {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private PractiseService practiseService;

	@RequestMapping(value = "/arsenal", method = RequestMethod.GET)
	public String defaultPage(ModelMap modelMap) {
		List<News> listNews1 = newsService.getListNewsByType(Long.parseLong("1"),6);
		listNews1.forEach((n)->{
			if(!n.getImage_news().contains("/img/core-img/")) {
				n.setImage_news("/img/core-img/" + n.getImage_news());
			}
		});
		listNews1.sort((p1,p2)->{
			if(p1.getTimeupdate_news().compareTo(LocalDateTime.now().toString()) >0) {
				return 1;
			}
			else {
				return -1;
			}
		});
		List<News> listNews2 = newsService.getListNewsByType(Long.parseLong("2"),5);
		listNews2.forEach((n)->{
			if(!n.getImage_news().contains("/img/core-img/")) {
				n.setImage_news("/img/core-img/" + n.getImage_news());
			}
		});
		listNews2.sort((p1,p2)->{
			if(p1.getTimeupdate_news().compareTo(LocalDateTime.now().toString()) >0) {
				return 1;
			}
			else {
				return -1;
			}
		});
		List<Schedule> listSchedule = scheduleService.findAll();
		Practise practise = practiseService.findById(Long.parseLong("1"));
		List<String> listImagePractise = new ArrayList<>();
		String[] x = practise.getImage().split(",");
		String firstImagePractise = "";
		if(!x[0].contains("/img/core-img/")) {
			firstImagePractise = "/img/core-img/"+x[0];
		}
		for(int i=0; i<x.length; i++) {
			if(!x[i].contains("/img/core-img/")) {
				listImagePractise.add("/img/core-img/"+x[i]);
			}
		}
		modelMap.addAttribute("listNews1", listNews1);
		modelMap.addAttribute("listNews2", listNews2);
		modelMap.addAttribute("listSchedule", listSchedule);
		modelMap.addAttribute("firstImagePractise", firstImagePractise);
		modelMap.addAttribute("listImagePractise", listImagePractise);
		return "homePage";
	}
}
