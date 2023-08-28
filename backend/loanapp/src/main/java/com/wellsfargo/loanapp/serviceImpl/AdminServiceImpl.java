package com.wellsfargo.loanapp.serviceImpl;

import java.util.List;

import com.wellsfargo.loanapp.service.AdminService;
import com.wellsfargo.loanapp.utils.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
	public boolean verifyAdmin(UserDetails userDetails)
	{
		String employeeId = userDetails.getUsername();
    	List<? extends GrantedAuthority> role = userDetails.getAuthorities().stream().toList();
		return role.get(0).toString().equals(Role.ADMIN.name());
	}

}
