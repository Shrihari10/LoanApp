package com.wellsfargo.loanapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.wellsfargo.loanapp.dto.EmployeeDTO;
import com.wellsfargo.loanapp.model.LoginModel;

<<<<<<< HEAD
public interface EmployeeService {
=======
@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	AdminService adminService;
	
	public ResponseEntity<EmployeeMaster> saveEmployee(EmployeeMaster employee)
	{
		employee.setEmployeeID(Utils.generateUniqueId());
		EmployeeMaster createdEmployee = employeeRepository.save(employee);
		return ResponseGenerator.generateResponse(HttpStatus.CREATED, "Employee created successfully !!!", createdEmployee);
	}
	
public ResponseEntity<EmployeeMaster> employeeLogin(LoginModel loginModel) {
		
	Optional<EmployeeMaster> optionalEmployee = employeeRepository.findById(loginModel.employeeID);
	if (optionalEmployee.isPresent()) {
		EmployeeMaster employeeMaster= optionalEmployee.get();
		if (employeeMaster.getPassword().equals(loginModel.password)) {
			return ResponseGenerator.generateResponse(HttpStatus.OK, "Employee login successfull !!!", employeeMaster);
		} else {
			return ResponseGenerator.generateResponse(HttpStatus.OK, "Employee login failed : Invalid Password !!!", null);
		}
	}
	return ResponseGenerator.generateResponse(HttpStatus.OK, "Employee login failed : Invalid User !!!", null);
	}
>>>>>>> 314bf7ec769c0e1af11a8685f3ba1240b8774dcc

	ResponseEntity<EmployeeDTO> saveEmployee(EmployeeDTO employeeDto);

	ResponseEntity<EmployeeDTO> employeeLogin(LoginModel loginModel);

	ResponseEntity<EmployeeDTO> getEmployeeDetails(String employeeId);

	ResponseEntity<EmployeeDTO> updateEmployeeDetails(String userName, String employeeId, EmployeeDTO employeeDto);

	ResponseEntity<List<EmployeeDTO>> getAllEmployeeDetails(String userName);

	ResponseEntity<EmployeeDTO> deleteEmployee(String userName, String employeeId);

}
