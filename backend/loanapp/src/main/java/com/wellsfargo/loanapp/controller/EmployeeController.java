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
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> getEmployeeDetails(@RequestBody @PathVariable("employeeId") String employeeId ) {
		return employeeService.getEmployeeDetails(employeeId);
		
	}
	
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> updateEmployeeDetails(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String employeeId, @RequestBody EmployeeDTO employeeDto) {
		return employeeService.updateEmployeeDetails(userDetails,employeeId,employeeDto);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployeeDetails(@AuthenticationPrincipal UserDetails userDetails) {
		return employeeService.getAllEmployeeDetails(userDetails);
		
	}
	
	@DeleteMapping("{employeeId}")
	public ResponseEntity<EmployeeDTO> deleteEmployee(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String employeeId) {
		return employeeService.deleteEmployee(userDetails,employeeId);
		
	}
	 
}
