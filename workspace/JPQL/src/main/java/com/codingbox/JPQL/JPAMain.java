package com.codingbox.JPQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;




public class JPAMain {
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
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);
			
			// 타입 정보가 명확할때
			TypedQuery<Member> query = 
					em.createQuery("select m from Member m", Member.class);
			List<Member> resultList = query.getResultList();
			for(Member member2 : resultList) {
				System.out.println("member2 = " + member2);
			}
			
			// 결과값이 하나일 경우
			Member result = query.getSingleResult();
			
			// 타입정보가 String.class로 반환타입이 명확할 때 사용
			TypedQuery<String> query2 = 
					em.createQuery("select m.username from Member m", String.class);
			
			
			// m.username(String), m.age(int) : 이렇게 반환타입이 명확하지 않을 떄 사용
			Query query3 = 
					em.createQuery("select m.username, m.age from Member m");
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
