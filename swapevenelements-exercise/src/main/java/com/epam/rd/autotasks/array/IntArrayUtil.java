package com.epam.rd.autotasks.array;

import java.util.Arrays;

public class IntArrayUtil {

	public static void swapEven(int[] array) {
		if (array != null) {
			for (int left = 0, right = array.length - 1; left < right; left++, right--) {
				if (array[left] % 2 == 0 && array[right] % 2 == 0) {

					int temp = array[left];
					array[left] = array[right];
					array[right] = temp;
				}
			}
        }
	}



	public static void main(String[] args) {



		{
			int[] array = null;
			swapEven(array);
			System.out.println(Arrays.toString(array));
		}
		{
			int[] array = new int[] { 100, 2, 3, 45, 33, 8, 4, 54 };
			swapEven(array);
			System.out.println(Arrays.toString(array));
		}
	}

}
