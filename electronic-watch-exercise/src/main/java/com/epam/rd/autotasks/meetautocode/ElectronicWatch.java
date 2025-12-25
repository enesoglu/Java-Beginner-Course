package com.epam.rd.autotasks.meetautocode;

import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();

        int secondsOnClock = seconds % 86400;
        int hours = secondsOnClock/3600;
        int minutes = secondsOnClock%3600/60;
        int second = secondsOnClock%60;

        System.out.printf("%d:%02d:%02d", hours, minutes, second);
    }
}
