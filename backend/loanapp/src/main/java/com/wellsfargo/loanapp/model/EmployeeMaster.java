package com.wellsfargo.loanapp.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMaster {
	
	@Id
	@Column(length = 6)
	private String employeeId;
	
	@Column(length = 20)
	private String employeeName;
	
	@Column(length = 25)
	private String designation;
	
	@Column(length = 25)
	private String department;
	
	private char gender;
	private Date dateOfBirth;
	private Date dateOfJoining;
	
	
	

}
