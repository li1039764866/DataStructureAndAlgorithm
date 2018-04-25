package com.lizi.algorithm.sort;
//��������ʱ�临�Ӷ�Ϊn
public class CountingSort {
	//arrayΪ���������飬��countΪ0�����ֵ�ĸ���
	public static int[] sort(int[]array,int count) {
		int[] count_arr=new int[count];
		for (int i = 0; i < count_arr.length; i++) {//ͳ�������ʼ��
			count_arr[i]=0;
		}
		
		for (int i = 0; i < array.length; i++) {//ͳ��ÿ��ֵ���ֵĸ���
			count_arr[array[i]]=count_arr[array[i]]+1;			
		}
		for (int i = 1; i < count_arr.length; i++) {//ͨ������ó�����ú�һ����ǰ���ж��ٸ���
			count_arr[i]=count_arr[i]+count_arr[i-1];
		}
		int[] sort_array=new int[array.length];
		for (int i = array.length-1; i>=0; i--){
			sort_array[count_arr[array[i]]-1]=array[i];
			count_arr[array[i]]=count_arr[array[i]]-1;			
		}
		return sort_array;
	}
}
