package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_SameValue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(1.0);

        assertEquals(first, second);
    }

    @Test
    void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(2.0);

        assertNotEquals(first, second);
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertNotEquals(null, value);
    }

    @Test
    void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertNotEquals("NonNumeric", value);
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertEquals(value, value);
    }
}
