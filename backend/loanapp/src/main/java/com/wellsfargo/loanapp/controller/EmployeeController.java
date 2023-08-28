package com.wellsfargo.loanapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.loanapp.dto.EmployeeDTO;
import com.wellsfargo.loanapp.service.EmployeeService;



@RestController
@Validated
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/employee/")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	/**
	 * API Endpoint to get employee details for particular employee
	 *
	 * @param employeeId - id of the employee
	 * @return response entity object with employee details for employee with id employeeId, response status code and message
	 */
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> getEmployeeDetails(@RequestBody @PathVariable("employeeId") String employeeId ) {
		return employeeService.getEmployeeDetails(employeeId);
		
	}

	/**
	 * API Endpoint to update employee details for particular employee
	 *
	 * @param userDetails - authenticated user details injected by SecurityContextHolder
	 * @param employeeId - id of the employee
	 * @param employeeDto - updated employee object
	 * @return response entity object with updated employee details for employee with id employeeId, response status code and message
	 */
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> updateEmployeeDetails(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String employeeId, @RequestBody EmployeeDTO employeeDto) {
		return employeeService.updateEmployeeDetails(userDetails,employeeId,employeeDto);
		
	}

	/**
	 * API Endpoint to get list of all employee details
	 *
	 * @param userDetails - authenticated user details injected by SecurityContextHolder
	 * @return response entity object with list of all employee details, response status code and message
	 */
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployeeDetails(@AuthenticationPrincipal UserDetails userDetails) {
		return employeeService.getAllEmployeeDetails(userDetails);
		
	}

	/**
	 * API Endpoint to delete employee details for particular employee
	 *
	 * @param userDetails - authenticated user details injected by SecurityContextHolder
	 * @param employeeId - id of the employee
	 * @return response entity object with response status code and message
	 */
	@DeleteMapping("{employeeId}")
	public ResponseEntity<EmployeeDTO> deleteEmployee(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String employeeId) {
		return employeeService.deleteEmployee(userDetails,employeeId);
		
	}
	 
}
