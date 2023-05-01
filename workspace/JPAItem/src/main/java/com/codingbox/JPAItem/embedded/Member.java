package com.codingbox.JPAItem.embedded;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	@Id @GeneratedValue
	private Long id;
	
	@Column(name="name", nullable = false)
	private String username;
	
/*
 * @Embeddable과 @Embedded 둘중 하나만
 * 넣어도 된다. 하지만 둘다 넣는것을 권장.
 */
	@Embedded
	private Period period = null;
	
//	private String city;
//	private String street;
//	private String zipcode;
	
	// 집 주소
	@Embedded
	private Address address;
	
//	// 회사 주소
//	@Embedded
//	@AttributeOverrides({
//		@AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
//		@AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
//		@AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE")),
//	})
//	private Address workAddress;
}
