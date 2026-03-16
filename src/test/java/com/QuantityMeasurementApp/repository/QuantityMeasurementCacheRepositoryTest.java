package com.QuantityMeasurementApp.repository;

import com.QuantityMeasurementApp.entity.QuantityMeasurementEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class QuantityMeasurementCacheRepositoryTest {

    private QuantityMeasurementCacheRepository repository;

    @Before
    public void setUp() {
        repository = QuantityMeasurementCacheRepository.getInstance();
    }

    @Test
    public void shouldSaveMeasurementInCache() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", "10 FEET + 6 INCHES = 10.5 FEET");

        repository.save(entity);

        List<QuantityMeasurementEntity> measurements = repository.getAllMeasurements();

        assertTrue(measurements.contains(entity));
    }

    @Test
    public void shouldRetrieveAllMeasurements() {

        QuantityMeasurementEntity entity1 =
                new QuantityMeasurementEntity("ADD", "10 FEET + 6 INCHES");

        QuantityMeasurementEntity entity2 =
                new QuantityMeasurementEntity("SUBTRACT", "10 FEET - 5 FEET");

        repository.save(entity1);
        repository.save(entity2);

        List<QuantityMeasurementEntity> measurements = repository.getAllMeasurements();

        assertNotNull(measurements);
        assertTrue(measurements.size() >= 2);
    }
}