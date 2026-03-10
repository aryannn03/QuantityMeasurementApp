package com.QuantityMeasurementApp;

public class Quantity<U extends IMeasurable>{

    private final double value;
    private final U unit;

    private static final double EPSILON = 0.000001;

    public Quantity(double value, U unit){

        if(unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if(!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue(){
        return value;
    }

    public U getUnit(){
        return unit;
    }

    private double toBaseUnit(){
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit){

        if(targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(converted, targetUnit);
    }

    // UC13 CENTRALIZED ARITHMETIC 

    private enum ArithmeticOperation{
        ADD{
            double apply(double a,double b){
                return a+b;
            }
        },
        SUBTRACT{
            double apply(double a,double b){
                return a-b;
            }
        },
        DIVIDE{
            double apply(double a,double b){
                return a/b;
            }
        };

        abstract double apply(double a,double b);
    }
    private double performArithmetic(Quantity<U> other,ArithmeticOperation operation){

        if(other==null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if(this.unit.getClass()!=other.unit.getClass())
            throw new IllegalArgumentException("Incompatible measurement categories");

        switch(operation){

            case ADD:
                if(!unit.supportsAddition())
                    unit.validateOperationSupport("addition");
                break;

            case SUBTRACT:
                if(!unit.supportsSubtraction())
                    unit.validateOperationSupport("subtraction");
                break;

            case DIVIDE:
                if(!unit.supportsDivision())
                    unit.validateOperationSupport("division");
                break;
        }

        double base1=this.toBaseUnit();
        double base2=other.toBaseUnit();

        if(operation==ArithmeticOperation.DIVIDE && Math.abs(base2)<EPSILON)
            throw new ArithmeticException("Division by zero");

        return operation.apply(base1,base2);
    }

    // ADD 

    public Quantity<U> add(Quantity<U> other){

        double baseSum = performArithmetic(other, ArithmeticOperation.ADD);

        double result = unit.convertFromBaseUnit(baseSum);

        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit){

        if(targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseSum = performArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(baseSum);

        return new Quantity<>(result, targetUnit);
    }

    // SUBTRACT 

    public Quantity<U> subtract(Quantity<U> other){

        double baseResult = performArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit){

        if(targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseResult = performArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result, targetUnit);
    }

    // DIVIDE 

    public double divide(Quantity<U> other){

        return performArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // EQUALITY 

    @Override
    public boolean equals(Object obj){

        if(this == obj)
            return true;

        if(obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if(this.unit.getClass() != other.unit.getClass())
            return false;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode(){
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString(){
        return value + " " + unit;
    }
}