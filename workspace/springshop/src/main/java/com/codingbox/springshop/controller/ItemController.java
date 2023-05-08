package com.codingbox.springshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// 메서드 : updateItemForm()
	// 저장완료시 : items/updateItemForm
	@GetMapping("/items/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
		Item item = itemService.findOne(itemId);
		
		ItemForm form = new ItemForm();
		form.setId(itemId);
		form.setName(item.getName());
//		form.setPrice(item.getPrice());
		form.setStockQuantity(item.getStockQuantity());
		
		model.addAttribute("form", form);
		return "items/updateItemForm";
	}
	
	@PostMapping("items/{itemId}/edit")
	public String updateItem(@ModelAttribute("form") Item form) {
		Item item = new Item();
		
		item.setId(form.getId());
		item.setName(form.getName());
//		item.setPrice(form.getPrice());
		item.setStockQuantity(form.getStockQuantity());
		
//		itemService.saveItem(item);
		itemService.updateItem(form.getId(), form);
		return "redirect:/items";
	}
}
