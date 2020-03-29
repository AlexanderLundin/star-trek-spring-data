package com.galvanize.controllers;

import com.galvanize.repositories.JdbcOfficerDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    private JdbcOfficerDao jdbcOfficerDao;

    @Value("${spring.application.name}")
    String appName;

    public HomeController(JdbcTemplate jdbcTemplate){
        this.jdbcOfficerDao = new JdbcOfficerDao(jdbcTemplate);
    }

    @RequestMapping("/")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        return new ModelAndView("redirect:/home", model);
    }



//    @RequestMapping("/home")
//    public Model showHomeTemplate(Model model){
//        model.addAttribute("appName", appName);
//        model.addAttribute("officers", jpaOfficerDao.findAll());
//        return model;
//    }
}
