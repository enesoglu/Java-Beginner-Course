package com.epam.rd.autotasks;

public class HalvingCarousel extends DecrementingCarousel {

    public HalvingCarousel(final int capacity) {
        super(capacity);
    }

    @Override
    public CarouselRun run() {
        if (!isRunning) {
            isRunning = true;
            return new CarouselRun(elements, elementCount, true);
        }
        return null;
    }
}
