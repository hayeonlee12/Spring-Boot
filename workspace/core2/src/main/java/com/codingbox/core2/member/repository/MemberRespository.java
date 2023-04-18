package com.codingbox.core2.member.repository;

import java.util.List;

import com.codingbox.core2.member.dto.Member;

public interface MemberRespository {
	// 회원 저장
	Member save(Member member);
	
	// 전체 조회
	List<Member> findAll();
}
