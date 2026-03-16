package com.QuantityMeasurementApp.integrationTests;

import com.QuantityMeasurementApp.controller.QuantityMeasurementController;
import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.repository.QuantityMeasurementDatabaseRepository;
import com.QuantityMeasurementApp.service.QuantityMeasurementServiceImpl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurementIntegrationTest {

    private QuantityMeasurementController controller;

    @Before
    public void setUp() {

        QuantityMeasurementDatabaseRepository repository =
                new QuantityMeasurementDatabaseRepository();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        controller = new QuantityMeasurementController(service);
    }

    @Test
    public void shouldPerformAdditionAndStoreResultInDatabase() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(6, "INCHES", "Length");

        QuantityDTO result = controller.performAddition(q1, q2);

        assertEquals(10.5, result.getValue(), 0.001);
        assertEquals("FEET", result.getUnit());
    }

    @Test
    public void shouldPerformSubtractionAndStoreResultInDatabase() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(5, "FEET", "Length");

        QuantityDTO result = controller.performSubtraction(q1, q2);

        assertEquals(5.0, result.getValue(), 0.001);
        assertEquals("FEET", result.getUnit());
    }

    @Test
    public void shouldCompareEqualQuantitiesAcrossUnits() {

        QuantityDTO q1 = new QuantityDTO(12, "INCHES", "Length");
        QuantityDTO q2 = new QuantityDTO(1, "FEET", "Length");

        boolean result = controller.performComparison(q1, q2);

        assertTrue(result);
    }

    @Test
    public void shouldDivideQuantities() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(5, "FEET", "Length");

        double result = controller.performDivision(q1, q2);

        assertEquals(2.0, result, 0.001);
    }
}