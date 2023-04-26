package com.codingbox.web.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingbox.web.domain.member.Member;
import com.codingbox.web.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
		return "login/loginForm";
	}
	
//	@PostMapping("/login")
	public String login(@ModelAttribute LoginForm form, Model model, RedirectAttributes redirAttrs,
						HttpServletResponse response) {
		/*
		 * model : forward 방식
		 * RedirectAttributes : redirect 방식
		 */
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if(loginMember == null) {
			// 로그인 실패
			model.addAttribute("msg", "로그인 실패");
			return "login/loginForm";
		}
		
		// 성공시
		Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
		response.addCookie(idCookie);
		redirAttrs.addFlashAttribute("msg", "로그인 성공");
		return "redirect:/";
		
	}
	
//	@PostMapping("/login")
	public String loginv2(@ModelAttribute LoginForm form, Model model, RedirectAttributes redirAttrs,
						HttpServletRequest request) {
		/*
		 * model : forward 방식
		 * RedirectAttributes : redirect 방식
		 */
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if(loginMember == null) {
			// 로그인 실패
			model.addAttribute("msg", "로그인 실패");
			return "login/loginForm";
		}
		
		// 성공시
		HttpSession session = request.getSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		redirAttrs.addFlashAttribute("msg", "로그인 성공");
		return "redirect:/";
		
	}
	
	@PostMapping("/login")
	public String loginv3(@ModelAttribute LoginForm form, Model model, RedirectAttributes redirAttrs,
						HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURL) {
		/*
		 * model : forward 방식
		 * RedirectAttributes : redirect 방식
		 */
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if(loginMember == null) {
			// 로그인 실패
			model.addAttribute("msg", "로그인 실패");
			return "login/loginForm";
		}
		
		// 성공시
		HttpSession session = request.getSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		redirAttrs.addFlashAttribute("msg", "로그인 성공");
		return "redirect:" + redirectURL;
		
	}
	
//	@PostMapping("/logout")
	public String logout(HttpServletResponse response) {
		expireCookie(response, "memberId");
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logoutv2(HttpServletRequest request) {
		// 세션을 삭제한다.
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	private void expireCookie(HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
