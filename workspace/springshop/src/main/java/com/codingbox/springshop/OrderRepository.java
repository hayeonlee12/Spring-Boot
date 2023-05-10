package com.codingbox.springshop;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.codingbox.springshop.domain.Order;
import com.codingbox.springshop.domain.OrderStatus;
import com.codingbox.springshop.domain.QMember;
import com.codingbox.springshop.domain.QOrder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
	
	private final EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}

	public List<Order> findAll(OrderSearch orderSearch) {
		// queryDSL
		JPAQueryFactory query = new JPAQueryFactory(em);
		QOrder order = QOrder.order;
		QMember member = QMember.member;
		
		return query.select(order)
					.from(order)
					.join(order.member, member)
//					.where(order.status.eq(orderSearch.getOrderStatus()), member.name.like(orderSearch.getMemberName()))
					.where(statsEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getMemberName()))
					.limit(1000)
					.fetch(); // member : member의 알리아스
	}
	
	private BooleanExpression nameLike(String memberName) {
		if(memberName == null || memberName.equals("")) {
			return null;
		}
		return QMember.member.name.contains(memberName);
	}

	private BooleanExpression statsEq(OrderStatus orderStatus) {
		if(orderStatus == null) {
			return null;
		}
		
		return QOrder.order.status.eq(orderStatus);
	}

	public Order findOne(Long orderId) {
		return em.find(Order.class, orderId);
	}
	
}
