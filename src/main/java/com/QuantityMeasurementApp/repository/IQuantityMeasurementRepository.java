package com.QuantityMeasurementApp.repository;

import java.util.List;
import com.QuantityMeasurementApp.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAllMeasurements();
}