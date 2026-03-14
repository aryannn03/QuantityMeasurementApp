package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;

import com.QuantityMeasurementApp.quantity.Quantity;
import com.QuantityMeasurementApp.unit.LengthUnit;
import com.QuantityMeasurementApp.unit.TemperatureUnit;
import com.QuantityMeasurementApp.unit.VolumeUnit;
import com.QuantityMeasurementApp.unit.WeightUnit;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest{

    private static final double EPSILON=0.000001;

    // SUBTRACTION TESTS

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(5.0,LengthUnit.FEET);

        Quantity<LengthUnit>result=a.subtract(b);

        assertEquals(5.0,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre(){
        Quantity<VolumeUnit>a=new Quantity<>(10.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>b=new Quantity<>(3.0,VolumeUnit.LITRE);

        Quantity<VolumeUnit>result=a.subtract(b);

        assertEquals(7.0,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(6.0,LengthUnit.INCHES);

        Quantity<LengthUnit>result=a.subtract(b);

        assertEquals(9.5,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet(){
        Quantity<LengthUnit>a=new Quantity<>(120.0,LengthUnit.INCHES);
        Quantity<LengthUnit>b=new Quantity<>(5.0,LengthUnit.FEET);

        Quantity<LengthUnit>result=a.subtract(b);

        assertEquals(60.0,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(6.0,LengthUnit.INCHES);

        Quantity<LengthUnit>result=a.subtract(b,LengthUnit.FEET);

        assertEquals(9.5,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(6.0,LengthUnit.INCHES);

        Quantity<LengthUnit>result=a.subtract(b,LengthUnit.INCHES);

        assertEquals(114.0,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Millilitre(){
        Quantity<VolumeUnit>a=new Quantity<>(5.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>b=new Quantity<>(2.0,VolumeUnit.LITRE);

        Quantity<VolumeUnit>result=a.subtract(b,VolumeUnit.MILLILITRE);

        assertEquals(3000.0,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_ResultingInNegative(){
        Quantity<LengthUnit>a=new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(10.0,LengthUnit.FEET);

        Quantity<LengthUnit>result=a.subtract(b);

        assertEquals(-5.0,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_ResultingInZero(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(120.0,LengthUnit.INCHES);

        Quantity<LengthUnit>result=a.subtract(b);

        assertEquals(0.0,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_WithZeroOperand(){
        Quantity<LengthUnit>a=new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(0.0,LengthUnit.INCHES);

        Quantity<LengthUnit>result=a.subtract(b);

        assertEquals(5.0,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_WithNegativeValues(){
        Quantity<LengthUnit>a=new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(-2.0,LengthUnit.FEET);

        Quantity<LengthUnit>result=a.subtract(b);

        assertEquals(7.0,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_NonCommutative(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(5.0,LengthUnit.FEET);

        double result1=a.subtract(b).getValue();
        double result2=b.subtract(a).getValue();

        assertNotEquals(result1,result2);
    }

    @Test
    void testSubtraction_WithLargeValues(){
        Quantity<WeightUnit>a=new Quantity<>(1e6,WeightUnit.KILOGRAM);
        Quantity<WeightUnit>b=new Quantity<>(5e5,WeightUnit.KILOGRAM);

        Quantity<WeightUnit>result=a.subtract(b);

        assertEquals(5e5,result.getValue(),EPSILON);
    }

    @Test
    void testSubtraction_NullOperand(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,()->a.subtract(null));
    }

    @Test
    void testSubtraction_NullTargetUnit(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(5.0,LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,()->a.subtract(b,null));
    }

    // DIVISION TESTS

    @Test
    void testDivision_SameUnit_FeetDividedByFeet(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(2.0,LengthUnit.FEET);

        assertEquals(5.0,a.divide(b),EPSILON);
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre(){
        Quantity<VolumeUnit>a=new Quantity<>(10.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>b=new Quantity<>(5.0,VolumeUnit.LITRE);

        assertEquals(2.0,a.divide(b),EPSILON);
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches(){
        Quantity<LengthUnit>a=new Quantity<>(24.0,LengthUnit.INCHES);
        Quantity<LengthUnit>b=new Quantity<>(2.0,LengthUnit.FEET);

        assertEquals(1.0,a.divide(b),EPSILON);
    }

    @Test
    void testDivision_CrossUnit_KilogramDividedByGram(){
        Quantity<WeightUnit>a=new Quantity<>(2.0,WeightUnit.KILOGRAM);
        Quantity<WeightUnit>b=new Quantity<>(2000.0,WeightUnit.GRAM);

        assertEquals(1.0,a.divide(b),EPSILON);
    }

    @Test
    void testDivision_RatioGreaterThanOne(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(2.0,LengthUnit.FEET);

        assertTrue(a.divide(b)>1);
    }

    @Test
    void testDivision_RatioLessThanOne(){
        Quantity<LengthUnit>a=new Quantity<>(5.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(10.0,LengthUnit.FEET);

        assertTrue(a.divide(b)<1);
    }

    @Test
    void testDivision_RatioEqualToOne(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(10.0,LengthUnit.FEET);

        assertEquals(1.0,a.divide(b),EPSILON);
    }

    @Test
    void testDivision_NonCommutative(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(5.0,LengthUnit.FEET);

        assertNotEquals(a.divide(b),b.divide(a));
    }

    @Test
    void testDivision_ByZero(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>b=new Quantity<>(0.0,LengthUnit.FEET);

        assertThrows(ArithmeticException.class,()->a.divide(b));
    }

    @Test
    void testDivision_WithLargeRatio(){
        Quantity<WeightUnit>a=new Quantity<>(1e6,WeightUnit.KILOGRAM);
        Quantity<WeightUnit>b=new Quantity<>(1.0,WeightUnit.KILOGRAM);

        assertEquals(1e6,a.divide(b),EPSILON);
    }

    @Test
    void testDivision_WithSmallRatio(){
        Quantity<WeightUnit>a=new Quantity<>(1.0,WeightUnit.KILOGRAM);
        Quantity<WeightUnit>b=new Quantity<>(1e6,WeightUnit.KILOGRAM);

        assertEquals(1e-6,a.divide(b),EPSILON);
    }

    @Test
    void testDivision_NullOperand(){
        Quantity<LengthUnit>a=new Quantity<>(10.0,LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,()->a.divide(null));
    }
     // ADDITION TESTS
    
    @Test
    void testAddition_SameUnit_FeetPlusFeet(){
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = a.add(b);

        assertEquals(15.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches(){
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = a.add(b);

        assertEquals(10.5, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches(){
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = a.add(b, LengthUnit.INCHES);

        assertEquals(126.0, result.getValue(), EPSILON);
    }
    
    // CONVERSION TESTS
    
    @Test
    void testConversion_FeetToInches(){
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_LitreToMillilitre(){
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = q.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }
    
    // EQUALITY TESTS
    
    @Test
    void testEquality_SameUnit(){
        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        assertTrue(a.equals(b));
    }

    @Test
    void testEquality_CrossUnit(){
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(a.equals(b));
    }
    
    // TEMPERATURE TESTS
    
    @Test
    void testTemperatureEquality(){
        Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
    }

    @Test
    void testTemperatureConversion(){
        Quantity<TemperatureUnit> c = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result = c.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureUnsupportedAddition(){
        Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class, () -> t1.add(t2));
    }

}