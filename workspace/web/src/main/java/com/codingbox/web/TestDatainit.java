package com.codingbox.web;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.codingbox.web.domain.member.Member;
import com.codingbox.web.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDatainit {
	
	private final MemberRepository memberRepository;
	
	@PostConstruct
	public void init() {
		Member member = new Member();
		member.setLoginId("test");
		member.setPassword("test!");
		member.setName("테스트");
		memberRepository.save(member);
	}
}
