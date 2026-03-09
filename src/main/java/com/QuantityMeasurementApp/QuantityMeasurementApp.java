package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static double demonstrateLengthConversion(double value,
                                                     LengthUnit from,
                                                     LengthUnit to) {
        return Length.convert(value, from, to);
    }

    public static Length demonstrateLengthConversion(Length length,
                                                      LengthUnit to) {
        return length.convertTo(to);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        if (l1 == null || l2 == null)
            throw new IllegalArgumentException("Length cannot be null");

        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1,
                                                   Length l2,
                                                   LengthUnit targetUnit) {
        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        return l1.add(l2, targetUnit);
    }
    public static boolean demonstrateWeightEquality(Weight w1, Weight w2) {
        return w1.equals(w2);
    }

    public static Weight demonstrateWeightConversion(Weight weight,
                                                      WeightUnit to) {
        return weight.convertTo(to);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2) {
        return w1.add(w2);
    }

    public static Weight demonstrateWeightAddition(
            Weight w1,
            Weight w2,
            WeightUnit targetUnit) {

        return w1.add(w2, targetUnit);
    }

    public static void main(String[] args) {

        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);
        System.out.println(demonstrateLengthAddition(a, b));

        Length c = new Length(1.0, LengthUnit.YARDS);
        Length d = new Length(3.0, LengthUnit.FEET);
        System.out.println(demonstrateLengthAddition(c, d));

        Length e = new Length(2.54, LengthUnit.CENTIMETERS);
        Length f = new Length(1.0, LengthUnit.INCHES);
        System.out.println(demonstrateLengthAddition(e, f));

        System.out.println(demonstrateLengthAddition(a, b, LengthUnit.FEET));
        System.out.println(demonstrateLengthAddition(a, b, LengthUnit.INCHES));
        System.out.println(demonstrateLengthAddition(a, b, LengthUnit.YARDS));
        System.out.println(demonstrateLengthAddition(c, d, LengthUnit.YARDS));

        Length g = new Length(36.0, LengthUnit.INCHES);
        Length h = new Length(1.0, LengthUnit.YARDS);

        System.out.println(demonstrateLengthAddition(g, h, LengthUnit.FEET));
        
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        System.out.println("Weight equality: " + demonstrateWeightEquality(w1, w2));

        System.out.println("Weight addition: " + demonstrateWeightAddition(w1, w2));
    }
}