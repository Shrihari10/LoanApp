package com.wellsfargo.loanapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.wellsfargo.loanapp.dto.LoanCardDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoanCardService {

	ResponseEntity<List<LoanCardDTO>> getAllLoanCards();

	ResponseEntity<LoanCardDTO> saveLoanCard(UserDetails userDetails, LoanCardDTO loanCardDto);

	ResponseEntity<LoanCardDTO> updateLoanCard(UserDetails userDetails, String loanCardId, LoanCardDTO loanCardDto);

	ResponseEntity<LoanCardDTO> deleteLoanCard(UserDetails userDetails, String loanCardId);

}
