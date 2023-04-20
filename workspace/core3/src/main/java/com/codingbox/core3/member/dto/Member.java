package com.codingbox.core3.member.dto;

@Entity
public class Member {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySequence")
	@SequenceGenerator(name = "mySequence",sequenceName = "member_seq", allocationSize = 1)
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
