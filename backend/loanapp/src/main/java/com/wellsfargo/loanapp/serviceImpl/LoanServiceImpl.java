package com.wellsfargo.loanapp.serviceImpl;

import com.wellsfargo.loanapp.exception.NoDataFoundException;
import com.wellsfargo.loanapp.service.EmployeeCardService;
import com.wellsfargo.loanapp.service.EmployeeIssueService;
import com.wellsfargo.loanapp.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wellsfargo.loanapp.model.EmployeeCardDetails;
import com.wellsfargo.loanapp.model.EmployeeIssueDetails;

import jakarta.transaction.Transactional;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private EmployeeCardService employeeCardService;

	@Autowired
	private EmployeeIssueService employeeIssueService;

	@Transactional
	public ResponseEntity<String> applyLoan(String employeeId, String loanCardId, String itemId) throws NoDataFoundException
	{
		EmployeeCardDetails employeeCard = employeeCardService.addEmployeeCard(employeeId, loanCardId);
		EmployeeIssueDetails employeeIssue = employeeIssueService.addEmployeeIssue(employeeId, itemId, loanCardId);

		if(employeeCard==null && employeeIssue==null)
		{
			throw new NoDataFoundException("No Data to Display");
			//return ResponseGenerator.generateResponse(HttpStatus.NOT_FOUND, "Loan Application Unsuccessful: Invalid Loan Type and Item for employee", null);
		}
		else if(employeeCard==null)
		{
			throw new NoDataFoundException("No Loan Type for employee");
			//return ResponseGenerator.generateResponse(HttpStatus.NOT_FOUND, "Loan Application Unsuccessful: Invalid Loan Type for employee", null);
		}
		if(employeeIssue==null)
		{
			throw new NoDataFoundException("No Item for employee");
			//return ResponseGenerator.generateResponse(HttpStatus.NOT_FOUND, "Loan Application Unsuccessful: Invalid Item for employee", null);

		}

		return ResponseGenerator.generateResponse(HttpStatus.CREATED, "Loan Application successful", null);

//
	}
}
