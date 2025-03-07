package com.codingbox.JPQL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product {
	
	@Id @GeneratedValue
	@Column(name="PRODUCT")
	private Long id;
	
	private String name;
	private int price;
	private int stockAmount;
}
