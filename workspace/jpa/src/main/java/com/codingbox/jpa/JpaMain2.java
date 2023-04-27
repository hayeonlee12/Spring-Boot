package com.codingbox.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			// 비영속 상태
			Member member = new Member();
			member.setId(100L);
			member.setName("newJPA");
			member.setAge(100);
			
			// 여기서부터 영속 상태
			// EntityManager안에 있는 영속 컨텍스트 영역에서 관리가 된다는 뜻
			System.out.println("=== before ===");
			em.persist(member);
			System.out.println("=== after ===");
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
