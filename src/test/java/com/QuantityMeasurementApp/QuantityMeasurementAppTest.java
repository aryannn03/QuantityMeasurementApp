package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // LENGTH TESTS 

    @Test
    public void lengthFeetEqualsInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    @Test
    public void lengthYardsEqualsFeet() {

        Quantity<LengthUnit> yards =
                new Quantity<>(1.0, LengthUnit.YARDS);

        Quantity<LengthUnit> feet =
                new Quantity<>(3.0, LengthUnit.FEET);

        assertTrue(yards.equals(feet));
    }

    @Test
    public void convertLengthFeetToInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), inches);
    }

    @Test
    public void addLengthFeetAndInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                feet.add(inches);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
    }

    // WEIGHT TESTS 

    @Test
    public void weightKilogramEqualsGrams() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(grams));
    }

    @Test
    public void weightPoundEqualsGrams() {

        Quantity<WeightUnit> pound =
                new Quantity<>(1.0, WeightUnit.POUND);

        Quantity<WeightUnit> grams =
                new Quantity<>(453.592, WeightUnit.GRAM);

        assertTrue(pound.equals(grams));
    }

    @Test
    public void convertWeightKilogramsToGrams() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), grams);
    }

    @Test
    public void addWeightKilogramsAndGrams() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                kg.add(grams, WeightUnit.KILOGRAM);

        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), result);
    }

    // GENERIC TYPE SAFETY 

    @Test
    public void preventCrossTypeComparisonLengthVsWeight() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }
}