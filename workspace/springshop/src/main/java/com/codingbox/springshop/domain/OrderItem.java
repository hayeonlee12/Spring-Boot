package com.codingbox.springshop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {
	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	private int orderPrice; // 주문 가격
	private int count;
	
	// ===== 비지니스 로직 =======
	public static OrderItem createOrderItem(Item item, int price, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		// 할인 쿠폰 등 .. 가격에 변동이 생겼을 때 여기에다가 비지니스 로직 작성
		orderItem.setOrderPrice(price);
		orderItem.setCount(count);
		
		// 주문한 만큼 재고 조정
		item.removeStock(count);
		
		return orderItem;
	}

	public void cancel() {
		getItem().addStock(count);
	}
	
	
}
