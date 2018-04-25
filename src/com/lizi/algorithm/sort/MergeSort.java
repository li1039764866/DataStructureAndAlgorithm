package com.lizi.algorithm.sort;

public class MergeSort {
	public static double[] aux;
	public static void merge(double[] array,int low,int middle,int high) {//������鲢
		int left=low;
		int right=middle+1;
		aux=new double[array.length];//�����Ǳ��ݹ���ã��·��������ó�����Խ�����һ�θ�����
		for (int k = low; k<=high; k++){
			aux[k]=array[k];
		}
		for (int i = low; i <=high; i++) {
			if (left>middle) array[i]=aux[right++];//��߲�������ɵݹ飬ֻ��Ҫ�ϲ��ұ߲���
			else if (right>high) array[i]=aux[left++];
			else if (aux[left]<aux[right]) array[i]=aux[left++];//ȡСֵ�ϲ�
			else array[i]=aux[right++];
		}
	}
	public static void sortTop(double []array,int low,int high) {//�Զ����µĹ鲢���򣬻���Ϊ0
		if (high<=low) return;
		int middle=low+(high-low)/2;
		sortTop(array, low, middle);//����������
		sortTop(array, middle+1, high);//���Ұ������
		merge(array, low, middle, high);
	}
	public static void sortButtom(double []array) {//�Ե����ϵĹ鲢����ѭ�򽥽�
		int length=array.length;
		for (int size = 1; size < length; size+=size) {//sizeΪ�������С
			for (int low = 0; low < length-size; low+=size+size) {
				//ȡ��Сֵ��ֹ����Խ��
				merge(array, low, low+size-1, Math.min(low+size+size-1, length-1));
				
			}
			
		}
	}
}
