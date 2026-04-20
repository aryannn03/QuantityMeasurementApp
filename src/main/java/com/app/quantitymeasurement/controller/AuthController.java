package com.app.quantitymeasurement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.repository.UserRepository;
import com.app.quantitymeasurement.security.JwtService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔹 SIGNUP
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {

        if (userRepository.existsById(user.getEmail())) {
            throw new QuantityMeasurementException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProvider("LOCAL");

        userRepository.save(user);

        return "User registered successfully";
    }

    // 🔹 LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User existing = userRepository.findById(user.getEmail())
                .orElseThrow(() -> new QuantityMeasurementException("User not found"));

        // ✅ IMPORTANT: Prevent Google users logging via password
        if (!"LOCAL".equals(existing.getProvider())) {
            throw new QuantityMeasurementException("Please login using Google");
        }

        if (!passwordEncoder.matches(user.getPassword(), existing.getPassword())) {
            throw new QuantityMeasurementException("Invalid password");
        }

        return jwtService.generateToken(existing.getEmail());
    }
}