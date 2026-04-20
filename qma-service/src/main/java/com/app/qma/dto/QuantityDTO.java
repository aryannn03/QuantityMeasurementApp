package com.app.qma.dto;

import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class QuantityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

    @NotNull(message="Value cannot be null")
    private Double value;

    @NotEmpty(message="Unit cannot be empty")
    private String unit;

    @NotEmpty(message="Measurement type cannot be empty")
    private String measurementType;

    public QuantityDTO(){}

    public QuantityDTO(Double value,String unit,String measurementType){
        this.value=value;
        this.unit=unit;
        this.measurementType=measurementType;
    }

    public Double getValue(){
        return value;
    }

    public void setValue(Double value){
        this.value=value;
    }

    public String getUnit(){
        return unit;
    }

    public void setUnit(String unit){
        this.unit=unit;
    }

    public String getMeasurementType(){
        return measurementType;
    }

    public void setMeasurementType(String measurementType){
        this.measurementType=measurementType;
    }
}