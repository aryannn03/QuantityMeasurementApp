package com.app.quantitymeasurement.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit Enum Tests")
public class UnitEnumTest {

    private static final double DELTA = 0.0001;

    // ─────────────────────────────────────────────────────────
    //  LENGTH UNIT
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("LengthUnit: FEET base conversion is correct")
    void lengthUnit_feet_toBaseUnit() {
        assertEquals(12.0, LengthUnit.FEET.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    @DisplayName("LengthUnit: INCH is base unit (factor 1)")
    void lengthUnit_inch_isBaseUnit() {
        assertEquals(1.0, LengthUnit.INCH.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    @DisplayName("LengthUnit: YARD to base = 36 inches")
    void lengthUnit_yard_toBaseUnit() {
        assertEquals(36.0, LengthUnit.YARD.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    @DisplayName("LengthUnit: CENTIMETER to base correct")
    void lengthUnit_centimeter_toBaseUnit() {
        assertEquals(0.393701, LengthUnit.CENTIMETER.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    @DisplayName("LengthUnit: convertFromBaseUnit inverts correctly")
    void lengthUnit_convertFromBaseUnit_correct() {
        double base = LengthUnit.FEET.convertToBaseUnit(5.0);
        double back = LengthUnit.FEET.convertFromBaseUnit(base);
        assertEquals(5.0, back, DELTA);
    }

    @Test
    @DisplayName("LengthUnit: supportsArithmetic returns true")
    void lengthUnit_supportsArithmetic_true() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    @Test
    @DisplayName("LengthUnit: getUnitName returns enum name")
    void lengthUnit_getUnitName() {
        assertEquals("FEET", LengthUnit.FEET.getUnitName());
        assertEquals("INCH", LengthUnit.INCH.getUnitName());
    }

    // ─────────────────────────────────────────────────────────
    //  WEIGHT UNIT
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("WeightUnit: KILOGRAM to GRAM base = 1000")
    void weightUnit_kilogram_toBaseUnit() {
        assertEquals(1000.0, WeightUnit.KILOGRAM.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    @DisplayName("WeightUnit: GRAM is base unit (factor 1)")
    void weightUnit_gram_isBaseUnit() {
        assertEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    @DisplayName("WeightUnit: MILLIGRAM to base = 0.001")
    void weightUnit_milligram_toBaseUnit() {
        assertEquals(0.001, WeightUnit.MILLIGRAM.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    @DisplayName("WeightUnit: POUND to GRAM correct")
    void weightUnit_pound_toGram() {
        assertEquals(453.592, WeightUnit.POUND.convertToBaseUnit(1.0), 0.001);
    }

    @Test
    @DisplayName("WeightUnit: TONNE to GRAM = 1,000,000")
    void weightUnit_tonne_toGram() {
        assertEquals(1000000.0, WeightUnit.TONNE.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    @DisplayName("WeightUnit: supportsArithmetic returns true")
    void weightUnit_supportsArithmetic_true() {
        assertTrue(WeightUnit.GRAM.supportsArithmetic());
    }

    @Test
    @DisplayName("WeightUnit: roundtrip conversion is lossless")
    void weightUnit_roundtrip_lossless() {
        double base = WeightUnit.POUND.convertToBaseUnit(3.0);
        double back = WeightUnit.POUND.convertFromBaseUnit(base);
        assertEquals(3.0, back, DELTA);
    }

    // ─────────────────────────────────────────────────────────
    //  VOLUME UNIT
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("VolumeUnit: LITRE is base unit (factor 1)")
    void volumeUnit_litre_isBaseUnit() {
        assertEquals(1.0, VolumeUnit.LITRE.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    @DisplayName("VolumeUnit: MILLILITRE to base = 0.001")
    void volumeUnit_millilitre_toBaseUnit() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    @DisplayName("VolumeUnit: GALLON to LITRE = 3.78541")
    void volumeUnit_gallon_toLitre() {
        assertEquals(3.78541, VolumeUnit.GALLON.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    @DisplayName("VolumeUnit: supportsArithmetic returns true")
    void volumeUnit_supportsArithmetic_true() {
        assertTrue(VolumeUnit.LITRE.supportsArithmetic());
    }

    @Test
    @DisplayName("VolumeUnit: roundtrip conversion is lossless")
    void volumeUnit_roundtrip_lossless() {
        double base = VolumeUnit.GALLON.convertToBaseUnit(2.5);
        double back = VolumeUnit.GALLON.convertFromBaseUnit(base);
        assertEquals(2.5, back, DELTA);
    }

    // ─────────────────────────────────────────────────────────
    //  TEMPERATURE UNIT
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("TemperatureUnit: 0 CELSIUS to base = 0")
    void temperatureUnit_celsius_toBaseUnit_zero() {
        assertEquals(0.0, TemperatureUnit.CELSIUS.convertToBaseUnit(0.0), DELTA);
    }

    @Test
    @DisplayName("TemperatureUnit: 100 CELSIUS to base = 100")
    void temperatureUnit_celsius_toBaseUnit_hundred() {
        assertEquals(100.0, TemperatureUnit.CELSIUS.convertToBaseUnit(100.0), DELTA);
    }

    @Test
    @DisplayName("TemperatureUnit: 32 FAHRENHEIT to base = 0 CELSIUS")
    void temperatureUnit_fahrenheit_toBaseUnit_freezing() {
        assertEquals(0.0, TemperatureUnit.FAHRENHEIT.convertToBaseUnit(32.0), DELTA);
    }

    @Test
    @DisplayName("TemperatureUnit: 212 FAHRENHEIT to base = 100 CELSIUS")
    void temperatureUnit_fahrenheit_toBaseUnit_boiling() {
        assertEquals(100.0, TemperatureUnit.FAHRENHEIT.convertToBaseUnit(212.0), DELTA);
    }

    @Test
    @DisplayName("TemperatureUnit: base 100 to FAHRENHEIT = 212")
    void temperatureUnit_fahrenheit_fromBaseUnit_boiling() {
        assertEquals(212.0, TemperatureUnit.FAHRENHEIT.convertFromBaseUnit(100.0), DELTA);
    }

    @Test
    @DisplayName("TemperatureUnit: supportsArithmetic returns FALSE")
    void temperatureUnit_supportsArithmetic_false() {
        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
        assertFalse(TemperatureUnit.FAHRENHEIT.supportsArithmetic());
    }

    @Test
    @DisplayName("TemperatureUnit: validateOperationSupport throws for ADD")
    void temperatureUnit_validateOperationSupport_throwsForAdd() {
        assertThrows(UnsupportedOperationException.class,
                () -> TemperatureUnit.CELSIUS.validateOperationSupport("ADD"));
    }

    @Test
    @DisplayName("TemperatureUnit: validateOperationSupport throws for SUBTRACT")
    void temperatureUnit_validateOperationSupport_throwsForSubtract() {
        assertThrows(UnsupportedOperationException.class,
                () -> TemperatureUnit.FAHRENHEIT.validateOperationSupport("SUBTRACT"));
    }

    @Test
    @DisplayName("TemperatureUnit: getUnitName returns enum name")
    void temperatureUnit_getUnitName() {
        assertEquals("CELSIUS", TemperatureUnit.CELSIUS.getUnitName());
        assertEquals("FAHRENHEIT", TemperatureUnit.FAHRENHEIT.getUnitName());
    }

    @Test
    @DisplayName("TemperatureUnit: getConversionFactor always 1.0")
    void temperatureUnit_getConversionFactor_isOne() {
        assertEquals(1.0, TemperatureUnit.CELSIUS.getConversionFactor(), DELTA);
        assertEquals(1.0, TemperatureUnit.FAHRENHEIT.getConversionFactor(), DELTA);
    }
}
