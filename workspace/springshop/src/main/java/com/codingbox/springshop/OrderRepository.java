package com.codingbox.springshop;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.codingbox.springshop.domain.Order;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
	
	private final EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}
	
}
