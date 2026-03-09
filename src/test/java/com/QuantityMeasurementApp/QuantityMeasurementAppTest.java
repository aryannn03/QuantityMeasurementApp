package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        Length l1 = new Length(6.0, LengthUnit.INCHES);
        Length l2 = new Length(6.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(12.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(2.0, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {
        Length l1 = new Length(2.54, LengthUnit.CENTIMETERS);
        Length l2 = new Length(1.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertTrue(result.equals(
                new Length(5.08, LengthUnit.CENTIMETERS)));
    }

    @Test
    void testAddition_Commutativity() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        Length result1 = a.add(b);
        Length result2 = b.add(a);

        assertTrue(result1.equals(result2));
    }

    @Test
    void testAddition_WithZero() {
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(0.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_NegativeValues() {
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(-2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_NullSecondOperand() {
        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            l1.add(null);
        });
    }

    @Test
    void testAddition_LargeValues() {
        Length l1 = new Length(1e6, LengthUnit.FEET);
        Length l2 = new Length(1e6, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(2e6, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_SmallValues() {
        Length l1 = new Length(0.001, LengthUnit.FEET);
        Length l2 = new Length(0.002, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertTrue(result.equals(
                new Length(0.003, LengthUnit.FEET)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2, LengthUnit.FEET);

        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2, LengthUnit.INCHES);

        assertEquals(new Length(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2, LengthUnit.YARDS);

        assertTrue(result.equals(new Length(2.0/3.0, LengthUnit.YARDS)));
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.INCHES);

        Length result = l1.add(l2, LengthUnit.CENTIMETERS);

        assertEquals(new Length(5.079997, LengthUnit.CENTIMETERS), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        Length result1 = a.add(b, LengthUnit.YARDS);
        Length result2 = b.add(a, LengthUnit.YARDS);

        assertTrue(result1.equals(result2));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () -> {
            l1.add(l2, null);
        });
    }
    @Test
    void kilogramEqual1000grams() {

        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void poundEqual453grams() {

        Weight w1 = new Weight(1.0, WeightUnit.POUND);
        Weight w2 = new Weight(453.592, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void weightAddition_KgPlusGram() {

        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        Weight result = w1.add(w2);

        assertEquals(new Weight(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void convertKgToGram() {

        Weight w = new Weight(1.0, WeightUnit.KILOGRAM);

        Weight result = w.convertTo(WeightUnit.GRAM);

        assertEquals(new Weight(1000.0, WeightUnit.GRAM), result);
    }
}