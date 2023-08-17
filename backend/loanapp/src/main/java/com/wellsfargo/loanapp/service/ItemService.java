package com.wellsfargo.loanapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.loanapp.dao.AdminRepository;
import com.wellsfargo.loanapp.dao.ItemRepository;
import com.wellsfargo.loanapp.model.ItemMaster;
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
	

}
