package com.app.qma.repository;

import com.app.qma.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperationAndUserEmail(String operation, String userEmail);

    List<QuantityMeasurementEntity> findByUserEmail(String userEmail);

    long countByOperationAndUserEmail(String operation, String userEmail);
}