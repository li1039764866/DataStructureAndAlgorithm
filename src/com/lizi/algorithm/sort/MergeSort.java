package com.lizi.algorithm.sort;

public class MergeSort {
	public static double[] aux;
	public static void merge(double[] array,int low,int middle,int high) {//子数组归并
		int left=low;
		int right=middle+1;
		aux=new double[array.length];//由于是被递归调用，下方两个调用程序可以仅创建一次该数组
		for (int k = low; k<=high; k++){
			aux[k]=array[k];
		}
		for (int i = low; i <=high; i++) {
			if (left>middle) array[i]=aux[right++];//左边部分已完成递归，只需要合并右边部分
			else if (right>high) array[i]=aux[left++];
			else if (aux[left]<aux[right]) array[i]=aux[left++];//取小值合并
			else array[i]=aux[right++];
		}
	}
	public static void sortTop(double []array,int low,int high) {//自顶向下的归并排序，画整为0
		if (high<=low) return;
		int middle=low+(high-low)/2;
		sortTop(array, low, middle);//将左半边排序
		sortTop(array, middle+1, high);//将右半边排序
		merge(array, low, middle, high);
	}
	public static void sortButtom(double []array) {//自底向上的归并排序，循序渐进
		int length=array.length;
		for (int size = 1; size < length; size+=size) {//size为子数组大小
			for (int low = 0; low < length-size; low+=size+size) {
				//取最小值防止数组越界
				merge(array, low, low+size-1, Math.min(low+size+size-1, length-1));
				
			}
			
		}
	}
}
