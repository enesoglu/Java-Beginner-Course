package com.epam.rd.autotasks;

public class DecrementingCarousel {

    private final int capacity;
    private final int[] elements;
    private int elementCount;
    private boolean isRunning;

    public DecrementingCarousel(int capacity) {
        this.capacity = capacity;
        this.elements = new int[capacity];
        this.elementCount = 0;
        this.isRunning = false;
    }

    public boolean addElement(int element){
        if ( element > 0 && elementCount < capacity && !isRunning){
            elements[elementCount++] = element;
            return true;
        }
        return false;
    }

    public CarouselRun run(){
       if (!isRunning){
           isRunning = true;
           return new CarouselRun(elements, elementCount);
       }
       return null;
    }
}
