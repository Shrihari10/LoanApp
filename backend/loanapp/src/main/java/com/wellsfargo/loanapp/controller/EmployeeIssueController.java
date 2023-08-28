package com.wellsfargo.loanapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.loanapp.dto.EmployeeIssueDTO;
import com.wellsfargo.loanapp.service.EmployeeIssueService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/employeeIssue")
public class EmployeeIssueController {
	
	@Autowired
	private EmployeeIssueService employeeIssueService;

	@GetMapping("/{employeeId}/all")
	public ResponseEntity<List<EmployeeIssueDTO>> getAllEmployeeIssue(@PathVariable("employeeId") String employeeId)
	{
		return employeeIssueService.getAllEmployeeIssue(employeeId);
	}
	
}
