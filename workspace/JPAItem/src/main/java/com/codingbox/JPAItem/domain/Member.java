package com.codingbox.JPAItem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class Member {
	@Id
	@GeneratedValue // 전략 생략하면 AUTO
	@Column(name = "MEMBER_ID")
	private Long id;
	private String name;
	private String City;
	private String Street;
	private String zipcode;
	
}
