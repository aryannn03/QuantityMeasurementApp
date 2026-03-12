package com.QuantityMeasurementApp.controller;

import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public boolean performComparison(QuantityDTO q1, QuantityDTO q2) {
        return service.compare(q1, q2);
    }

    public QuantityDTO performConversion(QuantityDTO q, String targetUnit) {
        return service.convert(q, targetUnit);
    }

    public QuantityDTO performAddition(QuantityDTO q1, QuantityDTO q2) {
        return service.add(q1, q2);
    }

    public QuantityDTO performSubtraction(QuantityDTO q1, QuantityDTO q2) {
        return service.subtract(q1, q2);
    }

    public double performDivision(QuantityDTO q1, QuantityDTO q2) {
        return service.divide(q1, q2);
    }
}