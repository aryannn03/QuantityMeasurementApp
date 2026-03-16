package com.QuantityMeasurementApp.service;

import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.model.QuantityModel;
import com.QuantityMeasurementApp.quantity.Quantity;
import com.QuantityMeasurementApp.repository.IQuantityMeasurementRepository;
import com.QuantityMeasurementApp.unit.IMeasurable;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    private Quantity<IMeasurable> toQuantity(QuantityDTO dto) {
        IMeasurable unit = IMeasurable.getUnitInstance(dto.getUnit());
        QuantityModel<IMeasurable> model = new QuantityModel<>(dto.getValue(), unit);
        return new Quantity<>(model.getValue(), model.getUnit());
    }

    private QuantityDTO toDTO(Quantity<IMeasurable> quantity) {
        return new QuantityDTO(
                quantity.getValue(),
                quantity.getUnit().getUnitName(),
                quantity.getUnit().getMeasurementType()
        );
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 = toQuantity(q1);
        Quantity<IMeasurable> quantity2 = toQuantity(q2);

        boolean result = quantity1.equals(quantity2);

        repository.save(new QuantityMeasurementEntity("COMPARE", String.valueOf(result)));

        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {

        Quantity<IMeasurable> q = toQuantity(quantity);
        IMeasurable target = IMeasurable.getUnitInstance(targetUnit);

        Quantity<IMeasurable> result = q.convertTo(target);

        QuantityDTO resultDTO = toDTO(result);

        repository.save(new QuantityMeasurementEntity("CONVERT", resultDTO.toString()));

        return resultDTO;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 = toQuantity(q1);
        Quantity<IMeasurable> quantity2 = toQuantity(q2);

        Quantity<IMeasurable> result = quantity1.add(quantity2);

        QuantityDTO resultDTO = toDTO(result);

        repository.save(new QuantityMeasurementEntity("ADD", resultDTO.toString()));

        return resultDTO;
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 = toQuantity(q1);
        Quantity<IMeasurable> quantity2 = toQuantity(q2);

        Quantity<IMeasurable> result = quantity1.subtract(quantity2);

        QuantityDTO resultDTO = toDTO(result);

        repository.save(new QuantityMeasurementEntity("SUBTRACT", resultDTO.toString()));

        return resultDTO;
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 = toQuantity(q1);
        Quantity<IMeasurable> quantity2 = toQuantity(q2);

        double result = quantity1.divide(quantity2);

        repository.save(new QuantityMeasurementEntity("DIVIDE", String.valueOf(result)));

        return result;
    }
}