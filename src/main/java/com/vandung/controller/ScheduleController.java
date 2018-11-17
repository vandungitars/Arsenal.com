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
import com.vandung.model.News;
import com.vandung.model.Schedule;
import com.vandung.service.NewsService;
import com.vandung.service.ScheduleService;

@Controller
public class ScheduleController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping(value = "/arsenal/schedule", method = RequestMethod.GET)
	public String defaultPage(ModelMap modelMap) {
		List<Schedule> listSchedule = scheduleService.findAll();
		List<News> listNews = newsService.getListNewsByType(Long.parseLong("1"), 6);
		listNews.forEach((p)->{
			if(!p.getImage_news().contains("/img/core-img/")) {
				p.setImage_news("/img/core-img/" + p.getImage_news());
			}
		});
		listNews.sort((p1,p2)->{
			if(p1.getId_news().compareTo(p2.getId_news()) >0) {
				return -1;
			}
			else {
				return 1;
			}
		});
		modelMap.addAttribute("listNews", listNews);
		modelMap.addAttribute("listSchedule", listSchedule);
		return "schedulePage";
	}
	
	@RequestMapping(value = "/admin/Schedule", method = RequestMethod.GET)
	public String viewAdminSchedule() {
		return "adminAddSchedule";
	}
	
	@RequestMapping(value = "/admin/addSchedule", method = RequestMethod.POST)
	public String addSchedule(@RequestParam String dataJson, @RequestParam String datetime) {
		ObjectMapper objectMapper = new ObjectMapper();
		Schedule schedule = new Schedule();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String team_customer = jsonNode.get("team_customer").asText();
			String team_home = jsonNode.get("team_home").asText();
			// time
			if(team_customer != "" && team_home != "") {
				schedule.setTeam_customer(team_customer);
				schedule.setTeam_home(team_home);
				schedule.setTime(datetime);
				scheduleService.save(schedule);	
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "adminAddSchedule";
	}
	
	@RequestMapping(value = "/admin/getListSchedule", method = RequestMethod.GET)
	public String getListSchedule(ModelMap modelMap) {
		List<Schedule> listSchedule = scheduleService.findAll();
		modelMap.addAttribute("listSchedule", listSchedule);
		return "adminUpdateSchedule";
	}
	
	@RequestMapping(value = "/admin/updateSchedule", method = RequestMethod.POST)
	public String updateSchedule(@RequestParam String dataJson, @RequestParam String idSchedule, @RequestParam String datetime) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode; 
		Long id_schedule = Long.parseLong(idSchedule);
		Schedule schedule = new Schedule();
		try {
			jsonNode = objectMapper.readTree(dataJson);
			schedule.setTeam_customer(jsonNode.get("team_customer").asText());
			schedule.setTeam_home(jsonNode.get("team_home").asText());
			schedule.setTime(datetime);
			scheduleService.update(schedule, id_schedule);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "adminUpdateDatabase";
	}
	
	@RequestMapping(value = "/admin/deleteSchedule", method = RequestMethod.POST)
	public String deleteSchedule(@RequestParam String idSchedule) {
		Long id_schedule = Long.parseLong(idSchedule);
		scheduleService.deleteById(id_schedule);
		return "adminUpdateDatabase";
	}
}
