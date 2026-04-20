package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityDTO;
import java.util.List;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/quantities")
@CrossOrigin(origins = "*")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // ✅ Helper — gets email from JWT (set by JwtFilter into SecurityContext)
    private String getEmail(Principal principal){
        return (principal != null) ? principal.getName() : null;
    }

    @PostMapping("/compare")
    public boolean compare(@Valid @RequestBody QuantityDTO[] quantities, Principal principal){
        return service.compare(quantities[0], quantities[1], getEmail(principal));
    }

    @PostMapping("/convert")
    public QuantityDTO convert(@Valid @RequestBody QuantityDTO quantity,
                               @RequestParam String targetUnit, Principal principal){
        return service.convert(quantity, targetUnit, getEmail(principal));
    }

    @PostMapping("/add")
    public QuantityDTO add(@Valid @RequestBody QuantityDTO[] quantities, Principal principal){
        return service.add(quantities[0], quantities[1], getEmail(principal));
    }

    @PostMapping("/subtract")
    public QuantityDTO subtract(@Valid @RequestBody QuantityDTO[] quantities, Principal principal){
        return service.subtract(quantities[0], quantities[1], getEmail(principal));
    }

    @PostMapping("/divide")
    public double divide(@Valid @RequestBody QuantityDTO[] quantities, Principal principal){
        return service.divide(quantities[0], quantities[1], getEmail(principal));
    }

    @GetMapping("/history/{operation}")
    public List<QuantityMeasurementEntity> getHistory(@PathVariable String operation, Principal principal){
        return service.getHistory(operation, getEmail(principal));
    }

    @GetMapping("/count/{operation}")
    public long getCount(@PathVariable String operation, Principal principal){
        return service.getCount(operation, getEmail(principal));
    }
}