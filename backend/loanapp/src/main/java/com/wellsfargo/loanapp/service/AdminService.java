package com.wellsfargo.loanapp.service;

import org.springframework.http.ResponseEntity;

import com.wellsfargo.loanapp.dto.AdminDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminService {

	ResponseEntity<AdminDTO> validateAdmin(AdminDTO adminDto);

//	boolean verfiyAdminUsername(String userName);

	boolean verifyAdmin(UserDetails userDetails);

}
