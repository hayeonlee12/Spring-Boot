package com.codingbox.querydsl;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.codingbox.querydsl.domain.Member;
import com.codingbox.querydsl.domain.Team;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.codingbox.querydsl.domain.QMember.*;
import static com.codingbox.querydsl.domain.QTeam.*;

import java.util.List;




public class QueryDSLMain6 {
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
			
			/*
			 * group by
			 * - 팀의 이름과 각 팀의 평균 연령을 구해라
			 */
//			List<Tuple> result = queryFactory
//									.select(team.name, member.age.avg())
//									.from(member)
//									.join(member.team, team) // member에 있는 team과 , team을 join
//									.groupBy(team.name)
//									.fetch();
//			
//			Tuple resultTeamA = result.get(0);
//			Tuple resultTeamB = result.get(1);
//			
//			System.out.println("resultTeamA : " + resultTeamA.toString());
//			System.out.println("resultTeamB : " + resultTeamB.toString());
			
			// having
			List<Tuple> result = queryFactory
					.select(team.name, member.age.avg())
					.from(member)
					.join(member.team, team) // member에 있는 team과 , team을 join
					.groupBy(team.name)
					.having(member.age.avg().gt(10))
					.fetch();

			Tuple resultTeamB = result.get(0);

			System.out.println("resultTeamB : " + resultTeamB.toString());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
