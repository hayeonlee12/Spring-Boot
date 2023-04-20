package com.codingbox.item.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
	private long id;
	private String itemname;
	private Integer price; // 가격이 null
	private Integer quantity; // 수량이 null
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Item() {}

	public Item(String itemname, Integer price, Integer quantity) {
		super();
		this.itemname = itemname;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	
	
}
