package com.wellsfargo.loanapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.loanapp.dao.AdminRepository;
import com.wellsfargo.loanapp.dao.ItemRepository;
import com.wellsfargo.loanapp.model.ItemMaster;
import com.wellsfargo.loanapp.model.LoanCardMaster;
import com.wellsfargo.loanapp.utils.Utils;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	AdminService adminService;

	public List<ItemMaster> getAllItems()
	{
		return itemRepository.findAll();
	}

	public ItemMaster saveItem(String userName, ItemMaster item) {
		if(adminService.verfiyAdminUsername(userName))
		{
			item.setItemId(Utils.generateUniqueId());
			return itemRepository.save(item);
		}
		return null;
	}

	public String updateItem(String userName, String itemId, ItemMaster item) {
		if(adminService.verfiyAdminUsername(userName))
		{
			Optional<ItemMaster> optionaItem = itemRepository.findById(itemId);
			if (optionaItem.isPresent()) {
				ItemMaster updatedItem = optionaItem.get();
				updatedItem.setIssueStatus(item.getIssueStatus());
				updatedItem.setItemCategory(item.getItemCategory());
				updatedItem.setItemDescription(item.getItemDescription());
				updatedItem.setItemMake(item.getItemMake());
				updatedItem.setItemValuation(item.getItemValuation());
				itemRepository.save(updatedItem);
				return "Item with Id "+itemId+" details Updated";
			} else {
				return "Item with Id " + itemId +" not found!!! " ;
			}
		}
		return "Access Denied: Admin level access only!!!";
	}

	public String deleteItem(String userName, String itemId) {
		if(adminService.verfiyAdminUsername(userName))
		{
			Optional<ItemMaster> optionaItem = itemRepository.findById(itemId);
			if (optionaItem.isPresent()) {
				itemRepository.delete(optionaItem.get());
				return "Item with Id "+itemId+" deleted successfully";
			} else {
				return "Item with Id " + itemId +" not found!!! " ;
			}
		}
		return "Access Denied: Admin level access only!!!";
	}
	

}
