package com.lizi.Main;

import com.lizi.algorithm.sort.MergeSort;

public class MergeSortMain {
	public static void main(String[] args) {

		double []array={2,2,5,3,7,9,4,8,0,1,6};
		//MergeSort.sortTop(array, 0, array.length-1);
		MergeSort.sortButtom(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+"   ");
		}
	}

}
