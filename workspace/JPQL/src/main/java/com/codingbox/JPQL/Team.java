package com.codingbox.JPQL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Team {
	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	private String name;
	
	// 양방향 만들기
	@OneToMany(mappedBy = "team")
	private List<Member> member = new ArrayList<>();
}
