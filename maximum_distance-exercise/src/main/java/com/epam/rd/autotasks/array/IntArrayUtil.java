package com.epam.rd.autotasks.array;

public class IntArrayUtil {

	public static int maximumDistance(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}

		int max = array[0];
		int last = 0;
		int first = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] > array[array.length - i - 1]) {
				max = array[i];
			}
		}

		for (int i = 0; i < array.length; i++) {
			if (array[i] == max){
				last = i;
			}
		}

		for (int i = 0; i < array.length; i++) {
			if (array[i] == max){
				first = i;
				break;
			}
		}

		return last - first;
	}

	public static void main(String[] args) {
		{
			int[] array = null;
			System.out.println("result = " + maximumDistance(array)); // 0
		}
		{
			int[] array = new int[] { 0 };
			System.out.println("result = " + maximumDistance(array)); // 0
		}
		{
			int[] array = new int[] { 4, 100, 3, 4 };
			System.out.println("result = " + maximumDistance(array)); // 0
		}
		{
			int[] array = new int[] { 5, 50, 50, 4, 5 };
			System.out.println("result = " + maximumDistance(array)); // 1
		}
		{
			int[] array = new int[] { 5, 350, 350, 4, 350 };
			System.out.println("result = " + maximumDistance(array)); // 3
		}
		{
			int[] array = new int[] { 10, 10, 10, 10, 10 };
			System.out.println("result = " + maximumDistance(array)); // 4
		}
	}

}
