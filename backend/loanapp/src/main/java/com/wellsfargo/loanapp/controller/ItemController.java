package com.wellsfargo.loanapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.loanapp.dto.ItemDTO;
import com.wellsfargo.loanapp.service.ItemService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/item")
public class ItemController {
	
	@Autowired
	public ItemService itemService;

	/**
	 * API Endpoint to get all items
	 *
	 * @return response entity object with all items, response status code and message
	 */
	@GetMapping("/all")
	public ResponseEntity<List<ItemDTO>> getAllItems()
	{
		return itemService.getAllItems();
	}

	/**
	 * API Endpoint to add item by admin
	 *
	 * @param userDetails - authenticated user details injected by SecurityContextHolder
	 * @param itemDto - details of item to be added
	 * @return response entity object with added item, response status code and message
	 */
	@PostMapping("/add")
	public ResponseEntity<ItemDTO> saveItem(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ItemDTO itemDto)
	{
	
		return itemService.saveItem(userDetails, itemDto);
	}

	/**
	 * API Endpoint to update item with id itemId
	 *
	 * @param userDetails - authenticated user details injected by SecurityContextHolder
	 * @param itemId - id of the item to be updated
	 * @param itemDto - updated details of item
	 * @return response entity object with updated item with id itemId, response status code and message
	 */
	@PutMapping("/{itemId}")
	public ResponseEntity<ItemDTO> updateItem(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String itemId,@RequestBody ItemDTO itemDto) {
		return itemService.updateItem(userDetails,itemId,itemDto);
		
	}

	/**
	 * API Endpoint to delete item with id itemId
	 *
	 * @param userDetails - authenticated user details injected by SecurityContextHolder
	 * @param itemId - id of the item to be deleted
	 * @return response entity object with response status code and message
	 */
	@DeleteMapping("/{itemId}")
	public ResponseEntity<ItemDTO> deleteItem(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String itemId) {
		return itemService.deleteItem(userDetails,itemId);
		
	}
	

}
