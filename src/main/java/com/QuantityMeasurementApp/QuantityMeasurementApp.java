package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("Equal: " + l1.equals(l2));

        Length i1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length i2 = new Length(1.0, Length.LengthUnit.INCHES);

        System.out.println("Equal: " + i1.equals(i2));
    }
}