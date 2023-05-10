package com.codingbox.querydsl;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.sql.Select;

import com.codingbox.querydsl.domain.Member;
import com.codingbox.querydsl.domain.QMember;
import com.codingbox.querydsl.domain.Team;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.codingbox.querydsl.domain.QMember.*;
import static com.codingbox.querydsl.domain.QTeam.*;

import java.util.List;




public class QueryDSLMain9 {
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
			Member member5 = new Member(null, 100, teamB);
			Member member6 = new Member("member5", 100, teamB);
			Member member7 = new Member("member6", 100, teamB);
			
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			em.persist(member5);
			em.persist(member6);
			em.persist(member7);
			
			// 초기화
			em.flush();
			em.clear();
			
			List<String> result = queryFactory
									.select(
											member.age.when(10).then("열살")
														.when(20).then("스무살")
														.otherwise("기타")
									)
									.from(member)
									.fetch();
			
			// 복잡한 조건
			List<String> result2 = queryFactory.
										select(
												new CaseBuilder()
												.when(member.age.between(0, 20)).then("0~20살")
												.when(member.age.between(21, 30)).then("21~30살")
												.otherwise("기타")
										)
										.from(member)
										.fetch();
			
			for(String s : result2) {
				System.out.println("s : " + s);
			}
			
			// 상수, 문자 더하기 - constant는 쿼리로는 나가지 않는다
			List<Tuple> result3 = queryFactory
									.select(member.username, Expressions.constant("A"))
									.from(member)
									.fetch();
			
			for(Tuple tuple : result3) {
				System.out.println("tuple : " + tuple);
			}
			
			// 문자 더하기, concat
			// username_age
			// stringValue() : 문자로 바꾼다
			List<String> result4 = queryFactory
									.select(member.username.concat("_").concat(member.age.stringValue()))
									.from(member)
									.fetch();
			
			for(String s : result4) {
				System.out.println("s : " + s);
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
