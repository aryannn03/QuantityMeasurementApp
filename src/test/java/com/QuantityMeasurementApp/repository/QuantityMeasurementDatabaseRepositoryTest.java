package com.QuantityMeasurementApp.repository;

import com.QuantityMeasurementApp.entity.QuantityMeasurementEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class QuantityMeasurementDatabaseRepositoryTest {

    private QuantityMeasurementDatabaseRepository repository;

    @Before
    public void setUp() {
        repository = new QuantityMeasurementDatabaseRepository();
    }

    @Test
    public void shouldSaveMeasurementToDatabase() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", "10 FEET + 6 INCHES = 10.5 FEET");

        repository.save(entity);

        List<QuantityMeasurementEntity> measurements =
                repository.getAllMeasurements();

        assertNotNull(measurements);
        assertTrue(measurements.size() > 0);
    }

    @Test
    public void shouldRetrieveMeasurementsFromDatabase() {

        List<QuantityMeasurementEntity> measurements =
                repository.getAllMeasurements();

        assertNotNull(measurements);
    }

    @Test
    public void shouldInsertAndFetchEntity() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("SUBTRACT", "10 FEET - 5 FEET = 5 FEET");

        repository.save(entity);

        List<QuantityMeasurementEntity> measurements =
                repository.getAllMeasurements();

        boolean found = measurements.stream()
                .anyMatch(m -> "SUBTRACT".equals(m.getOperation()));

        assertTrue(found);
    }
}