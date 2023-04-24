package com.codingbox.web.domain.member;

import lombok.Data;

@Data
public class Member {
	private Long id;
	private String loginId;
	private String name;
	private String password;
}
