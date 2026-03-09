package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        double result = Length.convert(1.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES);
        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = Length.convert(24.0,
                Length.LengthUnit.INCHES,
                Length.LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = Length.convert(1.0,
                Length.LengthUnit.YARDS,
                Length.LengthUnit.INCHES);
        assertEquals(36.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        double result = Length.convert(72.0,
                Length.LengthUnit.INCHES,
                Length.LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = Length.convert(2.54,
                Length.LengthUnit.CENTIMETERS,
                Length.LengthUnit.INCHES);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testConversion_FeetToYards() {
        double result = Length.convert(6.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double original = 5.75;

        double converted = Length.convert(original,
                Length.LengthUnit.FEET,
                Length.LengthUnit.YARDS);

        double roundTrip = Length.convert(converted,
                Length.LengthUnit.YARDS,
                Length.LengthUnit.FEET);

        assertEquals(original, roundTrip, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        double result = Length.convert(0.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = Length.convert(-1.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES);
        assertEquals(-12.0, result, EPSILON);
    }

    @Test
    void testConversion_SameUnit() {
        double result = Length.convert(5.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.FEET);
        assertEquals(5.0, result, EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                Length.convert(1.0, null, Length.LengthUnit.FEET));

        assertThrows(IllegalArgumentException.class, () ->
                Length.convert(1.0, Length.LengthUnit.FEET, null));
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                Length.convert(Double.NaN,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES));

        assertThrows(IllegalArgumentException.class, () ->
                Length.convert(Double.POSITIVE_INFINITY,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES));
    }
}