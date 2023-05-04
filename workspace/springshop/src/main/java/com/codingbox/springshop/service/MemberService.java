package com.codingbox.springshop.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.springshop.MemberRepository;
import com.codingbox.springshop.domain.Member;

import lombok.RequiredArgsConstructor;

/*
 * @Transactional
 * - DB와 관련된, 트랜젝션이 필요한 서비스 클래스 혹은 메서드에 @Transactional 추가
 * - 일련의 작업물을 묶어서 하나의 단위로 처리할때 
 * - 스프링에서 제공하는 것을 사용할것 : org.springframework.transaction.annotation.Transactional
 * - 옵션 : readOnly = true or false
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	@Transactional
	public Long join(Member member) throws IllegalAccessException {
		validationMemberCheck(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validationMemberCheck(Member member) throws IllegalAccessException {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		
		if(!findMembers.isEmpty()) {
			throw new IllegalAccessException("이미 존재하는 회원입니다.");
		}
		
	}
	
	/*
	 * @Transactional
	 * 회원목록   : findMembers()
	 */
	
//	@Transactional
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
}
