package com.example.bzoor_second.controllers;

import com.example.bzoor_second.handler.IncorrectCredentials;
import com.example.bzoor_second.models.AuthenticationResponse;
import com.example.bzoor_second.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtToken;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody Map<String, Object> user) throws Exception {
        System.out.println(user.get("userName") + " " + user.get("password"));
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.get("userName"), user.get("password"))
            );

        }catch(BadCredentialsException badCredentialsException){
            throw(new IncorrectCredentials("user enters invalid credentials"));
        }
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.get("userName").toString());

        final String jwt = this.jwtToken.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
