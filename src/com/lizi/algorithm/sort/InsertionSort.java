package com.lizi.algorithm.sort;

import com.lizi.tool.General;

//���������㷨�����Ӷ�n^2����������Բ�������������ر���Ч
public class InsertionSort {
	
	public static void sort(int []array) {
		int length=array.length;
		for (int i = 1; i < length; i++) {
			for (int j = i; j>0&&array[j]<array[j-1]; j--) {//������ǰ��ϴ��ֵ���н���
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
