package com.wellsfargo.loanapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.loanapp.model.EmployeeMaster;
import com.wellsfargo.loanapp.model.ItemMaster;
import com.wellsfargo.loanapp.model.LoanCardMaster;
import com.wellsfargo.loanapp.service.LoanCardService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/loanCard")
public class LoanCardController {
	
	@Autowired
	public LoanCardService loanCardService;
	
	@GetMapping("/all")
	public List<LoanCardMaster> getAllLoanCards()
	{
		return loanCardService.getAllLoanCards();
	}
	
	@PostMapping("/add")
	public LoanCardMaster saveLoanCard(@RequestParam String userName, @RequestBody LoanCardMaster loanCard)
	{
	
		LoanCardMaster createdLoanCard = loanCardService.saveLoanCard(userName,loanCard);
		return createdLoanCard;
	}
	
	@PutMapping("/{loanCardId}")
	public String updateLoanCard(@RequestParam String userName, @PathVariable String loanCardId,@RequestBody LoanCardMaster loanCard) {
		return loanCardService.updateLoanCard(userName,loanCardId,loanCard);
		
	}

	@DeleteMapping("/{loanCardId}")
	public String deleteLoanCard(@RequestParam String userName, @PathVariable String loanCardId) {
		return loanCardService.deleteLoanCard(userName,loanCardId);
		
	}

}
