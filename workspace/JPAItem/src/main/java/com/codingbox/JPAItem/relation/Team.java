package com.codingbox.JPAItem.relation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class Team {
	// pk, 자동증가
	@Id
	@GeneratedValue
	@Column(name="TEAM_ID")
	private Long id;
	private String name;
	
	/*
	 * @OneToMany : Team에서 Member로 가는건 1대 다 이기때문
	 * mappedBy   : 주인이 아닌 쪽
	 * 			    mappedBy가 적힌 곳은 읽기만 가능하다.
	 *       	    값을 넣어봐야 아무 일도 벌어지지 않는다. 대신 조회 가능.
	 */
	@OneToMany(mappedBy = "team")
	private List<Member> member = new ArrayList<>();
	
	public void addMember(Member member) {
		member.setTeam(this);
		this.member.add(member);
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", member=" + member + "]";
	}
		
}
