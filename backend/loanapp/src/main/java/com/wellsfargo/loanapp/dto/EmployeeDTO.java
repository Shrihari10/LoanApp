package com.wellsfargo.loanapp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDTO {
	
	private String employeeID;
	private String employeeName;
	private String designation;
	private String department;
	private Character gender;
	private LocalDate dateOfBirth;
	private LocalDate dateOfJoining;
	private String password;

}
