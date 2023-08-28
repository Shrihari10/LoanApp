package com.wellsfargo.loanapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.loanapp.dto.LoanCardDTO;
import com.wellsfargo.loanapp.service.LoanCardService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/loanCard")
public class LoanCardController {
	
	@Autowired
	public LoanCardService loanCardService;

	/**
	 * API Endpoint to get all loanCards
	 *
	 * @return response entity object with all loanCards, response status code and message
	 */
	@GetMapping("/all")
	public ResponseEntity<List<LoanCardDTO>> getAllLoanCards()
	{
		return loanCardService.getAllLoanCards();
	}

	/**
	 * API Endpoint to add a loanCard
	 *
	 * @param userDetails - authenticated user details injected by SecurityContextHolder
	 * @param loanCardDto - details of loanCard to be added
	 * @return response entity object with added loanCard, response status code and message
	 */
	@PostMapping("/add")
	public ResponseEntity<LoanCardDTO> saveLoanCard(@AuthenticationPrincipal UserDetails userDetails, @RequestBody LoanCardDTO loanCardDto)
	{
	
		return loanCardService.saveLoanCard(userDetails,loanCardDto);
	}

	/**
	 * API Endpoint to update a loanCard with id loanCardId
	 *
	 * @param userDetails - authenticated user details injected by SecurityContextHolder
	 * @param loanCardId - id of loanCard to be updated
	 * @param loanCardDto - details of updated loanCard
	 * @return response entity object with updated loanCard, response status code and message
	 */
	@PutMapping("/{loanCardId}")
	public ResponseEntity<LoanCardDTO> updateLoanCard(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String loanCardId,@RequestBody LoanCardDTO loanCardDto) {
		return loanCardService.updateLoanCard(userDetails,loanCardId,loanCardDto);
		
	}

	/**
	 * API Endpoint to delete a loanCard with id loanCardId
	 *
	 * @param userDetails - authenticated user details injected by SecurityContextHolder
	 * @param loanCardId - id of loanCard to be deleted
	 * @return response entity object with response status code and message
	 */
	@DeleteMapping("/{loanCardId}")
	public ResponseEntity<LoanCardDTO> deleteLoanCard(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String loanCardId) {
		return loanCardService.deleteLoanCard(userDetails,loanCardId);
		
	}

}
