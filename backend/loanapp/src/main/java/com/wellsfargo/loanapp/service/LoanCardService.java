package com.wellsfargo.loanapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.loanapp.dao.LoanCardRepository;
import com.wellsfargo.loanapp.model.EmployeeMaster;
import com.wellsfargo.loanapp.model.LoanCardMaster;
import com.wellsfargo.loanapp.utils.Utils;

@Service
public class LoanCardService {
	
	@Autowired
	LoanCardRepository loanCardRepository;
	
	@Autowired
	AdminService adminService;

	public List<LoanCardMaster> getAllLoanCards()
	{
		return loanCardRepository.findAll();
	}

	public LoanCardMaster saveLoanCard(String userName, LoanCardMaster loanCard) {
		if(adminService.verfiyAdminUsername(userName))
		{
			loanCard.setLoanId(Utils.generateUniqueId());
			return loanCardRepository.save(loanCard);
		}
		return null;
	}

	public String updateLoanCard(String userName, String loanCardId, LoanCardMaster loanCard) {
		if(adminService.verfiyAdminUsername(userName))
		{
			Optional<LoanCardMaster> optionalLoanCard = loanCardRepository.findById(loanCardId);
			if (optionalLoanCard.isPresent()) {
				LoanCardMaster updatedLoanCard = optionalLoanCard.get();
				updatedLoanCard.setDurationOfYears(loanCard.getDurationOfYears());
				updatedLoanCard.setLoanType(loanCard.getLoanType());
				return "Loan Card with Id "+loanCardId+" details Updated";
			} else {
				return "Loan Card with Id " + loanCardId +" not found!!! " ;
			}
		}
		return "Access Denied: Admin level access only!!!";
	}

	public String deleteLoanCard(String userName, String loanCardId) {
		if(adminService.verfiyAdminUsername(userName))
		{
			Optional<LoanCardMaster> optionalLoanCard = loanCardRepository.findById(loanCardId);
			if (optionalLoanCard.isPresent()) {
				loanCardRepository.delete(optionalLoanCard.get());
				return "Loan Card with Id "+loanCardId+" deleted successfully !!!";
			} else {
				return "Loan Card with Id " + loanCardId +" not found !!! " ;
			}
		}
		return "Access Denied: Admin level access only!!!";
	}
}
