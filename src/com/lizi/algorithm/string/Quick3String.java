package com.lizi.algorithm.string;

import com.lizi.tool.General;
//三向切分字符串排序
public class Quick3String {
	private static int charAt(String s,int figure) {//返回figure位置上的字符，此处隐式转换为int（unicode码）
		if(figure<s.length()) return s.charAt(figure);
		else return -1;
	}
	public static void sort(String []a){
		sort3(a, 0, a.length-1, 0);
	}
	
	private static void sort3(String []array,int left,int right,int figure) {
		if (right<=left) return;
		int left_cursor = left; //记录左指针的位置
		int right_cursor = right; //记录右指针的位置
		int pivot_key = charAt(array[left], figure); //记录关键值
		int now=left+1;
		while (now<=right_cursor) {
			int compare=charAt(array[now], figure);
			if(compare<pivot_key) General.swap(array, left_cursor++, now++);
			else if(compare>pivot_key) General.swap(array, now,right_cursor-- );
			else now++;
		}
		//[left_cursor-1,right_cursor+1]为等于关键值部分，不需排序
		sort3(array, left, left_cursor-1,figure);	//小于部分排序
		if(pivot_key>=0)   sort3(array, left, right_cursor,figure+1);//等于部分排序
		sort3(array, right_cursor+1, right,figure);//大于部分排序
	}
}
