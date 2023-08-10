package com.wellsfargo.loanapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMaster {
	
	@Id
	@Column(length = 6)
	private String itemId;
	
	@Column(length = 25)
	private String itemDescription;
	private char issueStatus;
	
	@Column(length = 25)
	private String itemMake;
	
	@Column(length = 20)
	private String itemCategory;
	
	@Column(length = 6)
	private int itemValuation;
}
