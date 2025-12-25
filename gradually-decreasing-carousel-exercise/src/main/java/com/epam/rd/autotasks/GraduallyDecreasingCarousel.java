package com.epam.rd.autotasks;

import java.util.Arrays;

public class GraduallyDecreasingCarousel extends DecrementingCarousel {
    public GraduallyDecreasingCarousel(final int capacity) {
        super(capacity);
    }

    @Override
    public CarouselRun run() {
        if (!isRunning) {
            isRunning = true;
            return new GraduallyDecreasingCarouselRun(elements, elementCount);
        }
        return null;
    }
}

class GraduallyDecreasingCarouselRun extends CarouselRun {

    private final int[] decrements;

    public GraduallyDecreasingCarouselRun(final int[] carousel, final int count) {
        super(carousel, count);
        // Each element has its own decrement value, which starts at 1 and increases each time it's used.
        this.decrements = new int[this.carousel.length];
        Arrays.fill(this.decrements, 1);
    }

    @Override
    public int next() {
        if (isFinished()) {
            return -1;
        }

        // Find the next positive element to process
        while (carousel[position] <= 0) {
            position = (position + 1) % carousel.length;
        }

        int currentValue = carousel[position];

        // Decrement the element by its current decrement value
        int decrementForThisPos = decrements[position];
        carousel[position] -= decrementForThisPos;

        // Increment the decrement for the next time this element is processed
        decrements[position]++;

        // Move to the next position
        position = (position + 1) % carousel.length;

        return currentValue;
    }
}
