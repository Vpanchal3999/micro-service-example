package com.synoverge.authservice.controller;

import com.synoverge.authservice.model.dto.AuthRequest;
import com.synoverge.authservice.model.dto.JwtResponse;
import com.synoverge.authservice.securityService.UserInfoService;
import com.synoverge.authservice.serviceImpl.JwtService;
import com.synoverge.authservice.serviceImpl.UserServiceImpl;
import com.synoverge.common.dtos.BaseResponseEntity;
import com.synoverge.common.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.baseUrl)
public class AuthController {
    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }


    @PostMapping("/auth/login")
    public ResponseEntity<BaseResponseEntity> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        BaseResponseEntity baseResponse;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUserName());
                JwtResponse response = new JwtResponse(token, authRequest.getUserName());
                baseResponse = new BaseResponseEntity(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), "Login Successfully with token generation!!", response);
                return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
            } else {
                throw new UsernameNotFoundException("invalid user request !");
            }
        } catch (BadCredentialsException ex) {
            baseResponse = new BaseResponseEntity(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), "Credential doesn't match!!..", ex.getMessage());
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
