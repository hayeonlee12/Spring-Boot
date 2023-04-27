package com.codingbox.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
//@Table(name="MBR")
public class Member {
	@Id
	private Long id;
	
	@Column(unique = true, length = 10)
	private String name;
	
	@Column(name="myage")
	private int age;
	
	// 날짜 타입 매핑
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	// 매핑 무시
	@Transient
	private int temp;
}
