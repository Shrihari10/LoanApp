package com.wellsfargo.loanapp.dto;

public class LoanCardDTO {
	
	private String loanId;
	private String loanType;
	private int durationOfYears;
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public int getDurationOfYears() {
		return durationOfYears;
	}
	public void setDurationOfYears(int durationOfYears) {
		this.durationOfYears = durationOfYears;
	}
	public LoanCardDTO(String loanId, String loanType, int durationOfYears) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
		this.durationOfYears = durationOfYears;
	}
	public LoanCardDTO() {
		super();
	}
	
}
