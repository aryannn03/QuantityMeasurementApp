package com.QuantityMeasurementApp;

public interface IMeasurable{

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();


    default boolean supportsAddition(){
        return true;
    }

    default boolean supportsSubtraction(){
        return true;
    }

    default boolean supportsDivision(){
        return true;
    }

    default void validateOperationSupport(String operation){
    }
}