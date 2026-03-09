package com.QuantityMeasurementApp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null)
                return false;

            if (getClass() != obj.getClass())
                return false;

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first value in feet: ");
        double first = sc.nextDouble();

        System.out.print("Enter second value in feet: ");
        double second = sc.nextDouble();

        Feet f1 = new Feet(first);
        Feet f2 = new Feet(second);

        boolean result = f1.equals(f2);

        System.out.println("Equal: " + result);

        sc.close();
    }
}
