package com.lizi.algorithm.sort;

import com.lizi.tool.General;

//插入排序算法，复杂度n^2，插入排序对部分有序的数组特别有效
public class InsertionSort {
	
	public static void sort(int []array) {
		int length=array.length;
		for (int i = 1; i < length; i++) {
			for (int j = i; j>0&&array[j]<array[j-1]; j--) {//依次与前面较大的值进行交换
					General.swap(array, j, j-1);
			}
		}
	}
	public static void print(int []array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+"  ");
		}
		System.out.println();
	}
}
