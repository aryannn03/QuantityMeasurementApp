package com.QuantityMeasurementApp;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 0.000001;

    public Weight(double value, WeightUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);

        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Weight(converted, targetUnit);
    }

    public Weight add(Weight other) {

        if (other == null)
            throw new IllegalArgumentException("Weight cannot be null");

        double baseSum = this.toBaseUnit() + other.toBaseUnit();

        double resultValue = unit.convertFromBaseUnit(baseSum);

        return new Weight(resultValue, unit);
    }

    public Weight add(Weight other, WeightUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Weight cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseSum = this.toBaseUnit() + other.toBaseUnit();

        double resultValue = targetUnit.convertFromBaseUnit(baseSum);

        return new Weight(resultValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Weight other = (Weight) obj;

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