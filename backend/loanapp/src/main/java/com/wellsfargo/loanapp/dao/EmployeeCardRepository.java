package com.wellsfargo.loanapp.dao;

import java.util.List;

import com.wellsfargo.loanapp.model.EmployeeIssueDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wellsfargo.loanapp.model.EmployeeCardDetails;
import com.wellsfargo.loanapp.model.EmployeeMaster;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCardDetails, String> {

	List<EmployeeCardDetails> findByEmployee(EmployeeMaster employeeMaster);

	@Query(value = "SELECT * from employee_card_details WHERE loan_id = :loanId",nativeQuery = true)
	List<EmployeeCardDetails> customfindbyLoanCardId(@Param("loanId") String loanCardId);
}
