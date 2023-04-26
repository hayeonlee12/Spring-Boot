package com.codingbox.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		// transaction : 데이터베이스의 상태를 변화시키기 위해 수행하는 작업 단위
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Member member = new Member();
			
			// 추가
			member.setId(2L);
			member.setName("UserB");
			
			em.persist(member);
			
			// 회원 조회
			Member findMember = em.find(Member.class, 1L);
			System.out.println("findMember.id : " + findMember.getId());
			System.out.println("findMember.name : " + findMember.getName());
			
			// 회원 수정
			findMember.setName("HelloJPA");
			
			// 회원 삭제
			em.remove(findMember);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
