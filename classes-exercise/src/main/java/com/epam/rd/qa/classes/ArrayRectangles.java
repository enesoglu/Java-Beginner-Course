package com.epam.rd.qa.classes;

public class ArrayRectangles {

    private final Rectangle[] rectangleArray;

    public ArrayRectangles(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        this.rectangleArray = new Rectangle[size];
    }

    public ArrayRectangles(Rectangle... rectangles) {
        if (rectangles == null || rectangles.length == 0) {
            throw new IllegalArgumentException();
        }
        this.rectangleArray = rectangles;
    }

    public boolean addRectangle(Rectangle rectangle) {
        if (rectangle == null) {
            return false;
        }
        for (int i = 0; i < this.rectangleArray.length; i++) {
            if (this.rectangleArray[i] == null) {
                this.rectangleArray[i] = rectangle;
                return true;
            }
        }
        return false;
    }

    public int size() {
        int count = 0;
        for (Rectangle rect : this.rectangleArray) {
            if (rect != null) {
                count++;
            }
        }
        return count;
    }

    public int indexMaxArea() {
        int index = -1;
        double maxArea = 0.0;

        for (int i = 0; i < this.rectangleArray.length; i++) {
            Rectangle currentRect = this.rectangleArray[i];
            if (currentRect != null) {
                if (index == -1 || currentRect.area() > maxArea) {
                    maxArea = currentRect.area();
                    index = i;
                }
            }
        }
        return index;
    }

    public int indexMinPerimeter() {
        int index = -1;
        double minPerimeter = Double.MAX_VALUE;

        for (int i = 0; i < this.rectangleArray.length; i++) {
            Rectangle currentRect = this.rectangleArray[i];
            if (currentRect != null) {
                if (index == -1 || currentRect.perimeter() < minPerimeter) {
                    minPerimeter = currentRect.perimeter();
                    index = i;
                }
            }
        }
        return index;
    }

    public int numberSquares() {
        int count = 0;
        for (Rectangle rect : this.rectangleArray) {
            if (rect != null && rect.isSquare()) {
                count++;
            }
        }
        return count;
    }
}