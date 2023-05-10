package com.codingbox.querydsl;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.criterion.Projection;
import org.hibernate.sql.Select;

import com.codingbox.querydsl.domain.Member;
import com.codingbox.querydsl.domain.MemberDTO;
import com.codingbox.querydsl.domain.QMember;
import com.codingbox.querydsl.domain.Team;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.codingbox.querydsl.domain.QMember.*;
import static com.codingbox.querydsl.domain.QTeam.*;

import java.util.List;




public class QueryDSLMain10 {
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
			
			// 프로젝션 대상이 하나
			List<String> result = queryFactory
									.select(member.username)
									.from(member)
									.fetch();
			
			for(String s : result) {
				System.out.println("s : " + s);
			}
			
			
			// 튜플 조회 : 프로젝션 대상이 둘 이상일떄 사용
			List<Tuple> result2 = queryFactory
									.select(member.username, member.age)
									.from(member)
									.fetch();
			
			for(Tuple tuple : result2) {
				String username = tuple.get(member.username);
				Integer age = tuple.get(member.age);
				System.out.println("username = " + username);
				System.out.println("age = " + age);
			}
			
			// DTO 조회(중요)
//			// jpql
//			List<MemberDTO> resultJPQL = em.createQuery(
//					"select new com.codingbox.querydsl.domain.MembeDTO(m.username, m.age) " + 
//					"from Member m", MemberDTO.class
//			).getResultList();
//			
//			for(MemberDTO memberDTO : resultJPQL) {
//				System.out.println("memberDTO : " + memberDTO);
//			}
			
			// 프로퍼티 접근 -> setter 접근 방법
			/*
			 * bean : getter, setter
			 * 1param : MemberDTO.class
			 * 2param ~ : 꺼내올 값 나열
			 */
			
			List<MemberDTO> result3 = queryFactory
										.select(Projections.bean(MemberDTO.class, 
													member.username,
													member.age))
										.from(member)
										.fetch();
			
			for(MemberDTO memberDTO : result3) {
				System.out.println("MemberDTO : " + memberDTO);
			}
			
			// 필드 직접 접드
			// getter, setter 없어도 된다.
			List<MemberDTO> result4 = queryFactory
										.select(Projections.fields(MemberDTO.class,
																	member.username, 
																	member.age))
										.from(member)
										.fetch();
			
			for(MemberDTO memberDTO : result4) {
				System.out.println("MemberDTO4 : " + memberDTO);
			}
			
			// 생성자
			
			List<MemberDTO> result5 = queryFactory
					.select(Projections.constructor(MemberDTO.class, 
													member.username, 
													member.age))
					.from(member)
					.fetch();

			for(MemberDTO memberDTO : result5) {
			System.out.println("MemberDTO5 : " + memberDTO);
			}
			
			List<MemberDTO> result6 = queryFactory
					.select(Projections.fields(MemberDTO.class, 
												member.username.as("name"), 
												member.age))
					.from(member)
					.fetch();

			for(MemberDTO memberDTO : result6) {
			System.out.println("MemberDTO6 : " + memberDTO);
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
