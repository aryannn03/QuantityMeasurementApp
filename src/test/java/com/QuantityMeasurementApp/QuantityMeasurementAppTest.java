package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_YardToYard_SameValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(1.0, Length.LengthUnit.YARDS);
        assertEquals(l1, l2);
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);
        assertEquals(l1, l2);
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(36.0, Length.LengthUnit.INCHES);
        assertEquals(l1, l2);
    }

    @Test
    void testEquality_CentimetersToInches_EquivalentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length l2 = new Length(0.393701, Length.LengthUnit.INCHES);
        assertEquals(l1, l2);
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);
        assertNotEquals(l1, l2);
    }

    @Test
    void testEquality_SameReference() {
        Length l1 = new Length(2.0, Length.LengthUnit.YARDS);
        assertEquals(l1, l1);
    }

    @Test
    void testEquality_NullComparison() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        assertNotEquals(null, l1);
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }
}