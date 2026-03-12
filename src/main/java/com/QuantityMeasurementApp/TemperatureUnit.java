package com.QuantityMeasurementApp;

public enum TemperatureUnit implements IMeasurable{

    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    @Override
    public double getConversionFactor(){
        return 1;
    }

    @Override
    public double convertToBaseUnit(double value){

        switch(this){
            case CELSIUS:
                return value;

            case FAHRENHEIT:
                return (value-32)*5/9;

            case KELVIN:
                return value-273.15;

            default:
                throw new IllegalArgumentException("Unknown temperature unit");
        }
    }

    @Override
    public double convertFromBaseUnit(double baseValue){

        switch(this){
            case CELSIUS:
                return baseValue;

            case FAHRENHEIT:
                return baseValue*9/5+32;

            case KELVIN:
                return baseValue+273.15;

            default:
                throw new IllegalArgumentException("Unknown temperature unit");
        }
    }

    @Override
    public String getUnitName(){
        return this.name();
    }

    // Temperature does not support arithmetic

    @Override
    public boolean supportsAddition(){
        return false;
    }

    @Override
    public boolean supportsSubtraction(){
        return false;
    }

    @Override
    public boolean supportsDivision(){
        return false;
    }

    @Override
    public void validateOperationSupport(String operation){
        throw new UnsupportedOperationException(
                "Temperature does not support "+operation+" operation");
    }

	@Override
	public String getMeasurementType() {
		return "Temperature";
	}
}