package com.wellsfargo.loanapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.loanapp.model.EmployeeCardDetails;
import com.wellsfargo.loanapp.model.EmployeeIssueDetails;
import com.wellsfargo.loanapp.service.EmployeeIssueService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/employeeIssue")
public class EmployeeIssueController {
	
	@Autowired
	private EmployeeIssueService employeeIssueService;
	
	@PostMapping("/add")
	public EmployeeIssueDetails addEmployeeCard(String employeeId, String itemId)
	{
		return employeeIssueService.addEmployeeIssue(employeeId,itemId);
	}
	
}
