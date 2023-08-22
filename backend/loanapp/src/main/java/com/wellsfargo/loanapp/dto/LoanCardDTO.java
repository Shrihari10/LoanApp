package com.wellsfargo.loanapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanCardDTO {
	
	private String loanId;
	private String loanType;
	private int durationOfYears;
}
