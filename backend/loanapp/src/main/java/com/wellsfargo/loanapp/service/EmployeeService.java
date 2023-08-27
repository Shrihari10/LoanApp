package com.wellsfargo.loanapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.wellsfargo.loanapp.dto.EmployeeDTO;
import com.wellsfargo.loanapp.model.LoginModel;
import org.springframework.security.core.userdetails.UserDetails;

public interface EmployeeService {

	ResponseEntity<EmployeeDTO> saveEmployee(EmployeeDTO employeeDto);

	ResponseEntity<EmployeeDTO> employeeLogin(LoginModel loginModel);

	ResponseEntity<EmployeeDTO> getEmployeeDetails(String employeeId);

	ResponseEntity<EmployeeDTO> updateEmployeeDetails(UserDetails userDetails, String employeeId, EmployeeDTO employeeDto);

	ResponseEntity<List<EmployeeDTO>> getAllEmployeeDetails(UserDetails userDetails);

	ResponseEntity<EmployeeDTO> deleteEmployee(UserDetails userDetails, String employeeId);

}