package com.wellsfargo.loanapp.service;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminService {
	boolean verifyAdmin(UserDetails userDetails);

}
