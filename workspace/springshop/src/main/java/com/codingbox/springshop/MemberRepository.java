package com.codingbox.springshop;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codingbox.springshop.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	
	/*
	 * entityManagerFactory를 직접 주입받고 싶으면,
	 * @PersistenceUnit을 사용하면 되긴하나, 굳이 사용할 일은 없다.
	 */
//	@PersistenceUnit
//	private EntityManagerFactory emf;
	
	// jpa
	// spring이 entityManager를 만들어서 em에다가 주입
//	@PersistenceContext
	@Autowired // spring boot lib 사용시 @Autowired가 처리해준다.
	private final EntityManager em;
	
//	// 생성자 주입
//	private MemberRepository(EntityManager em) {
//		this.em = em;
//	}
	
	// 저장
	public void save(Member member) {
		em.persist(member);
	}
	
	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	/*
	 * 회원목록 	: findAll(),
	 * return type : List<Member>
	 * 내용			: jpql
	 */
	
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}
	
}
