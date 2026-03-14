package com.QuantityMeasurementApp.service;

import com.QuantityMeasurementApp.unit.IMeasurable;
import com.QuantityMeasurementApp.quantity.Quantity;
import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.repository.IQuantityMeasurementRepository;
import com.QuantityMeasurementApp.entity.QuantityMeasurementEntity;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        IMeasurable unit1 = IMeasurable.getUnitInstance(q1.getUnit());
        IMeasurable unit2 = IMeasurable.getUnitInstance(q2.getUnit());

        Quantity<IMeasurable> quantity1 = new Quantity<>(q1.getValue(), unit1);
        Quantity<IMeasurable> quantity2 = new Quantity<>(q2.getValue(), unit2);

        boolean result = quantity1.equals(quantity2);

        repository.save(
                new QuantityMeasurementEntity("COMPARE", String.valueOf(result))
        );

        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {

        IMeasurable unit = IMeasurable.getUnitInstance(quantity.getUnit());
        IMeasurable target = IMeasurable.getUnitInstance(targetUnit);

        Quantity<IMeasurable> q = new Quantity<>(quantity.getValue(), unit);
        Quantity<IMeasurable> result = q.convertTo(target);

        QuantityDTO resultDTO = new QuantityDTO(
                result.getValue(),
                result.getUnit().getUnitName(),
                result.getUnit().getMeasurementType()
        );

        repository.save(
                new QuantityMeasurementEntity("CONVERT", resultDTO.toString())
        );

        return resultDTO;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        IMeasurable unit1 = IMeasurable.getUnitInstance(q1.getUnit());
        IMeasurable unit2 = IMeasurable.getUnitInstance(q2.getUnit());

        Quantity<IMeasurable> quantity1 = new Quantity<>(q1.getValue(), unit1);
        Quantity<IMeasurable> quantity2 = new Quantity<>(q2.getValue(), unit2);

        Quantity<IMeasurable> result = quantity1.add(quantity2);

        QuantityDTO resultDTO = new QuantityDTO(
                result.getValue(),
                result.getUnit().getUnitName(),
                result.getUnit().getMeasurementType()
        );

        repository.save(
                new QuantityMeasurementEntity("ADD", resultDTO.toString())
        );

        return resultDTO;
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        IMeasurable unit1 = IMeasurable.getUnitInstance(q1.getUnit());
        IMeasurable unit2 = IMeasurable.getUnitInstance(q2.getUnit());

        Quantity<IMeasurable> quantity1 = new Quantity<>(q1.getValue(), unit1);
        Quantity<IMeasurable> quantity2 = new Quantity<>(q2.getValue(), unit2);

        Quantity<IMeasurable> result = quantity1.subtract(quantity2);
        
        QuantityDTO resultDTO = new QuantityDTO(
                result.getValue(),
                result.getUnit().getUnitName(),
                result.getUnit().getMeasurementType()
        );

        repository.save(
                new QuantityMeasurementEntity("SUBTRACT", resultDTO.toString())
        );

        return resultDTO;
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        IMeasurable unit1 = IMeasurable.getUnitInstance(q1.getUnit());
        IMeasurable unit2 = IMeasurable.getUnitInstance(q2.getUnit());

        Quantity<IMeasurable> quantity1 = new Quantity<>(q1.getValue(), unit1);
        Quantity<IMeasurable> quantity2 = new Quantity<>(q2.getValue(), unit2);

        double result = quantity1.divide(quantity2);

        repository.save(
                new QuantityMeasurementEntity("DIVIDE", String.valueOf(result))
        );

        return result;
    }
}