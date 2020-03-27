package com.galvanize.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Value("${spring.application.name}")
    String appName;

    @RequestMapping("/home")
    public Model ShowHomeTemplate(Model model){
        model.addAttribute("appName", appName);
        return model;
    }
}
