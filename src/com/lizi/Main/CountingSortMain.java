package com.lizi.Main;

import com.lizi.algorithm.sort.CountingSort;

public class CountingSortMain {

	public static void main(String[] args) {
		int []array={2,9,5,3,7,9,4,8,1,6,2};
		array=CountingSort.sort(array, 10);
		System.out.println("≈≈–Ú∫Û£∫");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}

	}

}
