package com.epam.rd.autotasks;

import java.util.Arrays;

class CycleSwap {
    static void cycleSwap(int[] array) {

        if (array == null || array.length < 2) {
            return;
        }

        int lastElement = array[array.length - 1];

        for (int i = array.length-1; i > 0; i--) {
            array[i] = array[i-1];
        }
        array[0] = lastElement;

    }

    static void cycleSwap(int[] array, int shift) {

        if (array == null || array.length == 0) {
            return;
        }

        int realShift = shift % array.length;

        if (realShift == 0){
            return;
        }

        int[] originalArray = Arrays.copyOf(array, array.length);

        for (int i = 0; i < originalArray.length; i++) {
            int newPosition = (i + realShift) % originalArray.length;

            array[newPosition] = originalArray[i];
        }
    }

    public static void main(String[] args) {
        {
            int[] array = new int[]{1, 3, 2, 7, 4};
            CycleSwap.cycleSwap(array);
            System.out.println(Arrays.toString(array)); // Should be [4, 1, 3, 2, 7]
        }
        {
            int[] array = new int[]{1, 3, 2, 7, 4};
            CycleSwap.cycleSwap(array, 2);
            System.out.println(Arrays.toString(array)); // Should be [7, 4, 1, 3, 2]
        }
        {
            int[] array = new int[]{1, 3, 2, 7, 4};
            CycleSwap.cycleSwap(array, 5);
            System.out.println(Arrays.toString(array)); // Should be [1, 3, 2, 7, 4]
        }
    }
}
