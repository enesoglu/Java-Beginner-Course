package com.epam.rd.autotasks;

class LoopStatements {
    public static int sumOfFibonacciNumbers(int n) {

        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 1 || n == 0) {
            return 0;
        }

        int a = 0;
        int b = 1;
        int sum = 1;

        for (int i = 2; i < n; i++) {
            int next = a + b;

            sum += next;
            a = b;
            b = next;
        }
        return sum;
    }
}
