package com.epam.rd.autotasks.arrays;

import java.util.Arrays;

public class LocalMaximaRemove {

    public static void main(String[] args) {
        int[] array = new int[]{18, 1, 3, 6, 7, -5};

        System.out.println(Arrays.toString(removeLocalMaxima(array)));
    }

    public static int[] removeLocalMaxima(int[] array) {

        int n = array.length;
        boolean[] toRemove = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            boolean isLocalMax = false;

            if (i == 0) {
                if (array[i] > array[i + 1]) isLocalMax = true;
            }
            else if (i == n - 1) {
                if (array[i] > array[i - 1]) isLocalMax = true;
            }
            else {
                if (array[i] > array[i - 1] && array[i] > array[i + 1]) isLocalMax = true;
            }

            if (isLocalMax) {
                toRemove[i] = true;
                count++;
            }
        }

        int[] result = new int[n - count];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (!toRemove[i]) {
                result[j++] = array[i];
            }
        }

        return result;

    }
}
