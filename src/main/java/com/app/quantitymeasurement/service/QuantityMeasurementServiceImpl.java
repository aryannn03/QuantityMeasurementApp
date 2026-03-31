package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import java.util.List;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.quantity.Quantity;
import com.app.quantitymeasurement.unit.*;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    // 🔹 Convert DTO → Quantity Model
    private Quantity convertDTOToQuantity(QuantityDTO dto){

    	String unitName = dto.getUnit().trim().toUpperCase();
    	String type = dto.getMeasurementType().trim().toUpperCase();
        double value=dto.getValue();

        switch(type){

            case "LENGTH":
                return new Quantity(value,LengthUnit.valueOf(unitName));

            case "WEIGHT":
                return new Quantity(value,WeightUnit.valueOf(unitName));

            case "VOLUME":
                return new Quantity(value,VolumeUnit.valueOf(unitName));

            case "TEMPERATURE":
                return new Quantity(value,TemperatureUnit.valueOf(unitName));

            default:
                throw new QuantityMeasurementException("Invalid measurement type");
        }
    }

    // 🔹 Convert Quantity → DTO
    private QuantityDTO convertQuantityToDTO(Quantity quantity){

        String measurementType = quantity.getUnit().getClass()
                .getSimpleName()
                .replace("Unit","")
                .toUpperCase();

        return new QuantityDTO(
                quantity.getValue(),
                quantity.getUnit().toString(),
                measurementType
        );
    }

    // 🔥 COMPARE
    @Override
    public boolean compare(QuantityDTO q1,QuantityDTO q2){

        try{

            Quantity quantity1=convertDTOToQuantity(q1);
            Quantity quantity2=convertDTOToQuantity(q2);

            if(!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())){
                throw new QuantityMeasurementException("Different measurement types");
            }

            boolean result=quantity1.equals(quantity2);

            // ✅ SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    quantity1.toString(),
                    quantity2.toString(),
                    "COMPARE",
                    String.valueOf(result)
            ));

            return result;

        }catch(Exception e){
            throw new QuantityMeasurementException(
                    "Operation failed: " + e.getMessage(), e
                );
            }
    }

    // 🔥 CONVERT
    @Override
    public QuantityDTO convert(QuantityDTO source,String targetUnit){

        try{

            Quantity quantity=convertDTOToQuantity(source);
            targetUnit = targetUnit.trim().toUpperCase();

            IMeasurable unit=(IMeasurable)Enum.valueOf(
                    (Class<? extends Enum>)quantity.getUnit().getClass(),
                    targetUnit
            );

            Quantity result=quantity.convertTo(unit);

            // ✅ SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    quantity.toString(),
                    targetUnit,
                    "CONVERT",
                    result.toString()
            ));

            return convertQuantityToDTO(result);

        }catch(Exception e){
            throw new QuantityMeasurementException(
                    "Operation failed: " + e.getMessage(), e
                );
            }
    }

    // 🔥 ADD
    @Override
    public QuantityDTO add(QuantityDTO q1,QuantityDTO q2){

        try{

            Quantity quantity1=convertDTOToQuantity(q1);
            Quantity quantity2=convertDTOToQuantity(q2);
            validateArithmetic(quantity1);
            validateArithmetic(quantity2);
            validateSameType(q1, q2);

            Quantity result=quantity1.add(quantity2);

            // ✅ SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    quantity1.toString(),
                    quantity2.toString(),
                    "ADD",
                    result.toString()
            ));

            return convertQuantityToDTO(result);

        }catch(Exception e){
            throw new QuantityMeasurementException(
                    "Operation failed: " + e.getMessage(), e
                );
            }
    }

    // 🔥 SUBTRACT
    @Override
    public QuantityDTO subtract(QuantityDTO q1,QuantityDTO q2){

        try{

            Quantity quantity1=convertDTOToQuantity(q1);
            Quantity quantity2=convertDTOToQuantity(q2);
            validateArithmetic(quantity1);
            validateArithmetic(quantity2);
            validateSameType(q1, q2);

            Quantity result=quantity1.subtract(quantity2);

            // ✅ SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    quantity1.toString(),
                    quantity2.toString(),
                    "SUBTRACT",
                    result.toString()
            ));

            return convertQuantityToDTO(result);

        }catch(Exception e){
            throw new QuantityMeasurementException(
                    "Operation failed: " + e.getMessage(), e
                );
            }
    }

    // 🔥 DIVIDE
    @Override
    public double divide(QuantityDTO q1,QuantityDTO q2){

        try{

            Quantity quantity1=convertDTOToQuantity(q1);
            Quantity quantity2=convertDTOToQuantity(q2);
            validateArithmetic(quantity1);
            validateArithmetic(quantity2);
            validateSameType(q1, q2);

            double result=quantity1.divide(quantity2);

            // ✅ SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    quantity1.toString(),
                    quantity2.toString(),
                    "DIVIDE",
                    String.valueOf(result)
            ));

            return result;

        }catch(Exception e){
            throw new QuantityMeasurementException(
                    "Operation failed: " + e.getMessage(), e
                );
            }
    }
    @Override
    public List<QuantityMeasurementEntity> getHistory(String operation){
        return repository.findByOperation(operation);
    }

    @Override
    public long getCount(String operation){
        return repository.countByOperation(operation);
    }
    private void validateArithmetic(Quantity quantity) {
        if (!quantity.getUnit().supportsArithmetic()) {
            throw new QuantityMeasurementException(
                "Operation not supported for " + quantity.getUnit()
            );
        }
    }
    private void validateSameType(QuantityDTO q1, QuantityDTO q2) {
        String type1 = q1.getMeasurementType().trim().toUpperCase();
        String type2 = q2.getMeasurementType().trim().toUpperCase();

        if (!type1.equals(type2)) {
            throw new QuantityMeasurementException("Different measurement types");
        }
    }
}