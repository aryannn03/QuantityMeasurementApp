package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest{

    private static final double EPSILON=0.0001;

    @Test
    void testEquality_LitreToLitre_SameValue(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(1.0,VolumeUnit.LITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(2.0,VolumeUnit.LITRE);
        assertFalse(v1.equals(v2));
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_MillilitreToLitre_EquivalentValue(){
        Quantity<VolumeUnit>v1=new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(1.0,VolumeUnit.LITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_LitreToGallon_EquivalentValue(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(0.264172,VolumeUnit.GALLON);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_GallonToLitre_EquivalentValue(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.GALLON);
        Quantity<VolumeUnit>v2=new Quantity<>(3.78541,VolumeUnit.LITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_VolumeVsLength_Incompatible(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<LengthUnit>l1=new Quantity<>(1.0,LengthUnit.FEET);
        assertFalse(v1.equals(l1));
    }

    @Test
    void testEquality_VolumeVsWeight_Incompatible(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<WeightUnit>w1=new Quantity<>(1.0,WeightUnit.KILOGRAM);
        assertFalse(v1.equals(w1));
    }

    @Test
    void testEquality_NullComparison(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        assertFalse(v1.equals(null));
    }

    @Test
    void testEquality_SameReference(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        assertTrue(v1.equals(v1));
    }

    @Test
    void testEquality_NullUnit(){
        assertThrows(IllegalArgumentException.class,()->{
            new Quantity<>(1.0,null);
        });
    }

    @Test
    void testEquality_ZeroValue(){
        Quantity<VolumeUnit>v1=new Quantity<>(0.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(0.0,VolumeUnit.MILLILITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_NegativeVolume(){
        Quantity<VolumeUnit>v1=new Quantity<>(-1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(-1000.0,VolumeUnit.MILLILITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_LargeVolumeValue(){
        Quantity<VolumeUnit>v1=new Quantity<>(1000000.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(1000.0,VolumeUnit.LITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_SmallVolumeValue(){
        Quantity<VolumeUnit>v1=new Quantity<>(0.001,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(1.0,VolumeUnit.MILLILITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    void testConversion_LitreToMillilitre(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0,result.getValue(),EPSILON);
    }

    @Test
    void testConversion_MillilitreToLitre(){
        Quantity<VolumeUnit>v1=new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>result=v1.convertTo(VolumeUnit.LITRE);
        assertEquals(1.0,result.getValue(),EPSILON);
    }

    @Test
    void testConversion_GallonToLitre(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.GALLON);
        Quantity<VolumeUnit>result=v1.convertTo(VolumeUnit.LITRE);
        assertEquals(3.78541,result.getValue(),EPSILON);
    }

    @Test
    void testConversion_LitreToGallon(){
        Quantity<VolumeUnit>v1=new Quantity<>(3.78541,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.convertTo(VolumeUnit.GALLON);
        assertEquals(1.0,result.getValue(),EPSILON);
    }

    @Test
    void testConversion_MillilitreToGallon(){
        Quantity<VolumeUnit>v1=new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>result=v1.convertTo(VolumeUnit.GALLON);
        assertEquals(0.264172,result.getValue(),EPSILON);
    }

    @Test
    void testConversion_SameUnit(){
        Quantity<VolumeUnit>v1=new Quantity<>(5.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.convertTo(VolumeUnit.LITRE);
        assertEquals(5.0,result.getValue(),EPSILON);
    }

    @Test
    void testConversion_ZeroValue(){
        Quantity<VolumeUnit>v1=new Quantity<>(0.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(0.0,result.getValue(),EPSILON);
    }

    @Test
    void testConversion_NegativeValue(){
        Quantity<VolumeUnit>v1=new Quantity<>(-1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(-1000.0,result.getValue(),EPSILON);
    }

    @Test
    void testConversion_RoundTrip(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.5,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.convertTo(VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE);
        assertEquals(1.5,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_SameUnit_LitrePlusLitre(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(2.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.add(v2);
        assertEquals(3.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_SameUnit_MillilitrePlusMillilitre(){
        Quantity<VolumeUnit>v1=new Quantity<>(500.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(500.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>result=v1.add(v2);
        assertEquals(1000.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>result=v1.add(v2);
        assertEquals(2.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_CrossUnit_MillilitrePlusLitre(){
        Quantity<VolumeUnit>v1=new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.add(v2);
        assertEquals(2000.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_CrossUnit_GallonPlusLitre(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.GALLON);
        Quantity<VolumeUnit>v2=new Quantity<>(3.78541,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.add(v2);
        assertEquals(2.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Litre(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>result=v1.add(v2,VolumeUnit.LITRE);
        assertEquals(2.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Millilitre(){
        Quantity<VolumeUnit>v1=new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>result=v1.add(v2,VolumeUnit.MILLILITRE);
        assertEquals(2000.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gallon(){
        Quantity<VolumeUnit>v1=new Quantity<>(3.78541,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(3.78541,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.add(v2,VolumeUnit.GALLON);
        assertEquals(2.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_WithZero(){
        Quantity<VolumeUnit>v1=new Quantity<>(5.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(0.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>result=v1.add(v2);
        assertEquals(5.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_NegativeValues(){
        Quantity<VolumeUnit>v1=new Quantity<>(5.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(-2000.0,VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit>result=v1.add(v2);
        assertEquals(3.0,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_LargeValues(){
        Quantity<VolumeUnit>v1=new Quantity<>(1e6,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(1e6,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.add(v2);
        assertEquals(2e6,result.getValue(),EPSILON);
    }

    @Test
    void testAddition_SmallValues(){
        Quantity<VolumeUnit>v1=new Quantity<>(0.001,VolumeUnit.LITRE);
        Quantity<VolumeUnit>v2=new Quantity<>(0.002,VolumeUnit.LITRE);
        Quantity<VolumeUnit>result=v1.add(v2);
        assertEquals(0.003,result.getValue(),EPSILON);
    }

    @Test
    void testVolumeUnitEnum_LitreConstant(){
        assertEquals(1.0,VolumeUnit.LITRE.getConversionFactor(),EPSILON);
    }

    @Test
    void testVolumeUnitEnum_MillilitreConstant(){
        assertEquals(0.001,VolumeUnit.MILLILITRE.getConversionFactor(),EPSILON);
    }

    @Test
    void testVolumeUnitEnum_GallonConstant(){
        assertEquals(3.78541,VolumeUnit.GALLON.getConversionFactor(),EPSILON);
    }

    @Test
    void testConvertToBaseUnit_LitreToLitre(){
        assertEquals(5.0,VolumeUnit.LITRE.convertToBaseUnit(5.0),EPSILON);
    }

    @Test
    void testConvertToBaseUnit_MillilitreToLitre(){
        assertEquals(1.0,VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0),EPSILON);
    }

    @Test
    void testConvertToBaseUnit_GallonToLitre(){
        assertEquals(3.78541,VolumeUnit.GALLON.convertToBaseUnit(1.0),EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_LitreToLitre(){
        assertEquals(2.0,VolumeUnit.LITRE.convertFromBaseUnit(2.0),EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_LitreToMillilitre(){
        assertEquals(1000.0,VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0),EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_LitreToGallon(){
        assertEquals(1.0,VolumeUnit.GALLON.convertFromBaseUnit(3.78541),EPSILON);
    }

}