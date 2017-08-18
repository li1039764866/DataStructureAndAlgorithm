package com.lizi.algorithm.sort;
//选择排序算法，复杂度n^2
public class SelectionSort {
	public static void sort(int []array) {
		int length=array.length;
		for (int i = 0; i < length; i++) {
			int min=i;//记录子数组中最小数的下标
			for (int j = i+1; j < length; j++) {
				if (array[j]<array[min]) {//循环记录，直至找出最小值
					min=j;
				}
			}
			int mid=array[min];//将余下子数组中最小的数交换到子数组前面去
			array[min]=array[i];
			array[i]=mid;
		}
	}
	public static void print(int []array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+"  ");
		}
		System.out.println();
	}
}
