package com.wellsfargo.loanapp.auth;

import com.wellsfargo.loanapp.dto.EmployeeDTO;
import com.wellsfargo.loanapp.model.LoginModel;
import com.wellsfargo.loanapp.serviceImpl.ResponseGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class AuthenticationController {

  @Autowired
  private AuthenticationService service;

  /**
   * API endpoint to register employee or admin
   *
   * @param employeeDto - details of the employee
   * @return response entity with jwt access token, jwt refresh token, employeeDTO object and response status and message
   */
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
          @RequestBody EmployeeDTO employeeDto
  ) {
    AuthenticationResponse response = service.register(employeeDto);
    return ResponseGenerator.generateResponse(HttpStatus.OK, "Employee added successfully !!!",response);
  }

  /**
   * API endpoint to authenticate employee
   *
   * @param loginModel - username and password for employee
   * @return response entity with jwt access token, jwt refresh token, employee details and response status and message
   */
  @PostMapping("/authenticate/employee")
  public ResponseEntity<AuthenticationResponse> authenticateEmployee(
      @RequestBody LoginModel loginModel
  ) {
    return service.authenticateEmployee(loginModel);
  }

  /**
   * API endpoint to register authenticate admin
   *
   * @param loginModel - username and password for admin
   * @return response entity with jwt access token, jwt refresh token, admin details and response status and message
   */
  @PostMapping("/authenticate/admin")
  public ResponseEntity<AuthenticationResponse> authenticateAdmin(
          @RequestBody LoginModel loginModel
  ) {
    return service.authenticateAdmin(loginModel);
  }


  /**
   * API endpoint to generate new access token from a valid refreshToken
   *
   * @param request - the http request
   * @return response entity with refreshed jwt access token, jwt refresh token, admin details and response status and message
   * @throws IOException
   */
  @PostMapping("/refresh-token")
  public ResponseEntity<AuthenticationResponse> refreshToken(
      HttpServletRequest request
  ) throws IOException {
    return service.refreshToken(request);
  }


}
