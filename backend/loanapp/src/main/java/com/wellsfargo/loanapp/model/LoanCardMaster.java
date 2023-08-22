package com.wellsfargo.loanapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanCardMaster {
	
	@Id
	@Column(length = 6)
	private String loanId;
	
	@Column(length = 15)
	private String loanType;
	
	@Column(length = 2)
	private int durationOfYears;
}
