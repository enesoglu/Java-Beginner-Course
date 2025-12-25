package com.epam.rd.autotasks.pizzasplit;

import java.util.Scanner;

public class PizzaSplit {

    public static int findGCD(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int numberOfPeople = scan.nextInt();
        int piecesPerPizza = scan.nextInt();

        int lcm = (numberOfPeople * piecesPerPizza) / findGCD(piecesPerPizza, numberOfPeople);

        System.out.println(lcm/piecesPerPizza);
    }
}
