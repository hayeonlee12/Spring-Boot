package com.codingbox.springshop.test;

public enum Type {
	// static final string WALKING
	// = "워킹화";
	WALKING("워킹화"),
	RUNNING("런닝화"),
	TRACKING("트래킹화"),
	HIKING("등산화");
	
	final private String name;
	
	private Type(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
