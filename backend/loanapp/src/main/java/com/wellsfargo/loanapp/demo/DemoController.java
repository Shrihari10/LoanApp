package com.wellsfargo.loanapp.demo;

import com.wellsfargo.loanapp.auth.Utils;
import com.wellsfargo.loanapp.model.EmployeeMaster;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

  @GetMapping
  public ResponseEntity<String> sayHello(Authentication authentication) {
    String employeeId = Utils.getEmployeeIdfromAuthentication(authentication);
    System.out.println(employeeId);
    return ResponseEntity.ok("Hello from secured endpoint");
  }

}
