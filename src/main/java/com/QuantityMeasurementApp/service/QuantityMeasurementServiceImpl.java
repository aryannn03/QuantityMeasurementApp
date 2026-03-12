package com.QuantityMeasurementApp.service;

import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        boolean result = q1.getValue() == q2.getValue();

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                "COMPARE",
                String.valueOf(result)
        );

        repository.save(entity);

        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {

        QuantityDTO result = new QuantityDTO(
                quantity.getValue(),
                targetUnit,
                quantity.getMeasurementType()
        );

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("CONVERT", result.toString());

        repository.save(entity);

        return result;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        double value = q1.getValue() + q2.getValue();

        QuantityDTO result =
                new QuantityDTO(value, q1.getUnit(), q1.getMeasurementType());

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", result.toString());

        repository.save(entity);

        return result;
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        double value = q1.getValue() - q2.getValue();

        QuantityDTO result =
                new QuantityDTO(value, q1.getUnit(), q1.getMeasurementType());

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("SUBTRACT", result.toString());

        repository.save(entity);

        return result;
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        double result = q1.getValue() / q2.getValue();

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("DIVIDE", String.valueOf(result));

        repository.save(entity);

        return result;
    }
}