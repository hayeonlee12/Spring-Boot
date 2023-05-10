package com.codingbox.querydsl;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.codingbox.querydsl.domain.Member;
import com.codingbox.querydsl.domain.Team;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.codingbox.querydsl.domain.QMember.*;

import java.util.List;




public class QueryDSLMain3 {
	/*
	 * - 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
	 * - jpa의 모든 데이터 변경은 트랜잭션 안에서 실행
	 */

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		// transaction : 데이터베이스의 상태를 변화시키기 위해 수행하는 작업 단위
		EntityTransaction tx = em.getTransaction();
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		tx.begin();
		
	
		try {
			Team teamA = new Team("teamA");
			Team teamB = new Team("teamB");
			em.persist(teamA);
			em.persist(teamB);
			
			Member member1 = new Member("member1", 10, teamA);
			Member member2 = new Member("member2", 10, teamA);
			Member member3 = new Member("member3", 10, teamB);
			Member member4 = new Member("member4", 10, teamB);
			
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			
			// 초기화
			em.flush();
			em.clear();
			
			// List
			List<Member> fetch = queryFactory
									.selectFrom(member)
									.fetch();
			
			// 단건
			Member findMember1 = queryFactory
									.selectFrom(member)
									.fetchOne();
			
			// 처음 한 건 조회
			// fetchFirst -> limit(1).fetchOne( )
			Member findMember2 = queryFactory
									.selectFrom(member)
									.fetchFirst();
			
			System.out.println("findMember2 : " + findMember2);
			
			// 페이징에서 사용
			QueryResults<Member> results = queryFactory.selectFrom(member)
												.fetchResults();
			
			long totalCnt = results.getTotal();
			List<Member> content = results.getResults();
			
			// count
			Long totalCount = queryFactory.select(member.count())
											.from(member)
											.fetchOne();
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
