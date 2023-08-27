package com.wellsfargo.loanapp.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.loanapp.config.JwtService;
import com.wellsfargo.loanapp.dao.EmployeeRepository;
import com.wellsfargo.loanapp.dto.EmployeeDTO;
import com.wellsfargo.loanapp.model.EmployeeMaster;
import com.wellsfargo.loanapp.model.LoginModel;
import com.wellsfargo.loanapp.service.ResponseGenerator;
import com.wellsfargo.loanapp.token.Token;
import com.wellsfargo.loanapp.token.TokenRepository;
import com.wellsfargo.loanapp.token.TokenType;
import com.wellsfargo.loanapp.utils.Role;
import com.wellsfargo.loanapp.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private TokenRepository tokenRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private ModelMapper modelMapper;

  public AuthenticationResponse register(EmployeeDTO employeeDto) {

    employeeDto.setEmployeeID(Utils.generateUniqueId());
    employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
    EmployeeMaster employee = modelMapper.map(employeeDto, EmployeeMaster.class);
    EmployeeMaster createdEmployee = employeeRepository.save(employee);
    var jwtToken = jwtService.generateToken(createdEmployee);
    var refreshToken = jwtService.generateRefreshToken(createdEmployee);
    EmployeeDTO createdEmployeeDto = modelMapper.map(createdEmployee,EmployeeDTO.class);
    createdEmployeeDto.setPassword("");
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .employee(createdEmployeeDto)
        .build();
  }

  public Optional<EmployeeMaster> authenticate(LoginModel loginModel) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                loginModel.getEmployeeID(),
                loginModel.getPassword()
        )
    );
    Optional<EmployeeMaster> optionalEmployee = employeeRepository.findById(loginModel.getEmployeeID());
    return optionalEmployee;
  }

  public ResponseEntity<AuthenticationResponse> authenticateEmployee(LoginModel loginModel) {
    Optional<EmployeeMaster> optionalEmployee = authenticate(loginModel);
    if(optionalEmployee.isEmpty() || !optionalEmployee.get().getRole().equals(Role.EMPLOYEE))
    {
      return ResponseGenerator.generateResponse(HttpStatus.OK, "Invalid Credentials: No Employee Found !!!",null);
    }
    var jwtToken = jwtService.generateToken(optionalEmployee.get());
    var refreshToken = jwtService.generateRefreshToken(optionalEmployee.get());
    EmployeeDTO employeeDto = modelMapper.map(optionalEmployee.get(),EmployeeDTO.class);
    employeeDto.setPassword("");
    AuthenticationResponse response = AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .employee(employeeDto)
            .build();
    return ResponseGenerator.generateResponse(HttpStatus.OK, "Employee authenticated successfully !!!",response);
  }

  public ResponseEntity<AuthenticationResponse> authenticateAdmin(LoginModel loginModel) {
    Optional<EmployeeMaster> optionalEmployee = authenticate(loginModel);
    if(optionalEmployee.isEmpty() || !optionalEmployee.get().getRole().equals(Role.ADMIN))
    {
      return ResponseGenerator.generateResponse(HttpStatus.OK, "Invalid Credentials: No Admin Found!!!",null);
    }
    var jwtToken = jwtService.generateToken(optionalEmployee.get());
    var refreshToken = jwtService.generateRefreshToken(optionalEmployee.get());
    EmployeeDTO employeeDto = modelMapper.map(optionalEmployee.get(),EmployeeDTO.class);
    employeeDto.setPassword("");
    AuthenticationResponse response = AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .employee(employeeDto)
            .build();
    return ResponseGenerator.generateResponse(HttpStatus.OK, "Employee authenticated successfully !!!",response);
  }


  private void saveUserToken(EmployeeMaster employee, String jwtToken) {
    var token = Token.builder()
        .employee(employee)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(EmployeeMaster employee) {
    var validUserTokens = tokenRepository.findAllValidTokenByEmployee(employee.getEmployeeID());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String employeeId;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    employeeId = jwtService.extractUsername(refreshToken);
    if (employeeId != null) {
      var employee = this.employeeRepository.findById(employeeId)
              .orElseThrow();
      EmployeeDTO employeeDto = modelMapper.map(employee,EmployeeDTO.class);
      employeeDto.setPassword("");
      if (jwtService.isTokenValid(refreshToken, employee)) {
        var accessToken = jwtService.generateToken(employee);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .employee(employeeDto)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
