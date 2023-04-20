package com.koreait.springtestquest.request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestController {
	@GetMapping("/param/home")
	public String paramHome() {
		return "/param/home";
	}
	
	@GetMapping("/param/get")
	public String requestParam(@RequestParam(name="answer") int answer, Model model) {
		String check = null;
		if(answer == 500) {
			check = "정답입니다.";
		} else {
			check = "오답입니다.";
		}
		
		model.addAttribute("answer", answer);
		model.addAttribute("check", check);
		
		return "param/getresult";
		
	}
	
	@PostMapping("/param/post")
	public String paramPost(@RequestParam(name="username", defaultValue = "guest") String username, 
			@RequestParam(name="age", defaultValue = "-1") int age, Model model) {
		System.out.println("username : " + username);
		System.out.println("age : " + age);
		
		model.addAttribute("username", username);
		model.addAttribute("age", age);
		
		return "param/postresult";
	}
}
