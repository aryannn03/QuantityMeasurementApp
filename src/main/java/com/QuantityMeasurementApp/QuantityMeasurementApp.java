package com.QuantityMeasurementApp;

public class QuantityMeasurementApp{

    public static <U extends IMeasurable> boolean demonstrateEquality(
            Quantity<U>quantity1,
            Quantity<U>quantity2){

        if(quantity1==null||quantity2==null)
            throw new IllegalArgumentException("Quantity cannot be null");

        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U>quantity,
            U targetUnit){

        if(quantity==null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if(targetUnit==null)
            throw new IllegalArgumentException("Target unit cannot be null");

        return quantity.convertTo(targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U>quantity1,
            Quantity<U>quantity2){

        return quantity1.add(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U>quantity1,
            Quantity<U>quantity2,
            U targetUnit){

        return quantity1.add(quantity2,targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U>quantity1,
            Quantity<U>quantity2){

        return quantity1.subtract(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U>quantity1,
            Quantity<U>quantity2,
            U targetUnit){

        return quantity1.subtract(quantity2,targetUnit);
    }

    public static <U extends IMeasurable> double demonstrateDivision(
            Quantity<U>quantity1,
            Quantity<U>quantity2){

        return quantity1.divide(quantity2);
    }

    public static void main(String[]args){

        Quantity<LengthUnit>l1=new Quantity<>(10.0,LengthUnit.FEET);
        Quantity<LengthUnit>l2=new Quantity<>(6.0,LengthUnit.INCHES);

        Quantity<LengthUnit>subResult=demonstrateSubtraction(l1,l2);

        System.out.println("Subtraction result: "
                +subResult.getValue()+" "+subResult.getUnit());

        double divisionResult=demonstrateDivision(
                new Quantity<>(10.0,LengthUnit.FEET),
                new Quantity<>(2.0,LengthUnit.FEET));

        System.out.println("Division result: "+divisionResult);
    }
}