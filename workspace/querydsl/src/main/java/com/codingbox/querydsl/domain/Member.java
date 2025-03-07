package com.codingbox.querydsl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {
	@Id @GeneratedValue
	@Column(name="member_id")
	private Long id;
	private String username;
	private int age;
	
	@ManyToOne
	@JoinColumn(name="team_id")
	private Team team;
	
	// lombok에서 처리
//	protected Member() {}
	
	public Member(String username) {
		this(username, 0);
	}
	
	public Member(String username, int age) {
		this(username, age, null);
	}

	public Member(String username, int age, Team team) {
		super();
		this.username = username;
		this.age = age;
		if(team != null) {
			changeTeam(team);
		}
		this.team = team;
	}

	public void changeTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
	}

//	@Override
//	public String toString() {
//		return "Member [id=" + id + ", username=" + username + ", age=" + age + "]";
//	}
	
	
}
