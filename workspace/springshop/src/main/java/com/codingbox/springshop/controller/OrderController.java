package com.codingbox.springshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingbox.springshop.domain.Item;
import com.codingbox.springshop.domain.Member;
import com.codingbox.springshop.service.ItemService;
import com.codingbox.springshop.service.MemberService;
import com.codingbox.springshop.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {
	// url : /order
	// 메소드 : createForm
	// return : order/orderForm
	
	private final MemberService memberService;
	private final ItemService itemService;
	private final OrderService orderService;
	
	@GetMapping("/order")
	public String createForm(Model model) {
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		return "order/orderForm";
	}
	
	@PostMapping("/order")
	public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, 
						@RequestParam("count") int count) {
	
			orderService.order(memberId, itemId, count);
			return "redirect:/orders";
	}
}
