package com.wellsfargo.loanapp.dto;

import java.util.Date;
import com.wellsfargo.loanapp.model.EmployeeMaster;
import com.wellsfargo.loanapp.model.ItemMaster;

public class EmployeeIssueDTO {
	
	private String issueId;
	private EmployeeMaster employee;
	private ItemMaster item;
	private Date issueDate;
	private Date returnDate;
	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	public EmployeeMaster getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeMaster employee) {
		this.employee = employee;
	}
	public ItemMaster getItem() {
		return item;
	}
	public void setItem(ItemMaster item) {
		this.item = item;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public EmployeeIssueDTO(String issueId, EmployeeMaster employee, ItemMaster item, Date issueDate, Date returnDate) {
		super();
		this.issueId = issueId;
		this.employee = employee;
		this.item = item;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}
	public EmployeeIssueDTO() {
		super();
	}
	
	
	
}
