package com.vandung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String defaultPage() {
		return "login";
	}
}
