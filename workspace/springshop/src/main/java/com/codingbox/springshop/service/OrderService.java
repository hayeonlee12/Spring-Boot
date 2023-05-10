package com.codingbox.springshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.springshop.ItemRepository;
import com.codingbox.springshop.MemberRepository;
import com.codingbox.springshop.OrderRepository;
import com.codingbox.springshop.OrderSearch;
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

	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAll(orderSearch);
	}

	@Transactional
	public void cancelOrder(Long orderId) {
		// 영속성 컨텍스트 -> 주문 엔티티 조회
		Order order = orderRepository.findOne(orderId);
		
		// 주문 취소
		/*
		 * 주문 취소 시 수량 update, 상태값 변경에 처리를 각각 해줘야하나
		 * 데이터들만 바꿔주면 jpa는 해당 값들을 변경을 체크하고 있어서 (더티체킹, 변경내역 감지)
		 * 변경내역 감지가 변경된 내용들을 다 찾아서 데이터베이스에 업데이트 쿼리가 전송되게 된다.
		 * 여기선 Order의 상태변경 update, Item의 stockQuantity가 변경된다.
		 */
		order.cancel();
	}
	
}
