package com.epam.rd.autotasks;

import java.util.Arrays;

public class CarouselRun {

    protected final int[] carousel;
    protected int position = 0;

    CarouselRun(int[] carousel, int count) {
        this.carousel = Arrays.copyOf(carousel, count);
    }

    public int next() {
        if (isFinished()){
            return -1;
        }

        while (carousel[position] == 0){
            position = (position + 1) % carousel.length;
        }

        int currentValue = carousel[position];
        carousel[position]--;

        position = (position + 1) % carousel.length;

        return currentValue;
    }

    public boolean isFinished() {

        for (int element : carousel) {
            if (element > 0){
                return false;
            }
        }
        return true;
    }
}
