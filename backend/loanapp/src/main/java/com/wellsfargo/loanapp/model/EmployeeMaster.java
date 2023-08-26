package com.wellsfargo.loanapp.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeMaster {
	

	@Id
	@Column(length = 6)
//	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
	private String employeeID;
	
	@Column(length = 20)
	private String employeeName;
	
	@Column(length = 25)
	private String designation;
	
	@Column(length = 25)
	private String department;
	
	@Column()
	private Character gender;
	
	@Column()
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@Column()
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate dateOfJoining;
	
	@Column()
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeeCardDetails> employeeCards;

	@JsonIgnore
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeeIssueDetails> employeeIssues;
}
