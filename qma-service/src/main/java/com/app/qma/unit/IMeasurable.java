package com.app.qma.unit;

@FunctionalInterface
interface SupportsArithmetic{
    boolean isSupported();
}

public interface IMeasurable {

    SupportsArithmetic supportsArithmetic=()->true;

    String getUnitName();

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    default boolean supportsArithmetic(){
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation){
    }
}