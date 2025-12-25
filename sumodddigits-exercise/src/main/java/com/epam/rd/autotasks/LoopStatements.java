package com.epam.rd.autotasks;

class LoopStatements {
    public static int sumOddDigits(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();

        else {
            int sum = 0;

            while (n > 0){
                int digit = n % 10;

                if (digit % 2 != 0){
                    sum += digit;
                }

                n = n / 10;
            }
            return sum;
        }
    }
}
