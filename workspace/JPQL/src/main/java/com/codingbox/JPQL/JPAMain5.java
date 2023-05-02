package com.codingbox.JPQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;




public class JPAMain5 {
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
			
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			member.setTeam(team);
			em.persist(member);
			
			em.flush();
			em.clear();
			
			// 조인, insert 생략가능
			String jpql = "select m from Member m inner join m.team t";
			List<Member> resultList = em.createQuery(jpql, Member.class)
										.getResultList();
			
			// left outer join
			String jpql2 = "select m from Member m left outer join m.team t";
			List<Member> resultList2 = em.createQuery(jpql2, Member.class)
										.getResultList();
			
			// 세타조인(막 조인)
			String jpql3 = "select m from Member m, Team t where m.username = t.name";
			List<Member> resultList3 = em.createQuery(jpql3, Member.class)
										.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
