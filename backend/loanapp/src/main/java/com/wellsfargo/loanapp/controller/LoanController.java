package com.wellsfargo.loanapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.loanapp.model.ApplyLoanInputModel;
import com.wellsfargo.loanapp.service.LoanService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/loan")
public class LoanController {
	
	@Autowired
	private LoanService loanService;

	/**
	 * Function to apply loan for a particular employee for a particular loancard and item
	 *
	 * @param applyLoanInputModel - contains 3 parameters
	 *                            - employeeId - id for the employee who is applying
	 *                            - loanCardId - id of the loancard for which loan is applied
	 *                            - itemId - id of the item for which loan is applied
	 *
	 * @return response entity with response status code and message
	 */
	@PostMapping("/apply")
	public ResponseEntity<String> applyLoan(@RequestBody ApplyLoanInputModel applyLoanInputModel)
	{
		String employeeId = applyLoanInputModel.getEmployeeId();
		String loanCardId = applyLoanInputModel.getLoanCardId();
		String itemId = applyLoanInputModel.getItemId();
		return loanService.applyLoan(employeeId, loanCardId, itemId);
	}

}
