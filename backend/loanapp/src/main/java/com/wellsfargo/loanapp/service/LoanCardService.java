package com.wellsfargo.loanapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.loanapp.dao.LoanCardRepository;
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
}
