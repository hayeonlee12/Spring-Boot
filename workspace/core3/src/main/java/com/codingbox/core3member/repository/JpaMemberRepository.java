package com.codingbox.core3member.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.codingbox.core3.member.dto.Member;
import com.codingbox.core3.member.dto.MemberEntity;

@Repository
public class JpaMemberRepository implements MemberRespository{

	private final EntityManager em;
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", MemberEntity.class).getResultList();
	}
	
}
