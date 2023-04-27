package com.codingbox.jpa;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@SequenceGenerator(name="MEMBER2_SEQ_GENERATOR",
					sequenceName = "MEMBER_SEQ",
					initialValue = 1, allocationSize = 1)
public class Member2 {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "MEMBER2_SEQ_GENERATOR")
	private Long id;
	
	@Column(name="name", nullable = false)
	private String username;
}
