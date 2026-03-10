package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    /**
     * Demonstrate equality comparison between two quantities.
     */
    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1,Quantity<U> quantity2) {

        if (quantity1 == null || quantity2 == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        return quantity1.equals(quantity2);
    }

    /**
     * Demonstrate conversion of a quantity to a target unit.
     */
    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity,U targetUnit) {

        if (quantity == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        return quantity.convertTo(targetUnit);
    }

    /**
     * Demonstrate addition of two quantities.
     * Result returned in the unit of the first quantity.
     */
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1,Quantity<U> quantity2) {

        if (quantity1 == null || quantity2 == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        return quantity1.add(quantity2);
    }

    /**
     * Demonstrate addition of two quantities with explicit target unit.
     */
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1,Quantity<U> quantity2,U targetUnit) {

        if (quantity1 == null || quantity2 == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        return quantity1.add(quantity2, targetUnit);
    }

    public static void main(String[] args) {

        // LENGTH EXAMPLE 

        Quantity<LengthUnit> lengthInFeet =new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> lengthInInches =new Quantity<>(12.0, LengthUnit.INCHES);

        boolean lengthEqual =demonstrateEquality(lengthInFeet, lengthInInches);

        System.out.println("Are lengths equal? " + lengthEqual);

        Quantity<LengthUnit> convertedLength =demonstrateConversion(lengthInFeet, LengthUnit.INCHES);

        System.out.println("Converted length: "+ convertedLength.getValue()+ " "+ convertedLength.getUnit());

        Quantity<LengthUnit> totalLength =demonstrateAddition(lengthInFeet,lengthInInches,LengthUnit.FEET);

        System.out.println("Total length: "+ totalLength.getValue()+ " "+ totalLength.getUnit());


        // WEIGHT EXAMPLE 

        Quantity<WeightUnit> weightInKg =new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weightInGrams =new Quantity<>(1000.0, WeightUnit.GRAM);

        boolean weightEqual =demonstrateEquality(weightInKg, weightInGrams);

        System.out.println("Are weights equal? " + weightEqual);

        Quantity<WeightUnit> convertedWeight =demonstrateConversion(weightInGrams,WeightUnit.KILOGRAM);

        System.out.println("Converted weight: "+ convertedWeight.getValue()+ " "+ convertedWeight.getUnit());

        Quantity<WeightUnit> weightInPounds =new Quantity<>(2.20462, WeightUnit.POUND);

        Quantity<WeightUnit> totalWeight =demonstrateAddition(weightInKg, weightInPounds);

        System.out.println("Total weight: "+ totalWeight.getValue()+ " "+ totalWeight.getUnit());

        Quantity<WeightUnit> weightInGramsResult =demonstrateAddition(weightInKg,weightInPounds,WeightUnit.GRAM);

        System.out.println("Total weight in grams: "+ weightInGramsResult.getValue()+ " "+ weightInGramsResult.getUnit());
        
     // VOLUME EXAMPLE

        Quantity<VolumeUnit>volumeInLitre=new Quantity<>(1.0,VolumeUnit.LITRE);

        Quantity<VolumeUnit>volumeInMilli=new Quantity<>(1000.0,VolumeUnit.MILLILITRE);

        boolean volumeEqual=demonstrateEquality(volumeInLitre,volumeInMilli);

        System.out.println("Are volumes equal? "+volumeEqual);

        Quantity<VolumeUnit>convertedVolume=demonstrateConversion(volumeInLitre,VolumeUnit.MILLILITRE);

        System.out.println("Converted volume: "+convertedVolume.getValue()+" "+convertedVolume.getUnit());

        Quantity<VolumeUnit>volumeInGallon=new Quantity<>(1.0,VolumeUnit.GALLON);

        Quantity<VolumeUnit>totalVolume=demonstrateAddition(volumeInLitre,volumeInMilli);

        System.out.println("Total volume: "+totalVolume.getValue()+" "+totalVolume.getUnit());

        Quantity<VolumeUnit>volumeInMilliResult=demonstrateAddition(volumeInLitre,volumeInGallon,VolumeUnit.MILLILITRE);

        System.out.println("Total volume in millilitre: "+volumeInMilliResult.getValue()+" "+volumeInMilliResult.getUnit());
    }
}