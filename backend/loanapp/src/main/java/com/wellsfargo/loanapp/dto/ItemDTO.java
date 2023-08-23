package com.wellsfargo.loanapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemDTO {
	
	private String itemId;
	private String itemDescription;
	private char issueStatus;
	private String itemMake;
	private String itemCategory;
	private int itemValuation;
}
