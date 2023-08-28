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

	/**
	 * API Endpoint to get list of all employee issues for particular employee
	 *
	 * @param employeeId - id of the employee
	 * @return response entity object with list of all employee issues for employee with id employeeId, response status code and message
	 */
	@GetMapping("/{employeeId}/all")
	public ResponseEntity<List<EmployeeIssueDTO>> getAllEmployeeIssue(@PathVariable("employeeId") String employeeId)
	{
		return employeeIssueService.getAllEmployeeIssue(employeeId);
	}
	
}
