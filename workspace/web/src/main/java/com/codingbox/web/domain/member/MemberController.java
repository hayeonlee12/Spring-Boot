package com.codingbox.web.domain.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberRepository memberRepository;
	
	/*
	 * @ModelAttribute("member") Member member
	 * -> model.addAttribute("member" new Member());
	 */
	@GetMapping("/add")
	public String addForm(@ModelAttribute("member")Member member) {
		return "members/addMemberForm";
	}
	
	@PostMapping("/add")
	public String saveForm(
			@RequestParam String 	loginId,
			@RequestParam String	name,
			@RequestParam String	password,
			Model model
		) {
	Member member = new Member();
	member.setLoginId(loginId);
	member.setName(name);
	member.setPassword(password);
		
	memberRepository.save(member);
	model.addAttribute("member", member);
	return "home";
}
}
