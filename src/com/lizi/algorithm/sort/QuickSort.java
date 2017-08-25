package com.lizi.algorithm.sort;

import com.lizi.tool.General;

public class QuickSort {
	public  static void sort(int array[], int left, int right)//������±꿪ʼ�ͽ���λ��
	{
		if (left >= right) return;
		int middle=partition(array, left, right);
		sort(array, left, middle - 1); //�Թؼ�ֵ��ߵĽ�������
		sort(array, middle + 1, right);  //�Թؼ�ֵ�ұߵĽ�������
	}
	//����ʱ�����ҵ���KС��Ԫ��
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
	//�з�����
	public static int partition(int []array,int left,int right){
		int left_cursor = left; //��¼��ָ���λ��
		int right_cursor = right; //��¼��ָ���λ��
		int pivot_key = array[left]; //��¼�ؼ�ֵ
		while (left_cursor < right_cursor) //һ���������֮��ؼ�ֵ��ߵĶ�����С���ұߵĶ�������
		{//�ؼ�ֵ������������������ֵ�����������λ����Ȼ��������
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
		array[left_cursor] = pivot_key;//����ѭ����ʱ������ָ��ָ��ͬһ�±�
		return left_cursor;
	}
	//�����з֣�С�ڣ����ڣ����ڣ��Ŀ�����������ڴ����ظ���������ɴ�����
	public static void sort3(double []array,int left,int right) {
		if (right<=left) return;
		int left_cursor = left; //��¼��ָ���λ��
		int right_cursor = right; //��¼��ָ���λ��
		double pivot_key = array[left]; //��¼�ؼ�ֵ
		int now=left+1;
		while (now<=right_cursor) {
			if(array[now]<pivot_key) General.swap(array, left_cursor++, now++);
			else if(array[now]>pivot_key) General.swap(array, now,right_cursor-- );
			else now++;
		}
		//[left_cursor-1,right_cursor+1]Ϊ���ڹؼ�ֵ���֣���������
		sort3(array, left, left_cursor-1);	//С�ڲ�������
		sort3(array, right_cursor+1, right);//���ڲ�������
	}
	
}
