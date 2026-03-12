package com.QuantityMeasurementApp.service;

import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl 
        implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        return q1.getValue() == q2.getValue();
    }

    @Override
    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {
        return new QuantityDTO(quantity.getValue(), targetUnit, quantity.getMeasurementType());
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        return new QuantityDTO(q1.getValue() + q2.getValue(), q1.getUnit(), q1.getMeasurementType());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        return new QuantityDTO(q1.getValue() - q2.getValue(), q1.getUnit(), q1.getMeasurementType());
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {
        return q1.getValue() / q2.getValue();
    }
}