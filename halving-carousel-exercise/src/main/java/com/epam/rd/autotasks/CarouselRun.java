package com.epam.rd.autotasks;

import java.util.Arrays;

public class CarouselRun {

    private final int[] carousel;
    private int position = 0;
    private final boolean isHalving;

    CarouselRun(int[] carousel, int count) {
        this(carousel, count, false);
    }

    CarouselRun(int[] carousel, int count, boolean isHalving) {
        this.carousel = Arrays.copyOf(carousel, count);
        this.isHalving = isHalving;
    }

    public int next() {
        if (isFinished()) {
            return -1;
        }

        while (carousel.length > 0 && carousel[position] == 0) {
            position = (position + 1) % carousel.length;
        }

        int currentValue = carousel[position];

        if (isHalving) {
            carousel[position] /= 2;
        } else {
            carousel[position]--;
        }

        position = (position + 1) % carousel.length;

        return currentValue;
    }

    public boolean isFinished() {
        for (int element : carousel) {
            if (element > 0) {
                return false;
            }
        }
        return true;
    }
}
