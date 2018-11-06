package com.vandung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class PractiseController {
 
    @RequestMapping(value = "/admin/Practise", method = RequestMethod.GET)
    public String viewAdminPractise() {
        return "adminAddPractise";
    }
}
