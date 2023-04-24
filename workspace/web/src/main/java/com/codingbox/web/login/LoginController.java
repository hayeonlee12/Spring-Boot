package com.codingbox.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingbox.web.domain.member.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute LoginForm form) {
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if(loginMember == null) {
			// 로그인 실패
			return "login/loginForm";
		}
		
		// 성공시
		return "redirect:/";
	}
}
