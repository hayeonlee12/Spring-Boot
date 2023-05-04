package com.codingbox.springshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/")
	public String home() {
//		System.out.println("home controller");
		log.info("home controller");
		return "home"; // home.html로 찾아간다.
	}
}
