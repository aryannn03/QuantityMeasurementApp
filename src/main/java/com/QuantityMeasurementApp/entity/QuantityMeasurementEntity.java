package com.QuantityMeasurementApp.entity;

public class QuantityMeasurementEntity {

    private int id;
    private double thisValue;
    private String thisUnit;
    private String measurementType;
    private String operation;
    private String resultString;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(String operation, String resultString) {
        this.operation = operation;
        this.resultString = resultString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public double getThisValue() {
        return thisValue;
    }

    public void setThisValue(double thisValue) {
        this.thisValue = thisValue;
    }

    public String getThisUnit() {
        return thisUnit;
    }

    public void setThisUnit(String thisUnit) {
        this.thisUnit = thisUnit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }
}