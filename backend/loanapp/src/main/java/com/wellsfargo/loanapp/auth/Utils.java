package com.wellsfargo.loanapp.auth;

import com.wellsfargo.loanapp.model.EmployeeMaster;
import org.springframework.security.core.Authentication;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public class Utils {

    public static String getEmployeeIdfromAuthentication(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        EmployeeMaster employee = (EmployeeMaster) principal;
        String employeeID = employee.getEmployeeID();
        return employeeID;
    }
}
