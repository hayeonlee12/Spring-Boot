package com.codingbox.core3.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingbox.core3.member.dto.Member;
import com.codingbox.core3.member.dto.MemberFormDTO;
import com.codingbox.core3.member.service.MemberService;
import com.codingbox.core3member.repository.MemberRespository;

@Controller
public class MemberController {
	/*
	 * Controller가 service를 의존한다고 표현한다.
	 * Service는 여러 Controller가 가져다 쓸 수 있기 때문에
	 * Spring Container에 등록을 해야 한다.
	 */
//	MemberService service = new MemberService(null);
	
	// 스프링 스럽게 작업
	private final MemberService memberService;
	
	/*
	 * MemberController가 생성될때, 생성자를 호출해준다.
	 * 즉 service까지 생성해서 자동으로 호출해준다.
	 * 서버 기동시 연결 실패시 에러가 발생한다.
	 * - > 기존은 테스트를 통해서만 service가 오류가 나는 것을 확인할 수 있었다.
	 */
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/members/new")
	public String createForm() {
		return "/members/createMemberForm";
	}
	
	@PostMapping(value = "/members/new")
	public String create(MemberFormDTO form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		// 홈 화면으로 돌린다
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
	
}
