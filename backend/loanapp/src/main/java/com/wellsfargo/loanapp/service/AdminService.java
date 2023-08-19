package com.wellsfargo.loanapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wellsfargo.loanapp.dao.AdminRepository;
import com.wellsfargo.loanapp.model.Admin;
import com.wellsfargo.loanapp.model.GenericResponse;

@Service
public class AdminService {
	@Autowired
	AdminRepository adminRepository;
	
	public ResponseEntity<Admin> validateAdmin(Admin adminLogin) {
		Optional<Admin> optionalAdmin = adminRepository.findById(adminLogin.getUsername());
		if (optionalAdmin.isPresent()) {
			Admin admin = optionalAdmin.get();
			if (admin.getPassword().equals(adminLogin.getPassword())) {
				return ResponseGenerator.generateResponse(HttpStatus.OK, "Successfully Logged In", admin);
			} else {
				return ResponseGenerator.generateResponse(HttpStatus.UNAUTHORIZED, "Login Failed : Invalid password", null);
			}
		}
		return ResponseGenerator.generateResponse(HttpStatus.UNAUTHORIZED, "Login Failed : Invalid Admin Username", null);
	}
	
	public boolean verfiyAdminUsername(String userName)
	{
		Optional<Admin> optionalAdmin = adminRepository.findById(userName);
		if (optionalAdmin.isPresent()) {
			return true;
		}
		return false;
	}
}
