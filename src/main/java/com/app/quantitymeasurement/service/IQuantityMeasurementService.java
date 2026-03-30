package com.app.quantitymeasurement.service;

import java.util.*;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO q1,QuantityDTO q2);

    QuantityDTO convert(QuantityDTO source,String targetUnit);

    QuantityDTO add(QuantityDTO q1,QuantityDTO q2);

    QuantityDTO subtract(QuantityDTO q1,QuantityDTO q2);

    double divide(QuantityDTO q1,QuantityDTO q2);
    
    List<QuantityMeasurementEntity> getHistory(String operation);

    long getCount(String operation);
}