package com.QuantityMeasurementApp;

public interface IMeasurable {

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    // UC14 operation support methods

    default boolean supportsAddition() {
        return true;
    }

    default boolean supportsSubtraction() {
        return true;
    }

    default boolean supportsDivision() {
        return true;
    }

    default void validateOperationSupport(String operation) {
        // default allows operation
    }

    // UC15 helper methods

    String getMeasurementType();

    static IMeasurable getUnitInstance(String unitName) {

        for (LengthUnit u : LengthUnit.values())
            if (u.name().equalsIgnoreCase(unitName))
                return u;

        for (WeightUnit u : WeightUnit.values())
            if (u.name().equalsIgnoreCase(unitName))
                return u;

        for (VolumeUnit u : VolumeUnit.values())
            if (u.name().equalsIgnoreCase(unitName))
                return u;

        for (TemperatureUnit u : TemperatureUnit.values())
            if (u.name().equalsIgnoreCase(unitName))
                return u;

        throw new IllegalArgumentException("Invalid unit: " + unitName);
    }
}