package com.QuantityMeasurementApp.entity;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementEntityTest {

    @Test
    public void shouldCreateEntitySuccessfully() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD",
                        "10 FEET + 6 INCHES = 10.5 FEET");

        assertEquals("ADD", entity.getOperation());
        assertEquals("10 FEET + 6 INCHES = 10.5 FEET",
                entity.getResult());
    }

    @Test
    public void shouldSetAndGetOperation() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("SUBTRACT", "dummy");

        assertEquals("SUBTRACT", entity.getOperation());
    }

    @Test
    public void shouldSetAndGetResult() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", "5 FEET");

        assertEquals("5 FEET", entity.getResult());
    }

}