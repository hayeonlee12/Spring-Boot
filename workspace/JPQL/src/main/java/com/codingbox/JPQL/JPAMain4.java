package com.codingbox.JPQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;




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
			
			for(int i = 0; i < 100; i++) {
				Member member = new Member();
				member.setUsername("member1");
				member.setAge(10);
				em.persist(member);
			}
			
			em.flush();
			em.clear();
			
			// 페이징 처리
			// order by 들어가야 한다
			String jpql = "select m from Member m order by m.username desc";
			List<Member> resultList = em.createQuery(jpql, Member.class)
										.setFirstResult(10)
										.setMaxResults(20)
										.getResultList();
			
			System.out.println("result.size" + resultList.size());
			
			for(Member member1 : resultList) {
				System.out.println("member1 = " + member1);
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
