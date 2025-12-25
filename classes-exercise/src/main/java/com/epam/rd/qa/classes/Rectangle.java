package com.epam.rd.qa.classes;

public class Rectangle {

    private double sideA;
    private double sideB;

    public Rectangle(double a, double b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException();
        }
        this.sideA = a;
        this.sideB = b;
    }

    public Rectangle(double side) {
        this(side, side);
    }

    public Rectangle() {
        this.sideA = 4;
        this.sideB = 3;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double area() {
        return this.sideA * this.sideB;
    }

    public double perimeter() {
        return 2 * (this.sideA + this.sideB);
    }

    public boolean isSquare() {
        return this.sideA == this.sideB;
    }

    public void replaceSides() {
        double temp = this.sideA;
        this.sideA = this.sideB;
        this.sideB = temp;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Rectangle rectangle = (Rectangle) object;

        return (this.sideA == rectangle.sideA && this.sideB == rectangle.sideB) ||
                (this.sideA == rectangle.sideB && this.sideB == rectangle.sideA);
    }

    @Override
    public int hashCode() {
        long hash = Double.doubleToLongBits(sideA + sideB) + Double.doubleToLongBits(sideA * sideB);
        return Long.hashCode(hash);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "sideA=" + sideA +
                ", sideB=" + sideB +
                '}';
    }
}