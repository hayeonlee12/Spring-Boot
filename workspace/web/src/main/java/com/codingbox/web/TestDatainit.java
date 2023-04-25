package com.codingbox.web;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.codingbox.web.domain.member.Member;
import com.codingbox.web.domain.member.MemberRepository;
import com.codingbox.web.item.Item;
import com.codingbox.web.item.ItemRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDatainit {
	
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	@PostConstruct
	public void init() {
		Member member = new Member();
		member.setLoginId("test");
		member.setPassword("test!");
		member.setName("테스트");
		memberRepository.save(member);
	}
	
	/*
	 * 테스트용 데이터 추가
	 */
	@PostConstruct
	public void init2() {
		System.out.println("초기화 메서드");
		itemRepository.save(new Item("testA", 10000, 10));
		itemRepository.save(new Item("testB", 20000, 20));
		itemRepository.save(new Item("testC", 30000, 30));
	}
}
