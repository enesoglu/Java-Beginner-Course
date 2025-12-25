package com.epam.rd.autotasks.godutch;

import java.util.Scanner;

public class GoDutch {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int bill = scan.nextInt();
        int friends = scan.nextInt();

        if (friends <= 0) {
            System.out.println("Number of friends cannot be negative or zero");
            return;
        } else if (bill < 0) {
            System.out.println("Bill total amount cannot be negative");
            return;
        }

        int billWithTip = (int) (bill + (bill*0.1));

        System.out.println(billWithTip / friends);

    }
}
