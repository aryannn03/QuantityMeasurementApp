package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static double demonstrateLengthConversion(double value,
                                                     Length.LengthUnit from,
                                                     Length.LengthUnit to) {
        return Length.convert(value, from, to);
    }

    public static Length demonstrateLengthConversion(Length length,
                                                     Length.LengthUnit to) {
        return length.convertTo(to);
    }

    public static void main(String[] args) {

        System.out.println(demonstrateLengthConversion(1.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES));

        System.out.println(demonstrateLengthConversion(3.0,
                Length.LengthUnit.YARDS,
                Length.LengthUnit.FEET));

        System.out.println(demonstrateLengthConversion(36.0,
                Length.LengthUnit.INCHES,
                Length.LengthUnit.YARDS));

        System.out.println(demonstrateLengthConversion(1.0,
                Length.LengthUnit.CENTIMETERS,
                Length.LengthUnit.INCHES));
    }
}