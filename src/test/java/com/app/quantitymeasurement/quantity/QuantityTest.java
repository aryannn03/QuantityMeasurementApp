package com.app.quantitymeasurement.quantity;

import com.app.quantitymeasurement.unit.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Quantity Domain Tests")
public class QuantityTest {

    private static final double DELTA = 0.0001;

    // ─────────────────────────────────────────────────────────
    //  CONSTRUCTOR VALIDATION
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Should throw when unit is null")
    void constructor_nullUnit_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(10.0, null));
    }

    @Test
    @DisplayName("Should throw when value is NaN")
    void constructor_nanValue_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.INCH));
    }

    @Test
    @DisplayName("Should throw when value is Infinity")
    void constructor_infiniteValue_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.INCH));
    }

    @Test
    @DisplayName("Should create quantity with valid inputs")
    void constructor_validInputs_createsQuantity() {
        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);
        assertEquals(10.0, q.getValue());
        assertEquals(LengthUnit.FEET, q.getUnit());
    }

    // ─────────────────────────────────────────────────────────
    //  LENGTH CONVERSIONS
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("1 FEET should equal 12 INCH")
    void length_feetToInch() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = feet.convertTo(LengthUnit.INCH);
        assertEquals(12.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("1 YARD should equal 36 INCH")
    void length_yardToInch() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
        Quantity<LengthUnit> result = yard.convertTo(LengthUnit.INCH);
        assertEquals(36.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("1 INCH should convert to CENTIMETER correctly")
    void length_inchToCentimeter() {
        Quantity<LengthUnit> inch = new Quantity<>(1.0, LengthUnit.INCH);
        Quantity<LengthUnit> result = inch.convertTo(LengthUnit.CENTIMETER);
        assertEquals(2.54, result.getValue(), 0.01);
    }

    @Test
    @DisplayName("Same unit conversion should return same value")
    void length_sameUnit_returnsIdentical() {
        Quantity<LengthUnit> q = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.FEET);
        assertEquals(5.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Should throw when target unit is null")
    void convertTo_nullTarget_throwsException() {
        Quantity<LengthUnit> q = new Quantity<>(5.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.convertTo(null));
    }

    // ─────────────────────────────────────────────────────────
    //  WEIGHT CONVERSIONS
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("1 KILOGRAM should equal 1000 GRAM")
    void weight_kilogramToGram() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = kg.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("1 GRAM should equal 1000 MILLIGRAM")
    void weight_gramToMilligram() {
        Quantity<WeightUnit> g = new Quantity<>(1.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = g.convertTo(WeightUnit.MILLIGRAM);
        assertEquals(1000.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("1 POUND should convert to GRAM correctly")
    void weight_poundToGram() {
        Quantity<WeightUnit> pound = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> result = pound.convertTo(WeightUnit.GRAM);
        assertEquals(453.592, result.getValue(), 0.001);
    }

    @Test
    @DisplayName("1 TONNE should equal 1000 KILOGRAM")
    void weight_tonneToKilogram() {
        Quantity<WeightUnit> tonne = new Quantity<>(1.0, WeightUnit.TONNE);
        Quantity<WeightUnit> result = tonne.convertTo(WeightUnit.KILOGRAM);
        assertEquals(1000.0, result.getValue(), DELTA);
    }

    // ─────────────────────────────────────────────────────────
    //  VOLUME CONVERSIONS
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("1 LITRE should equal 1000 MILLILITRE")
    void volume_litreToMillilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("1 GALLON should convert to LITRE correctly")
    void volume_gallonToLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.LITRE);
        assertEquals(3.78541, result.getValue(), 0.0001);
    }

    // ─────────────────────────────────────────────────────────
    //  TEMPERATURE CONVERSIONS
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("0 CELSIUS should equal 32 FAHRENHEIT")
    void temperature_celsiusToFahrenheit_freezingPoint() {
        Quantity<TemperatureUnit> celsius = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = celsius.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(32.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("100 CELSIUS should equal 212 FAHRENHEIT")
    void temperature_celsiusToFahrenheit_boilingPoint() {
        Quantity<TemperatureUnit> celsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = celsius.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(212.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("32 FAHRENHEIT should equal 0 CELSIUS")
    void temperature_fahrenheitToCelsius_freezingPoint() {
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> result = fahrenheit.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(0.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("212 FAHRENHEIT should equal 100 CELSIUS")
    void temperature_fahrenheitToCelsius_boilingPoint() {
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> result = fahrenheit.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(100.0, result.getValue(), DELTA);
    }

    // ─────────────────────────────────────────────────────────
    //  EQUALS / COMPARE
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("1 FEET and 12 INCH should be equal")
    void equals_feetAndInch_areEqual() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inch = new Quantity<>(12.0, LengthUnit.INCH);
        assertEquals(feet, inch);
    }

    @Test
    @DisplayName("1 FEET and 11 INCH should not be equal")
    void equals_feetAndInch_notEqual() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inch = new Quantity<>(11.0, LengthUnit.INCH);
        assertNotEquals(feet, inch);
    }

    @Test
    @DisplayName("Same object reference should be equal")
    void equals_sameReference_isEqual() {
        Quantity<LengthUnit> q = new Quantity<>(5.0, LengthUnit.FEET);
        assertEquals(q, q);
    }

    @Test
    @DisplayName("Null comparison should return false")
    void equals_null_returnsFalse() {
        Quantity<LengthUnit> q = new Quantity<>(5.0, LengthUnit.FEET);
        assertNotEquals(null, q);
    }

    @Test
    @DisplayName("Different measurement types should not be equal")
    void equals_differentTypes_returnsFalse() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.GRAM);
        assertNotEquals(length, weight);
    }

    @Test
    @DisplayName("1 KILOGRAM and 1000 GRAM should be equal")
    void equals_kilogramAndGram_areEqual() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g  = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(kg, g);
    }

    // ─────────────────────────────────────────────────────────
    //  ADD
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Add 5 GRAM + 1 KILOGRAM = 1005 GRAM")
    void add_gramAndKilogram_correctResult() {
        Quantity<WeightUnit> g  = new Quantity<>(5.0, WeightUnit.GRAM);
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = g.add(kg);
        assertEquals(1005.0, result.getValue(), DELTA);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    @DisplayName("Add 1 LITRE + 1000 MILLILITRE = 2 LITRE")
    void add_litreAndMillilitre_correctResult() {
        Quantity<VolumeUnit> l  = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = l.add(ml);
        assertEquals(2.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Add should throw when other is null")
    void add_nullOther_throwsException() {
        Quantity<WeightUnit> g = new Quantity<>(5.0, WeightUnit.GRAM);
        assertThrows(IllegalArgumentException.class, () -> g.add(null));
    }

    @Test
    @DisplayName("Add should throw for Temperature (unsupported)")
    void add_temperature_throwsUnsupportedOperation() {
        Quantity<TemperatureUnit> c1 = new Quantity<>(10.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> c2 = new Quantity<>(20.0, TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> c1.add(c2));
    }

    // ─────────────────────────────────────────────────────────
    //  SUBTRACT
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Subtract 500 GRAM from 1 KILOGRAM = 500 GRAM")
    void subtract_kilogramMinusGram_correctResult() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g  = new Quantity<>(500.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = kg.subtract(g);
        assertEquals(500.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Subtract should throw for Temperature (unsupported)")
    void subtract_temperature_throwsUnsupportedOperation() {
        Quantity<TemperatureUnit> c1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> c2 = new Quantity<>(20.0, TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> c1.subtract(c2));
    }

    @Test
    @DisplayName("Subtract should throw when other is null")
    void subtract_nullOther_throwsException() {
        Quantity<WeightUnit> g = new Quantity<>(5.0, WeightUnit.GRAM);
        assertThrows(IllegalArgumentException.class, () -> g.subtract(null));
    }

    // ─────────────────────────────────────────────────────────
    //  DIVIDE
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Divide 10 FEET by 2 FEET = 5.0")
    void divide_feetByFeet_correctResult() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(5.0, q1.divide(q2), DELTA);
    }

    @Test
    @DisplayName("Divide 1 KILOGRAM by 500 GRAM = 2.0")
    void divide_kilogramByGram_correctResult() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g  = new Quantity<>(500.0, WeightUnit.GRAM);
        assertEquals(2.0, kg.divide(g), DELTA);
    }

    @Test
    @DisplayName("Divide by zero should throw ArithmeticException")
    void divide_byZero_throwsArithmeticException() {
        Quantity<WeightUnit> kg   = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> zero = new Quantity<>(0.0, WeightUnit.GRAM);
        assertThrows(ArithmeticException.class, () -> kg.divide(zero));
    }

    @Test
    @DisplayName("Divide should throw for Temperature (unsupported)")
    void divide_temperature_throwsUnsupportedOperation() {
        Quantity<TemperatureUnit> c1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> c2 = new Quantity<>(10.0, TemperatureUnit.CELSIUS);
        assertThrows(UnsupportedOperationException.class, () -> c1.divide(c2));
    }

    @Test
    @DisplayName("Divide should throw when other is null")
    void divide_nullOther_throwsException() {
        Quantity<WeightUnit> g = new Quantity<>(5.0, WeightUnit.GRAM);
        assertThrows(IllegalArgumentException.class, () -> g.divide(null));
    }

    // ─────────────────────────────────────────────────────────
    //  CROSS-TYPE ARITHMETIC VALIDATION
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Add between different types should throw")
    void add_differentMeasurementTypes_throwsException() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        // Workaround to force cross-type: use raw type (as service does)
        Quantity rawLength = length;
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.GRAM);
        Quantity rawWeight = weight;
        assertThrows(IllegalArgumentException.class, () -> rawLength.add(rawWeight));
    }

    // ─────────────────────────────────────────────────────────
    //  TOSTRING
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("toString should return value and unit")
    void toString_returnsCorrectFormat() {
        Quantity<LengthUnit> q = new Quantity<>(5.0, LengthUnit.FEET);
        assertEquals("5.0 FEET", q.toString());
    }

    // ─────────────────────────────────────────────────────────
    //  HASHCODE
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Equal quantities should have same hashCode")
    void hashCode_equalQuantities_sameHash() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inch = new Quantity<>(12.0, LengthUnit.INCH);
        assertEquals(feet.hashCode(), inch.hashCode());
    }
}
