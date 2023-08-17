package com.wellsfargo.loanapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.loanapp.model.LoginModel;
import com.wellsfargo.loanapp.model.EmployeeMaster;
import com.wellsfargo.loanapp.service.EmployeeService;

import jakarta.validation.Valid;



@RestController
@Validated
@CrossOrigin("http://localhost:3000")
@RequestMapping("/employee/")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/add")
	//public String saveEmployee(@RequestBody EmployeeMaster employee)
	public ResponseEntity<EmployeeMaster> saveEmployee(@Valid @RequestBody EmployeeMaster employee, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<> (employee, HttpStatus.BAD_REQUEST);
		}
		else {
		EmployeeMaster createdEmployee = employeeService.saveEmployee(employee);
		return new ResponseEntity<> (createdEmployee, HttpStatus.OK);
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginModel loginModel) {
		return employeeService.employeeLogin(loginModel);
	}
	
	@GetMapping("/{employeeId}")
	public EmployeeMaster getEmployeeDetails(@RequestBody @PathVariable("employeeId") String employeeId ) {
		return employeeService.getEmployeeDetails(employeeId);
		
	}
	
	@PutMapping("/{employeeId}")
	public String updateEmployeeDetails( @PathVariable String employeeId,@RequestBody EmployeeMaster employee) {
		return employeeService.updateEmployeeDetails(employeeId,employee);
		
	}
	
}
