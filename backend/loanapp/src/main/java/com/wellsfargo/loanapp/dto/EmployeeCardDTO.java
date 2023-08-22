package com.wellsfargo.loanapp.dto;

import java.util.Date;

import com.wellsfargo.loanapp.model.EmployeeMaster;
import com.wellsfargo.loanapp.model.LoanCardMaster;

public class EmployeeCardDTO {
	
	private String employeeCardId;
	private EmployeeMaster employee;
	private LoanCardMaster loanCard;
	private Date cardIssueDate;
	
	public String getEmployeeCardId() {
		return employeeCardId;
	}

	public void setEmployeeCardId(String employeeCardId) {
		this.employeeCardId = employeeCardId;
	}

	public EmployeeMaster getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeMaster employee) {
		this.employee = employee;
	}

	public LoanCardMaster getLoanCard() {
		return loanCard;
	}

	public void setLoanCard(LoanCardMaster loanCard) {
		this.loanCard = loanCard;
	}

	public Date getCardIssueDate() {
		return cardIssueDate;
	}

	public void setCardIssueDate(Date cardIssueDate) {
		this.cardIssueDate = cardIssueDate;
	}

	public EmployeeCardDTO(String employeeCardId, EmployeeMaster employee, LoanCardMaster loanCard,
			Date cardIssueDate) {
		super();
		this.employeeCardId = employeeCardId;
		this.employee = employee;
		this.loanCard = loanCard;
		this.cardIssueDate = cardIssueDate;
	}

	public EmployeeCardDTO() {
		super();
	}
	
	

	
	
	
}
