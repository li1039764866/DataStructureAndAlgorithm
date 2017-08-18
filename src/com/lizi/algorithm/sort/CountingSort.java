package com.lizi.algorithm.sort;
//计数排序，时间复杂度为n
public class CountingSort {
	//array为待排序数组，而count为0—最大值的个数
	public static int[] sort(int[]array,int count) {
		int[] count_arr=new int[count];
		for (int i = 0; i < count_arr.length; i++) {//统计数组初始化
			count_arr[i]=0;
		}
		
		for (int i = 0; i < array.length; i++) {//统计每个值出现的个数
			count_arr[array[i]]=count_arr[array[i]]+1;			
		}
		for (int i = 1; i < count_arr.length; i++) {//通过计算得出排序好后一个数前面有多少个数
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
