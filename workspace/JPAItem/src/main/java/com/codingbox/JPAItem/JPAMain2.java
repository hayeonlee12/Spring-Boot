package com.codingbox.JPAItem;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.codingbox.JPAItem.relation.Member;
import com.codingbox.JPAItem.domain.Order;
import com.codingbox.JPAItem.relation.Team;

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
			Team team = new Team();
			team.setName("TeamB");
			em.persist(team);
			
			Member member = new Member();
			member.setName("member2");
//			member.setTeam(team);
			member.changeTeam(team);	// member를 기준으로 team을 넣는다
			em.persist(member);
			
//			team.addMember(member);
			
//			// 강제로 db쿼리를 보고 싶을때
//			em.flush();
//			em.clear();
			
			// 양방향 매핑시에는 양쪽에 값을 모두 입력해 주어야 한다.
			// DB를 다시 다녀오지않고 객체 상태로만 사용할 수 있다.
			team.getMember().add(member);
			
			// 오너가 아니여도 양방향 매핑에서는 select 가능
			System.out.println("=================================");
			Team findTeam = em.find(Team.class, team.getId());
			List<Member> members = findTeam.getMember();
			
			System.out.println("members = " + findTeam);
			
			for(Member m : members) {
				System.out.println("m = " + m.getName());
			}
			System.out.println("=================================");
			
			// 강제로 db쿼리를 보고 싶을때
			em.flush();
			em.clear();
			
			/*
			 * insert 문만 실행하고, select 문은 실행되지 않는다.
			 * - 이유 : team이 영속성 컨텍스트에 들어가있는데(1차 캐시), 현재 변경된 것을
			 * 		감지하지 못한 상태 (flush가 호출되지 않은 상태)에서 검색을 하니
			 * 		select가 되지 않은 것.
			 * 		즉, 1차 캐시에서 조회된 것이 그대로 나오게 된다.
			 * 		1차 캐시에 담긴 내용이 그대로 조회된다.
			 */
			
//			// 저장
//			Member member = new Member();
//			member.setName("member1");
//			em.persist(member);
//			
//			Team team = new Team();
//			team.setName("TeamA");
//			team.getMember().add(member);
//			em.persist(team);
//			
//			// 강제로 db쿼리를 보고 싶을때
//			em.flush();
//			em.clear();
//			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
