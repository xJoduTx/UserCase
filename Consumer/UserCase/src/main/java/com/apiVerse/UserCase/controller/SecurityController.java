package com.apiVerse.UserCase.controller;

import com.apiVerse.UserCase.DTO.SigninRequest;
import com.apiVerse.UserCase.DTO.SignupRequest;
import com.apiVerse.UserCase.entity.User;
import com.apiVerse.UserCase.repository.UserRepo;
import com.apiVerse.UserCase.security.JwtCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtCore jwtCore;
    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        if (userRepository.existsUserByUsername(signupRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different name");
        }
        if (userRepository.existsUserByPhoneNumber(signupRequest.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different PhoneNumber");}
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPhoneNumber(signupRequest.getPhoneNumber());
        String hashed = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashed);
        userRepository.save(user);
        return ResponseEntity.ok("Success, baby");
    }

    @PostMapping("/signin")
    ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }
}

