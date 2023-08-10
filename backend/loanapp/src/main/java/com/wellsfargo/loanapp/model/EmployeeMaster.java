package com.wellsfargo.loanapp.model;

import java.sql.Date;

import org.antlr.v4.runtime.misc.NotNull;

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
	public String employeeId;
	
	@Column(length = 20, nullable = false)
	private String employeeName;
	
	@Column(length = 25, nullable = false)
	private String designation;
	
	@Column(length = 25, nullable = false)
	private String department;
	
	@Column(nullable = false)
	private char gender;
	
	@Column(nullable = false)
	private Date dateOfBirth;
	
	@Column(nullable = false)
	private Date dateOfJoining;
	
	
	

}
