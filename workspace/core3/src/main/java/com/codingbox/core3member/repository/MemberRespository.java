package com.codingbox.core3member.repository;

import java.util.List;

import com.codingbox.core3.member.dto.Member;

public interface MemberRespository {
	// 회원 저장
	Member save(Member member);
	
	// 전체 조회
	List<Member> findAll();
}
