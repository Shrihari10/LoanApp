package com.wellsfargo.loanapp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wellsfargo.loanapp.dao.EmployeeRepository;
import com.wellsfargo.loanapp.model.Admin;
import com.wellsfargo.loanapp.model.EmployeeMaster;
import com.wellsfargo.loanapp.model.LoginModel;
import com.wellsfargo.loanapp.utils.Utils;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	AdminService adminService;
	
	public EmployeeMaster saveEmployee(EmployeeMaster employee)
	{
		employee.employeeID = Utils.generateUniqueId();
		EmployeeMaster createdEmployee = employeeRepository.save(employee);
		return createdEmployee;
	}
	
public String employeeLogin(LoginModel loginModel) {
		
	Optional<EmployeeMaster> optionalEmployee = employeeRepository.findById(loginModel.employeeID);
	if (optionalEmployee.isPresent()) {
		EmployeeMaster employeeMaster= optionalEmployee.get();
		if (employeeMaster.getPassword().equals(loginModel.password)) {
			return "Logging in successful";
		} else {
			return "Invalid password";
		}
	}
	return "Invalid user";
	}

public EmployeeMaster getEmployeeDetails(String employeeId) {
	// TODO Auto-generated method stub
	Optional<EmployeeMaster> optionalEmployee = employeeRepository.findById(employeeId);
	if (optionalEmployee.isPresent()) {
		EmployeeMaster employeeMaster= optionalEmployee.get();
		return employeeMaster;
	} else {
		return null;
	}
}

public String updateEmployeeDetails(String userName, String employeeId, EmployeeMaster employee) {
	
	if(adminService.verfiyAdminUsername(userName))
	{
		Optional<EmployeeMaster> optionalEmployee = employeeRepository.findById(employeeId);
		if (optionalEmployee.isPresent()) {
			EmployeeMaster updatedEmployee = optionalEmployee.get();
			updatedEmployee.setDateOfBirth(employee.getDateOfBirth());
			updatedEmployee.setDateOfJoining(employee.getDateOfJoining());
			updatedEmployee.setDepartment(employee.getDepartment());
			updatedEmployee.setDesignation(employee.getDesignation());
			updatedEmployee.setEmployeeName(employee.getEmployeeName());
			updatedEmployee.setGender(employee.getGender());
			updatedEmployee.setPassword(employee.getPassword());
			employeeRepository.save(updatedEmployee);
			return "Employee Details Updated";
		} else {
			return "Employee with " + employeeId +" not found!!! " ;
		}
	}
	return "Access Denied: Admin level access only!!!";
}

public List<EmployeeMaster> getAllEmployeeDetails(String userName) {
	if(adminService.verfiyAdminUsername(userName))
	{
		return employeeRepository.findAll();
	}
	return null;
}

public String deleteEmployee(String userName, String employeeId) {
	if(adminService.verfiyAdminUsername(userName))
	{
		Optional<EmployeeMaster> optionalEmployee = employeeRepository.findById(employeeId);
		if (optionalEmployee.isPresent()) {
			employeeRepository.delete(optionalEmployee.get());
			return "Employee with Id "+  employeeId +"Deleted Successfully !!!";
		} else {
			return "Employee with " + employeeId +" not found!!! " ;
		}
	}
	return "Access Denied: Admin level access only!!!";
}

}
