package com.lizi.algorithm.sort;
//ѡ�������㷨�����Ӷ�n^2
public class SelectionSort {
	public static void sort(int []array) {
		int length=array.length;
		for (int i = 0; i < length; i++) {
			int min=i;//��¼����������С�����±�
			for (int j = i+1; j < length; j++) {
				if (array[j]<array[min]) {//ѭ����¼��ֱ���ҳ���Сֵ
					min=j;
				}
			}
			int mid=array[min];//����������������С����������������ǰ��ȥ
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
