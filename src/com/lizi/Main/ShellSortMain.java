package com.lizi.Main;

import com.lizi.algorithm.sort.ShellSort;

public class ShellSortMain {

	public static void main(String[] args) {
		int []array={2,2,5,3,7,9,4,8,0,1,6};
		ShellSort.sort(array);
        ShellSort.print(array);
	}

}