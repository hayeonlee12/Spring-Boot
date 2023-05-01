package com.codingbox.JPAItem;



import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.codingbox.JPAItem.embedded.Address;
import com.codingbox.JPAItem.embedded.Member;
import com.codingbox.JPAItem.embedded.Period;



public class JPAMain4 {
	/*
	 * - 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
	 * - jpa의 모든 데이터 변경은 트랜잭션 안에서 실행
	 */

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		// transaction : 데이터베이스의 상태를 변화시키기 위해 수행하는 작업 단위
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
	
		try {
			Member member = new Member();
			member.setUsername("user");
			member.setAddress(new Address("서울", "역삼", "123"));
			member.setPeriod(new Period(LocalDateTime.now(), LocalDateTime.now()));
			
			em.persist(member);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
