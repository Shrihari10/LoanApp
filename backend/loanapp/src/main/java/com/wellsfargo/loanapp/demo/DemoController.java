package com.wellsfargo.loanapp.demo;

import com.wellsfargo.loanapp.auth.Utils;
import com.wellsfargo.loanapp.model.EmployeeMaster;
import com.wellsfargo.loanapp.utils.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

  @GetMapping
  public ResponseEntity<String> sayHello(@AuthenticationPrincipal UserDetails userDetails) {
//    String employeeId = Utils.getEmployeeIdfromAuthentication(authentication);
//    System.out.println(employeeId);
    String employeeId = userDetails.getUsername();
    List<? extends GrantedAuthority> role = userDetails.getAuthorities().stream().toList();

    System.out.println(employeeId+" "+role.get(0).toString());
    return ResponseEntity.ok("Hello from secured endpoint");
  }

}
