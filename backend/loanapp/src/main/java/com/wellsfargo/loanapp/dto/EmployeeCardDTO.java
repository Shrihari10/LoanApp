package com.wellsfargo.loanapp.dto;

import java.util.Date;

import com.wellsfargo.loanapp.model.EmployeeMaster;
import com.wellsfargo.loanapp.model.LoanCardMaster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeCardDTO {

	private String employeeCardId;
	private EmployeeMaster employee;
	private LoanCardMaster loanCard;
	private Date cardIssueDate;

}
