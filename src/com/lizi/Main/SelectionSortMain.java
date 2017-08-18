package com.lizi.Main;

import com.lizi.algorithm.sort.SelectionSort;

public class SelectionSortMain {

	public static void main(String[] args) {
		int []array={2,2,5,3,7,9,4,8,0,1,6};
		SelectionSort.sort(array);
        SelectionSort.print(array);
	}

}
