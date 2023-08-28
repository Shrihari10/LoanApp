package com.wellsfargo.loanapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.loanapp.dto.EmployeeCardDTO;
import com.wellsfargo.loanapp.service.EmployeeCardService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/employeeCard")
public class EmployeeCardController {
	
	@Autowired
	private EmployeeCardService employeeCardService;

	/**
	 * API Endpoint to get list of all employee cards for particular employee
	 *
	 * @param employeeId - id of the employee
	 * @return response entity object with list of all Employee Card for employee with id employeeId, response status code and message
	 */
	@GetMapping("/{employeeId}/all")
	public ResponseEntity<List<EmployeeCardDTO>> getAllEmployeeCard(@PathVariable("employeeId") String employeeId)
	{
		return employeeCardService.getAllEmployeeCard(employeeId);
	}
}
