package com.codingbox.web.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
	private Long id;
	private String itemName;
	private Integer price;		// 가격이 null
	private Integer quantity;	// 수량이 null
	
	public Item() {}

	public Item(String itemName, Integer price, Integer quantity) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	
}

















