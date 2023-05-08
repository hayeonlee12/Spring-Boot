package com.codingbox.springshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.springshop.ItemRepository;
import com.codingbox.springshop.MemberRepository;
import com.codingbox.springshop.OrderRepository;
import com.codingbox.springshop.domain.Delivery;
import com.codingbox.springshop.domain.Item;
import com.codingbox.springshop.domain.Member;
import com.codingbox.springshop.domain.Order;
import com.codingbox.springshop.domain.OrderItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	private final OrderRepository orderRepository;
	
	// 주문
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		// 엔티티 조회
		// jpa 영속성 컨텍스트 영역에 들어옴
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		// 주문 상품
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		// 주문 생성
		Order order = Order.createOrder(member, orderItem);
		// 주문 저장
		orderRepository.save(order);
		
		return order.getId();
		
		
	}
	
}
