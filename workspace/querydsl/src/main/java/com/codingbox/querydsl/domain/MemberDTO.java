package com.codingbox.querydsl.domain;

import lombok.Data;

@Data
public class MemberDTO {
	private String name;
	private int age;
	
	public MemberDTO() {}

	public MemberDTO(String username, int age) {
		super();
		this.name = username;
		this.age = age;
	}
	
}
