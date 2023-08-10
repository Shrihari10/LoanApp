package com.wellsfargo.loanapp.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.loanapp.dao.EmployeeRepository;
import com.wellsfargo.loanapp.model.EmployeeMaster;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public EmployeeMaster saveEmployee(EmployeeMaster employee)
	{
		employee.employeeId = generateUniqueId();
		EmployeeMaster createdEmployee = employeeRepository.save(employee);
		return createdEmployee;
	}

	private String generateUniqueId() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid.substring(0,6);
	}
}
