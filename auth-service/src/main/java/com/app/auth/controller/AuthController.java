package com.app.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.app.auth.entity.User;
import com.app.auth.exception.AuthException;
import com.app.auth.repository.UserRepository;
import com.app.auth.security.JwtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.Map;
import java.util.HashMap;
import com.app.auth.dto.LoginRequest;
import com.app.auth.dto.SignupRequest;
import jakarta.validation.Valid;
import com.app.auth.security.TokenBlacklistService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // 🔹 SIGNUP
    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupRequest request) {

    	if (userRepository.existsById(request.getEmail())) {
    	    throw new AuthException("User already exists");
    	}

    	User user = new User();
    	user.setName(request.getName());
    	user.setEmail(request.getEmail());
    	user.setPassword(passwordEncoder.encode(request.getPassword()));
    	user.setProvider("LOCAL");

    	userRepository.save(user);

    	return "User registered successfully";
    }

    // 🔹 LOGIN
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {

        User existing = userRepository.findById(request.getEmail())
                .orElseThrow(() -> new AuthException("User not found"));

        // ✅ IMPORTANT: Prevent Google users logging via password
        if (!"LOCAL".equals(existing.getProvider())) {
            throw new AuthException("Please login using Google");
        }

        if (!passwordEncoder.matches(request.getPassword(), existing.getPassword())) {
            throw new AuthException("Invalid password");
        }

        return jwtService.generateToken(existing.getEmail());
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        	tokenBlacklistService.blacklistToken(authHeader.substring(7));
        }
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing token");
        }
        String email = jwtService.extractEmail(authHeader.substring(7));
        User user = userRepository.findById(email)
                .orElseThrow(() -> new AuthException("User not found"));

        Map<String, String> profile = new HashMap<>();
        profile.put("email", user.getEmail());
        profile.put("name",  user.getName());
        profile.put("provider", user.getProvider());

        return ResponseEntity.ok(profile);
    }
}