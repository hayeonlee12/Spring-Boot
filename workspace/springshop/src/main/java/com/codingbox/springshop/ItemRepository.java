package com.codingbox.springshop;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.codingbox.springshop.domain.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	private final EntityManager em;
	
	public void save(Item item) {
		// 처음에 item이 없으면 id가 null 값이기 때문이다.
		if( item.getId() == null) {
			// 신규등록
			em.persist(item);
		} else {
			
		}
	}
	
	// 메서드 : findAll()
	// return : select 전체한 결과 값, jpql
	
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}
}
