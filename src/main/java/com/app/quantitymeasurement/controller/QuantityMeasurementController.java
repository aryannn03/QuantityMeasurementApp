package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityDTO;
import java.util.List;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quantities")
@CrossOrigin(origins = "*")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public boolean compare(@Valid @RequestBody QuantityDTO[] quantities){
        return service.compare(quantities[0],quantities[1]);
    }

    @PostMapping("/convert")
    public QuantityDTO convert(@Valid @RequestBody QuantityDTO quantity,
                               @RequestParam String targetUnit){
        return service.convert(quantity,targetUnit);
    }

    @PostMapping("/add")
    public QuantityDTO add(@Valid @RequestBody QuantityDTO[] quantities){
        return service.add(quantities[0],quantities[1]);
    }

    @PostMapping("/subtract")
    public QuantityDTO subtract(@Valid @RequestBody QuantityDTO[] quantities){
        return service.subtract(quantities[0],quantities[1]);
    }

    @PostMapping("/divide")
    public double divide(@Valid @RequestBody QuantityDTO[] quantities){
        return service.divide(quantities[0],quantities[1]);
    }
    
    @GetMapping("/history/{operation}")
    public List<QuantityMeasurementEntity> getHistory(@PathVariable String operation){
        return service.getHistory(operation);
    }

    @GetMapping("/count/{operation}")
    public long getCount(@PathVariable String operation){
        return service.getCount(operation);
    }
}