package com.wellsfargo.loanapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.loanapp.model.ItemMaster;
import com.wellsfargo.loanapp.service.ItemService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	public ItemService itemService;
	
	@GetMapping("/all")
	public ResponseEntity<List<ItemMaster>> getAllItems()
	{
		return itemService.getAllItems();
	}
	
	@PostMapping("/add")
	public ResponseEntity<ItemMaster> saveItem(@RequestParam String userName, @RequestBody ItemMaster item)
	{
	
		return itemService.saveItem(userName, item);
	}
	
	@PutMapping("/{itemId}")
	public ResponseEntity<ItemMaster> updateItem(@RequestParam String userName, @PathVariable String itemId,@RequestBody ItemMaster item) {
		return itemService.updateItem(userName,itemId,item);
		
	}
	
	@DeleteMapping("/{itemId}")
	public ResponseEntity<ItemMaster> deleteItem(@RequestParam String userName, @PathVariable String itemId) {
		return itemService.deleteItem(userName,itemId);
		
	}
	

}
