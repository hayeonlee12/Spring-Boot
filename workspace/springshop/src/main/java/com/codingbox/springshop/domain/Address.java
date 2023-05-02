package com.codingbox.springshop.domain;


import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
	
	// jpa 표준 스펙상 만들어 놓은 기본 생성자
	protected Address() {}

	
	/*
	 * @Setter를 제거하고, 생성자에서 값을 모두 초기화해서 변경 불가능할 클래스를 만들려는 의도
	 */
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	
}
