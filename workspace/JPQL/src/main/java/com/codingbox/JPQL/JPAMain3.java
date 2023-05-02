package com.codingbox.JPQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;




public class JPAMain3 {
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
			
			em.flush();
			em.clear();
			
			// 엔티티 프로젝션
			// m은 엔티티
			List<Member> result = em.createQuery("select m from Member m", Member.class)
									.getResultList();
			
			// 이 영역이 update가 되면, 영속성 컨텍스트에서 관리가 되고,
			// update가 안되면 관리가 안된다는 것
			Member findMember = result.get(0);
			findMember.setAge(20);
			
			// 엔티티 프로덕션
			/*
			 * JPQL 입장에선 일반 select 문이 나가지만, 실제 sql 입장에서는 join결과가
			 * 발생한다.
			 */
//			List<Team> result2 = em.createQuery("select m.team from Member m", Team.class)
//								.getResultList();
			
			List<Team> result2 = em.createQuery("select m.team from Member m join m.team t", Team.class)
					.getResultList();
			
			// 임베디드 타입 프로젝션
			em.createQuery("select o.address from Order o", Address.class)
			.getResultList();
			
			// 에러발생
			// from Address 로 가져올 수 없다. o.Address가 소속되어 있는 엔티티에서 가져와야
			// 한다.
//			em.createQuery("select o.address from Address o", Address.class)
//			.getResultList();
			
			// 여러 값 조회
			List resultList = em.createQuery("select m.username, m.age from Member m")
								.getResultList();
			// 타입을 지정하지 못하니까 object 받아오기
			Object o = resultList.get(0);
			Object[] result3 = (Object[])o;
			System.out.println("username = " + result3[0]);
			System.out.println("age = " + result3[1]);
			
			///////////////////////////////////// 2단계
			List<Object[]> resultList2 = em.createQuery("select m.username, m.age from Member m")
									.getResultList();
			
			Object[] result4 = resultList2.get(0);
			System.out.println("username = " + result4[0]);
			System.out.println("age = " + result4[1]);
			
			///////////////////////////////////// 3단계 - 제일 권장
			List<MemberDTO> result5 
					= em.createQuery("select new com.codingbox.JPQL.MemberDTO(m.username, m.age) from Member m")
						.getResultList();
			
			MemberDTO memberDTO = result5.get(0);
			System.out.println("memberDTO : " + memberDTO.getUsername());
			System.out.println("memberDTO : " + memberDTO.getAge());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
