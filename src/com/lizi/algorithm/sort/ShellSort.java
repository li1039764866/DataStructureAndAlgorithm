package com.lizi.algorithm.sort;

import com.lizi.tool.General;

//ϣ������һ�ֻ��ڲ�������Ŀ��ٵ������㷨����һ��ʼh�ϴ�һ�¾Ϳ��Խ�Ԫ���ƶ�����Զ�ĵط���
//Ϊʵ�ָ�С��h���򴴽�����
public class ShellSort {
	public static void sort(int []array) {
		int length=array.length;
		for (int step =length/2 ; step>0; step /= 2) {//�������μ���
			
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
