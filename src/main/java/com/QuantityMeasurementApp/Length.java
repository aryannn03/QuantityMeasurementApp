package com.QuantityMeasurementApp;

public class Length {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 0.000001;

    public Length(double value, LengthUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    // Convert current value to base unit (inches)
    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    // Convert this Length to another unit
    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Length(converted, targetUnit);
    }

    // Static conversion helper
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (source == null || target == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        double baseValue = source.convertToBaseUnit(value);

        return target.convertFromBaseUnit(baseValue);
    }

    // Add two lengths using this unit as result unit
    public Length add(Length other) {

        if (other == null)
            throw new IllegalArgumentException("Length cannot be null");

        double baseSum = this.toBaseUnit() + other.toBaseUnit();

        double resultValue = unit.convertFromBaseUnit(baseSum);

        return new Length(resultValue, unit);
    }

    // Add two lengths with explicit target unit
    public Length add(Length other, LengthUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Length cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseSum = this.toBaseUnit() + other.toBaseUnit();

        double resultValue = targetUnit.convertFromBaseUnit(baseSum);

        return new Length(resultValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Length other = (Length) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}