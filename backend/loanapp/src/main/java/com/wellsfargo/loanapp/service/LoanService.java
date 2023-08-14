package com.wellsfargo.loanapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.loanapp.model.EmployeeCardDetails;
import com.wellsfargo.loanapp.model.EmployeeIssueDetails;

import jakarta.transaction.Transactional;

@Service
public class LoanService {
	
	@Autowired
	private EmployeeCardService employeeCardService;
	
	@Autowired
	private EmployeeIssueService employeeIssueService;
	
	@Transactional
	public String applyLoan(String employeeId, String loanCardId, String itemId)
	{
		EmployeeCardDetails employeeCard = employeeCardService.addEmployeeCard(employeeId, loanCardId);
		EmployeeIssueDetails employeeIssue = employeeIssueService.addEmployeeIssue(employeeId, itemId);
		
		System.out.println(employeeCard+" "+employeeIssue);
		if(employeeCard!=null && employeeIssue!=null)
		{
			return "Loan Application successfull";
		}
		else if(employeeCard!=null)
		{
			return "Loan Application Unsuccessful: Invalid Loan Type";
		}
		if(employeeIssue!=null)
		{
			return "Loan Application Unsuccessful: Invalid Item";
		}
		return "Loan Application Unsuccessful: Invalid Loan Type and Item";
	}
}
