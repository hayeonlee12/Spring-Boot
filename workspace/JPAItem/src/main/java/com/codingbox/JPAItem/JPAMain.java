package com.codingbox.JPAItem;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.codingbox.JPAItem.domain.Item;
import com.codingbox.JPAItem.relation.Member;
import com.codingbox.JPAItem.domain.Order;
import com.codingbox.JPAItem.domain.OrderItem;
import com.codingbox.JPAItem.relation.Team;

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
			// 저장
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
			member.setTeam(team);
			em.persist(member);
			
			// 강제로 db쿼리를 보고 싶을때
			em.flush();
			em.clear();
			
			// 조회
			Member findMember = em.find(Member.class, member.getId());
			Team findTeam = findMember.getTeam();
			System.out.println("findTeamName : " + findTeam.getName());
			
			// 양방향 매핑
			Member findSideMember = em.find(Member.class, member.getId());
			List<Member> members = findSideMember.getTeam().getMember();
			
			for(Member m : members) {
				System.out.println("result = " + m.getName());
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
//			Team team = new Team();
//			team.setName("TeamA");
//			em.persist(team); // 영속상태가 되면 pk의 값이 세팅이 된 후 영속상태가 된다.
//			
//			Member member = new Member();
//			member.setName("member1");
//			member.setTeam(team);
//			em.persist(member);
//			
//			// select
//			// 특정 멤버가 속해있는 팀의 이름을 알고싶을떄
//			Member findMember = em.find(Member.class, member.getId());
//			Team findTeamId = findMember.getTeam();
//			Team findTeam = em.find(Team.class, findTeamId);
//			System.out.println("findTeam : " + findTeam.getName());
			
			
			
			
		
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
