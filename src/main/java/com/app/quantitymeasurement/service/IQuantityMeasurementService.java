package com.app.quantitymeasurement.service;

import java.util.*;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO q1, QuantityDTO q2, String userEmail);     

    QuantityDTO convert(QuantityDTO source, String targetUnit, String userEmail); 

    QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String userEmail);      

    QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String userEmail); 

    double divide(QuantityDTO q1, QuantityDTO q2, String userEmail);        

    List<QuantityMeasurementEntity> getHistory(String operation, String userEmail); 

    long getCount(String operation, String userEmail); 
}