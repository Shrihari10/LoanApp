package com.wellsfargo.loanapp.dto;

public class ItemDTO {
	
	private String itemId;
	private String itemDescription;
	private char issueStatus;
	private String itemMake;
	private String itemCategory;
	private int itemValuation;
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public char getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(char issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getItemMake() {
		return itemMake;
	}
	public void setItemMake(String itemMake) {
		this.itemMake = itemMake;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public int getItemValuation() {
		return itemValuation;
	}
	public void setItemValuation(int itemValuation) {
		this.itemValuation = itemValuation;
	}
	public ItemDTO(String itemId, String itemDescription, char issueStatus, String itemMake, String itemCategory,
			int itemValuation) {
		super();
		this.itemId = itemId;
		this.itemDescription = itemDescription;
		this.issueStatus = issueStatus;
		this.itemMake = itemMake;
		this.itemCategory = itemCategory;
		this.itemValuation = itemValuation;
	}
	public ItemDTO() {
		super();
	}
	
	
}
