package com.wellsfargo.loanapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApplyLoanInputModel {
	
	public String employeeId;
	public String loanCardId;
	public String itemId;
}
