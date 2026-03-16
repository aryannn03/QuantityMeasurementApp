package com.QuantityMeasurementApp.controller;

import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.service.IQuantityMeasurementService;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class QuantityMeasurementControllerTest {

    private IQuantityMeasurementService service;
    private QuantityMeasurementController controller;

    @Before
    public void setUp() {
        service = mock(IQuantityMeasurementService.class);
        controller = new QuantityMeasurementController(service);
    }

    @Test
    public void shouldPerformAddition() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(6, "INCHES", "Length");

        QuantityDTO expected = new QuantityDTO(10.5, "FEET", "Length");

        when(service.add(q1, q2)).thenReturn(expected);

        QuantityDTO result = controller.performAddition(q1, q2);

        assertEquals(expected.getValue(), result.getValue(), 0.001);
        assertEquals(expected.getUnit(), result.getUnit());
        assertEquals(expected.getMeasurementType(), result.getMeasurementType());

        verify(service).add(q1, q2);
    }

    @Test
    public void shouldPerformSubtraction() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(5, "FEET", "Length");

        QuantityDTO expected = new QuantityDTO(5, "FEET", "Length");

        when(service.subtract(q1, q2)).thenReturn(expected);

        QuantityDTO result = controller.performSubtraction(q1, q2);

        assertEquals(expected.getValue(), result.getValue(), 0.001);
        verify(service).subtract(q1, q2);
    }

    @Test
    public void shouldCompareQuantities() {

        QuantityDTO q1 = new QuantityDTO(12, "INCHES", "Length");
        QuantityDTO q2 = new QuantityDTO(1, "FEET", "Length");

        when(service.compare(q1, q2)).thenReturn(true);

        boolean result = controller.performComparison(q1, q2);

        assertTrue(result);
        verify(service).compare(q1, q2);
    }
}