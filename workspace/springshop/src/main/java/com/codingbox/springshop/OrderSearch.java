package com.codingbox.springshop;

import com.codingbox.springshop.domain.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
	// 회원이름
	private String memberName;
	// 주문상태[ORDER, CANCEL]
	private OrderStatus orderStatus;
}
