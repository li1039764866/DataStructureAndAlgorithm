package com.lizi.Main;

import com.lizi.algorithm.sort.QuickSort;

public class QuickSortMain {

	public static void main(String[] args) {
		double []array={2,10,5,3,7,9,4,8,0,1,6};
		QuickSort.sort1(array, 0, array.length-1);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+"    ");
		}
		
		int []array1={2,10,5,3,7,9,4,8,0,1,6};
		QuickSort.sort(array1, 0, array1.length-1);
		System.out.println("¿ìËÙÅÅÐò£º");
		for (int i = 0; i < array1.length; i++) {
			System.out.print(array1[i]+"    ");
		}
	}

}
