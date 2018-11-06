package com.vandung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vandung.model.News;
import com.vandung.service.NewsService;

@Controller
public class HomepageCotroller {
	
	@Autowired
	private NewsService newsService;

	@RequestMapping(value = "/arsenal", method = RequestMethod.GET)
	public String defaultPage(ModelMap modelMap) {
		News news = newsService.findById(Long.parseLong("17"));
		modelMap.addAttribute("news", news);
		return "homePage";
	}
}
