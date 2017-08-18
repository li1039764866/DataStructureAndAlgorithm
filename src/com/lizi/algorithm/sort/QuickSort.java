package com.lizi.algorithm.sort;

import com.lizi.tool.General;

public class QuickSort {
	public  static void sort(int array[], int left, int right)//数组的下标开始和结束位置
	{
		if (left >= right) return;
		int middle=partition(array, left, right);
		sort(array, left, middle - 1); //对关键值左边的进行排序
		sort(array, middle + 1, right);  //对关键值右边的进行排序
	}
	//线性时间内找到第K小的元素
	public static int select(int[] array,int k) {
		int left=0;
		int right=array.length-1;
		while (right>left) {
			int middle=partition(array, left, right);
			if (middle==k) return array[k];
			else if(middle>k) right=middle-1;
			else if(middle<k) left=middle+1;
			
		}
		return array[k];
	}
	//切分数组
	public static int partition(int []array,int left,int right){
		int left_cursor = left; //记录左指针的位置
		int right_cursor = right; //记录右指针的位置
		int pivot_key = array[left]; //记录关键值
		while (left_cursor < right_cursor) //一轮排序，完成之后关键值左边的都比它小，右边的都比它大
		{
			while (left_cursor < right_cursor&&array[right_cursor] >= pivot_key)
			{
				right_cursor--;
			}
			array[left_cursor] = array[right_cursor];
			while (left_cursor < right_cursor&&array[left_cursor] <= pivot_key)
			{
				left_cursor++;
			}
			array[right_cursor] = array[left_cursor];
		}
		array[left_cursor] = pivot_key;//结束循环的时候左右指针指向同一下标
		return left_cursor;
	}
	//三向切分的快速排序，针对于多个重复值的排序可达线性
	public static void sort1(double []array,int left,int right) {
		if (right<=left) return;
		int left_cursor = left; //记录左指针的位置
		int right_cursor = right; //记录右指针的位置
		double pivot_key = array[left]; //记录关键值
		int now=left+1;
		while (now<=right_cursor) {
			if(array[now]<pivot_key) General.swap(array, left_cursor++, now++);
			else if(array[now]>pivot_key) General.swap(array, now,right_cursor-- );
			else now++;
		}
		sort1(array, left, left_cursor-1);
		sort1(array, right_cursor+1, right);
	}
	
}
