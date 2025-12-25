package com.epam.rd.autotasks.snail;

import java.util.Scanner;

public class Snail
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        int h = input.nextInt();

        if(a >= h){
            System.out.println(1);
            return;
        }

        if(a-b<=0){
            System.out.println("Impossible");
            return;
        }

        int heightLeft = h;
        int counter = 0;

        while(heightLeft > 0){
            heightLeft -= a;
            counter++;
            if (heightLeft <= 0)
                break;
            heightLeft += b;
        }

        System.out.println(counter);
    }
}
