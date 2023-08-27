package com.wellsfargo.loanapp.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.loanapp.config.JwtService;
import com.wellsfargo.loanapp.dao.EmployeeRepository;
import com.wellsfargo.loanapp.dto.EmployeeDTO;
import com.wellsfargo.loanapp.model.EmployeeMaster;
import com.wellsfargo.loanapp.model.LoginModel;
import com.wellsfargo.loanapp.token.Token;
import com.wellsfargo.loanapp.token.TokenRepository;
import com.wellsfargo.loanapp.token.TokenType;
import com.wellsfargo.loanapp.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.io.IOException;

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
    saveUserToken(createdEmployee, jwtToken);
    EmployeeDTO createdEmployeeDto = modelMapper.map(createdEmployee,EmployeeDTO.class);
    createdEmployeeDto.setPassword("");
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .employee(createdEmployeeDto)
        .build();
  }

  public AuthenticationResponse authenticate(LoginModel loginModel) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                loginModel.getEmployeeID(),
                loginModel.getPassword()
        )
    );
    EmployeeMaster employee = employeeRepository.findById(loginModel.getEmployeeID())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(employee);
    var refreshToken = jwtService.generateRefreshToken(employee);
    revokeAllUserTokens(employee);
    saveUserToken(employee, jwtToken);
    EmployeeDTO employeeDto = modelMapper.map(employee,EmployeeDTO.class);
    employeeDto.setPassword("");
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .employee(employeeDto)
        .build();
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
      if (jwtService.isTokenValid(refreshToken, employee)) {
        var accessToken = jwtService.generateToken(employee);
        revokeAllUserTokens(employee);
        saveUserToken(employee, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
