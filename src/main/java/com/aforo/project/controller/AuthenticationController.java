package com.aforo.project.controller;

import com.aforo.project.dto.LoginRequest;
import com.aforo.project.dto.LoginResponse;
import com.aforo.project.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginDTO) {
        try {
            @SuppressWarnings("unused")
			Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());
            String token = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new LoginResponse(token, "Login successful"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new LoginResponse(null, "Invalid username or password"));
        }
    }
}
