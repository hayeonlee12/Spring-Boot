package com.codingbox.JPQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;




public class JPAMain2 {
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
			
			// 파라미터 바인딩 - 이름기준
//			TypedQuery<Member> query
//				= em.createQuery("select m from Member m where m.username = :username", Member.class);
//			query.setParameter("username", "member1");
//			Member result = query.getSingleResult();
			
			// 메시지 체인
			Member result
			= em.createQuery("select m from Member m where m.username = :username", Member.class)
			  .setParameter("username", "member1")
			  .getSingleResult();
			
			System.out.println("result = " + result);
			System.out.println("result = " + result.getUsername());
			
			// 파라미터 바인딩 - 위치기반(쓰지말것)
			
			Member result2 
				= em.createQuery("select m from Member m where m.username = ?1", Member.class)
				  .setParameter(1, "member1")
				  .getSingleResult();
			
			System.out.println("result2 = " + result2.getUsername());
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
