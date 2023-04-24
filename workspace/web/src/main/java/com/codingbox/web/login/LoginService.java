package com.codingbox.web.login;

import org.springframework.stereotype.Service;

import com.codingbox.web.domain.member.Member;
import com.codingbox.web.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final MemberRepository memberRepository;

	public Member login(String loginId, String password) {
		Member member = memberRepository.findByLoginId(loginId);
		
		if(member != null && member.getLoginId().equals(loginId) && member.getPassword().equals(password) ) {
			// 로그인 성공시
			return member;
		} else {
			return null;
		}
	}
	
}
