package com.codingbox.springshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingbox.springshop.domain.Item;
import com.codingbox.springshop.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
	// createForm()
	// return items/createItemForm
	
	@GetMapping("/items/new")
	public String createItemForm(Model model) {
		model.addAttribute("form", new ItemForm());
		return "items/createItemForm";
	}
	
	// 메서드 이름 : create()
	// 저장 완료시 : home.html
	@PostMapping("/items/new")
	public String create(@Valid ItemForm form, BindingResult result) {
		Item item = new Item();
		item.setName(form.getName());
		item.setPrice(form.getPrice());
		item.setStockQuantity(form.getStockQuantity());
		
		itemService.saveItem(item);
		
		return "redirect:/";
	}
	
	// 메서드 : list()
	// return items/itemList
	@GetMapping("/items")
	public String items(Model model) {
		List<Item> items = itemService.findItems();
		model.addAttribute("items", items);
		return "items/itemList";
	}
}
