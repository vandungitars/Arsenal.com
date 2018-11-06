package com.vandung.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vandung.model.News;
import com.vandung.model.Type_News;
import com.vandung.service.NewsService;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value = "/arsenal/News/{idNews}", method = RequestMethod.GET)
	public String defaultPage(@PathVariable String idNews, ModelMap modelMap) {
		Long id_news = Long.parseLong(idNews);
		News news = newsService.findById(id_news);
		news.setContent_news(ApiController.changeContent(news.getContent_news()));
		modelMap.addAttribute("news", news);
		return "newsPage";
	}
	
	@RequestMapping(value = "/admin/News" , method = RequestMethod.GET)
	public String viewAdminNews(ModelMap modelMap){
		List<Type_News> listType = newsService.getListType();
		modelMap.addAttribute("listType", listType);
		return "adminAddNews";
	}
	
	@RequestMapping(value = "/admin/addNews", method = RequestMethod.POST)
	public String addNews(@RequestParam String dataJson, @RequestParam String optionText) {
		ObjectMapper objectMapper = new ObjectMapper();
		News news = new News();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String title_news = jsonNode.get("title_news").asText();
			String description_news = jsonNode.get("description_news").asText();
			String description_image = jsonNode.get("description_image").asText();
			String image_news = ApiController.nameFile;
			String timeupdate_news = LocalDateTime.now().toString();
			String content_news = jsonNode.get("content_news").asText();
			if((title_news != "") && (description_news != "") && (description_image != "") && (image_news != "") && (content_news != "")) {
				news.setTitle_news(title_news);
				news.setDescription_news(description_news);
				news.setDescription_image(description_image);
				news.setImage_news(image_news);
				news.setContent_news(content_news);
				news.setTimeupdate_news(timeupdate_news);
				List<Type_News> listType = newsService.getListType();
				listType.forEach((p)->{
					if(p.getTypes().equals(optionText)) {
						news.setType(p);
					}
				});
				newsService.save(news);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "adminAddNews";
	}
	
	@RequestMapping(value = "/admin/getListNews", method = RequestMethod.GET)
	public String getListNews(ModelMap modelMap) {
		List<News> listNews = newsService.findAll();
		listNews.forEach((n)->{
			n.setImage_news("/img/core-img/" + n.getImage_news());
		});
		modelMap.addAttribute("listNews", listNews);
		return "adminUpdateNews";
	}
	
	@RequestMapping(value = "/admin/updateNews", method = RequestMethod.POST)
	public String updateNews(@RequestParam String dataJson, @RequestParam String idNews) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode; 
		Long id_News = Long.parseLong(idNews);
		News news = new News();
		try {
			jsonNode = objectMapper.readTree(dataJson);
			news.setTitle_news(jsonNode.get("title_news").asText());
			news.setDescription_news(jsonNode.get("description_news").asText());
			news.setContent_news(jsonNode.get("content_news").asText());
			news.setTimeupdate_news(LocalDateTime.now().toString());
			newsService.update(news, id_News);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "adminUpdateNews";
	}
	
	@RequestMapping(value = "/admin/deleteNews", method = RequestMethod.POST)
	public String deleteNews(@RequestParam String idNews) {
		Long id_News = Long.parseLong(idNews);
		newsService.deleteById(id_News);
		return "adminUpdateNews";
	}
}
