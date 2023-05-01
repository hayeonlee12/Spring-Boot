package com.codingbox.JPAItem.embedded;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Period {
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	public Period(LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	// 기본 생성자는 반드시 있어야한다
	public Period() {}
}
