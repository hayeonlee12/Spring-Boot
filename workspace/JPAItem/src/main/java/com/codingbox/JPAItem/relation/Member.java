package com.codingbox.JPAItem.relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	// pk, 자동증가
	@Id
	@GeneratedValue
	@Column(name="MEMBER_ID")
	private Long id;
	@Column(name="USERNAME")
	private String name;
	
	
//	private Long teamId;
/*
 * 1대다의 개념을 객체에게 알려줭한다.
 * DB의 기준으로 1대다의 개념을 줘야한다.
 * @ManyToOne : 여기선 Team이 하나이다.
 * @JoinColummn : 관계 컬럼을 적어준다.
 * 				TEAM_ID와 조인해야 한다.
 */
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	@Setter(value = AccessLevel.NONE) // lombok에서 자동으로 setter생성을 막아준다.
	private Team team;
	
	/*
	 * 일반적인 setter의 형태(자바에서의 관례 형태를 벗어났다)가 아니면 이름을 바꿔준다.
	 * 그럼 추후 소스코드를 봤을때 단순 setter작업이 아닌 중요한 작업을 진행하는지를 파악할 수 있다.
	 */
	
	public void changeTeam(Team team) {
		this.team = team;
		// this : 나 자신의 인스턴스를 넣어준다.
		team.getMember().add(this);
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", team=" + team + "]";
	}
	
}
