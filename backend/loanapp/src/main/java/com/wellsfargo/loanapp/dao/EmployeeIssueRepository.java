package com.wellsfargo.loanapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.loanapp.model.EmployeeIssueDetails;

@Repository
public interface EmployeeIssueRepository extends JpaRepository<EmployeeIssueDetails, String> {

}
