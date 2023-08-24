package com.wellsfargo.loanapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.wellsfargo.loanapp.dto.ItemDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

public interface ItemService {

	ResponseEntity<List<ItemDTO>> getAllItems();

	ResponseEntity<ItemDTO> saveItem(UserDetails userDetails, ItemDTO itemDto);

	ResponseEntity<ItemDTO> updateItem(UserDetails userDetails, String itemId, ItemDTO itemDto);

	ResponseEntity<ItemDTO> deleteItem(UserDetails userDetails, String itemId);

}
