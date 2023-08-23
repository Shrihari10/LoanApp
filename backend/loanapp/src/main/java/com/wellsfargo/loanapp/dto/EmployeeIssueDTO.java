package com.wellsfargo.loanapp.dto;

import java.util.Date;
import com.wellsfargo.loanapp.model.EmployeeMaster;
import com.wellsfargo.loanapp.model.ItemMaster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeIssueDTO {
	
	private String issueId;
	private EmployeeMaster employee;
	private ItemMaster item;
	private Date issueDate;
	private Date returnDate;
}
