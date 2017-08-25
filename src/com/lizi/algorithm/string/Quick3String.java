package com.lizi.algorithm.string;

import com.lizi.tool.General;
//�����з��ַ�������
public class Quick3String {
	private static int charAt(String s,int figure) {//����figureλ���ϵ��ַ����˴���ʽת��Ϊint��unicode�룩
		if(figure<s.length()) return s.charAt(figure);
		else return -1;
	}
	public static void sort(String []a){
		sort3(a, 0, a.length-1, 0);
	}
	
	private static void sort3(String []array,int left,int right,int figure) {
		if (right<=left) return;
		int left_cursor = left; //��¼��ָ���λ��
		int right_cursor = right; //��¼��ָ���λ��
		int pivot_key = charAt(array[left], figure); //��¼�ؼ�ֵ
		int now=left+1;
		while (now<=right_cursor) {
			int compare=charAt(array[now], figure);
			if(compare<pivot_key) General.swap(array, left_cursor++, now++);
			else if(compare>pivot_key) General.swap(array, now,right_cursor-- );
			else now++;
		}
		//[left_cursor-1,right_cursor+1]Ϊ���ڹؼ�ֵ���֣���������
		sort3(array, left, left_cursor-1,figure);	//С�ڲ�������
		if(pivot_key>=0)   sort3(array, left, right_cursor,figure+1);//���ڲ�������
		sort3(array, right_cursor+1, right,figure);//���ڲ�������
	}
}
