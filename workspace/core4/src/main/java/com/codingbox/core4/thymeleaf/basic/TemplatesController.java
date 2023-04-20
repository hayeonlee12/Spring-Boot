package com.codingbox.core4.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplatesController {
	@GetMapping("/fragment")
	public String templateFragment() {
		return "template/fragment/fragmentMain";
	}
}
