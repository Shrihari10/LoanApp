package com.wellsfargo.loanapp.service;

import com.wellsfargo.loanapp.exception.NoDataFoundException;
import org.springframework.http.ResponseEntity;

public interface LoanService {

	ResponseEntity<String> applyLoan(String employeeId, String loanCardId, String itemId) throws NoDataFoundException;

}
