package com.QuantityMeasurementApp.service;

import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.repository.IQuantityMeasurementRepository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class QuantityMeasurementServiceImplTest {

    private IQuantityMeasurementRepository repository;
    private QuantityMeasurementServiceImpl service;

    @Before
    public void setUp() {
        repository = mock(IQuantityMeasurementRepository.class);
        service = new QuantityMeasurementServiceImpl(repository);
    }

    @Test
    public void shouldAddLengthQuantities() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(6, "INCHES", "Length");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(10.5, result.getValue(), 0.001);
        assertEquals("FEET", result.getUnit());
    }

    @Test
    public void shouldSubtractLengthQuantities() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(5, "FEET", "Length");

        QuantityDTO result = service.subtract(q1, q2);

        assertEquals(5.0, result.getValue(), 0.001);
        assertEquals("FEET", result.getUnit());
    }

    @Test
    public void shouldCompareEqualQuantities() {

        QuantityDTO q1 = new QuantityDTO(12, "INCHES", "Length");
        QuantityDTO q2 = new QuantityDTO(1, "FEET", "Length");

        boolean result = service.compare(q1, q2);

        assertTrue(result);
    }

    @Test
    public void shouldDivideQuantities() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(5, "FEET", "Length");

        double result = service.divide(q1, q2);

        assertEquals(2.0, result, 0.001);
    }

    @Test
    public void shouldSaveMeasurementToRepository() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(5, "FEET", "Length");

        service.subtract(q1, q2);

        verify(repository, atLeastOnce()).save(any(QuantityMeasurementEntity.class));
    }
}