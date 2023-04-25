package com.codingbox.web.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.codingbox.web.domain.member.Member;
import com.codingbox.web.domain.member.MemberRepository;
import com.codingbox.web.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final MemberRepository memberRepository;
	
//	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	/*
	 * 로그인 처리까지 되는 home화면 확인
	 * required = false : 로그인 안한 사용자도 들어와야 한다.
	 */
//	@GetMapping("/")
	public String loginhome(
			@CookieValue(name="memberId", required=false) Long memberId,
							Model model) {
		
		// 로그인한 사용자가 아니라면 home으로 보낸다.
		if(memberId == null) {
			return "home";
		}
		// db 조회를 한 후, 사용자가 없으면 다시 home으로 보낸다.
		Member loginMember = memberRepository.findById(memberId);
		if(loginMember == null) {
			return "home";
		}
		
		// 로그인에 성공한 사람은
		// db 조회 결과값을 담아 loginHome 화면으로 이동
		model.addAttribute("member", loginMember);
		return "loginHome";
		
	}
	
//	@GetMapping("/")
	public String loginhomev2(HttpServletRequest request, Model model){
		HttpSession session = request.getSession(false);
		// session null 값이면 return home
		if(session == null) {
			return "home";
		}
		// session의 LOGIN_MEMBER 값으로 db 조회해서 
		// 회원의 데이터가 없으면 return home 
		Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
		if(loginMember == null) {
			return "home";
		}
		// 세션이 유지되면 model에 db 조회 결과 담아서 return loginHome
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
	@GetMapping("/")
	public String loginhomev3(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false)Member loginMember,
			Model model){
		// session attribute 뒤져서 member에 값을 넣어준다.
		if(loginMember == null) {
			return "home";
		}
		// 세션이 유지되면 model에 db 조회 결과 담아서 return loginHome
		model.addAttribute("member", loginMember);
		return "loginHome";
	}

}
