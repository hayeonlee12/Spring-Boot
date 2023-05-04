package com.codingbox.springshop.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.springshop.ItemRepository;
import com.codingbox.springshop.domain.Item;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	// 메서드 : findItems()
	// return : List
	public List<Item> findItems() {
		return itemRepository.findAll();
	}

}
