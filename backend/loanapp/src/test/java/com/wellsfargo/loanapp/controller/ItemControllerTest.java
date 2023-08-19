package com.wellsfargo.loanapp.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.loanapp.model.GenericResponse;
import com.wellsfargo.loanapp.model.ItemMaster;
import com.wellsfargo.loanapp.service.ItemService;
import com.wellsfargo.loanapp.service.ResponseEntityGenerator;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ItemService itemService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	public ItemMaster getItem()
	{
		return new ItemMaster();
	}
	
	@Test
	public void getAllItems() throws Exception {
		
		List<Object> itemList = new ArrayList<>();
		itemList.add(getItem());
		
		ResponseEntity<GenericResponse<Object>> response = ResponseEntityGenerator.generateResponse(HttpStatus.OK, null,itemList);
		
		when(itemService.getAllItems()).thenReturn(response);
		
		MvcResult result = mvc.perform(get("/item/all").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(objectMapper.writeValueAsString(response.getBody())))
				.andReturn();
		
		verify(itemService,times(1)).getAllItems();
		
		
	}

}
