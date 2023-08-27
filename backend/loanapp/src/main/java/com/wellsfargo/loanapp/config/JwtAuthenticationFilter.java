package com.wellsfargo.loanapp.config;

import com.wellsfargo.loanapp.token.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.beans.Transient;
import java.io.IOException;
import java.security.Security;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private TokenRepository tokenRepository;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    if (request.getServletPath().contains("/api/v1/auth")) {
      filterChain.doFilter(request, response);
      return;
    }
    System.out.println("Hello w!");
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userName;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      System.out.println("Hello wf!");
      return;
    }

    jwt = authHeader.substring(7);

    System.out.println("Hello wt! "+jwt);

    userName = jwtService.extractUsername(jwt);

    System.out.println("Hello wt! "+userName);


    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
      System.out.println("Hello wt4! "+userName);
//
//      var isTokenValid = tokenRepository.findByToken(jwt)
//          .map(t -> !t.isExpired() && !t.isRevoked())
//          .orElse(false);
//      System.out.println("Hello wt5! "+isTokenValid);
      if (jwtService.isTokenValid(jwt, userDetails)) {
        System.out.println("Hello wt5! ");
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        System.out.println("Hello wt6! "+authToken);

        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        System.out.println("Hello wt7! "+authToken);
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    System.out.println("Hello wt8! "+" "+SecurityContextHolder.getContext().getAuthentication());
    filterChain.doFilter(request, response);
  }
}
