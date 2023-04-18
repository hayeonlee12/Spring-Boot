package com.codingbox.core2.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingbox.core2.member.dto.Member;
import com.codingbox.core2.member.repository.MemberRespository;

@Service
public class MemberService {
//	MemberRespository memberRespository = new MemoryMemberRepository();
	
	// 필드주입 : 순환참조 때문에 권장하지 않음
	// @Autowired private MemberRepository memberRepository;
	
	private final MemberRespository memberRespository;
	
	@Autowired
	public MemberService(MemberRespository memberRespository) {
		this.memberRespository = memberRespository;
	}
	
	// 회원 가입
	public int join(Member member) {
		memberRespository.save(member);
		return member.getId();
	}
	
	// 전체 회원 조회
	public List<Member> findMembers(){
		return memberRespository.findAll();
	}
}
