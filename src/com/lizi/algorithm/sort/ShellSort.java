package com.lizi.algorithm.sort;

import com.lizi.tool.General;

//希尔排序（一种基于插入排序的快速的排序算法）：一开始h较大，一下就可以将元素移动到很远的地方，
//为实现更小的h有序创建方便
public class ShellSort {
	public static void sort(int []array) {
		int length=array.length;
		for (int step =length/2 ; step>0; step /= 2) {//步长依次减半
			
			for (int i = step; i <length; i++) {
				
				for (int j = i; j >=step&&array[j]<array[j-step]; j-=step) {	
						General.swap(array, j, j-step);
				}
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
