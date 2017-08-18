package com.lizi.algorithm.sort;

import com.lizi.datastructure.MaxHeap;
import com.lizi.tool.General;

public class HeapSort {
	
	public double[] sort(double []heap) {//���ô󶥶ѵ����ʣ��������ȡ���ٻָ�Ϊ�󶥶�
		MaxHeap maxHeap=new MaxHeap(heap);
		maxHeap.buildMaxHeap();//�����󶥶ѣ�ʵ�������ǿ���һֱʹ�����������ȥ�������Ķѻָ�Ϊ�󶥶ѣ���������
		//ȡ��֮������������������Ȼ����󶥶ѵĹ��ɣ���������������ɿ��Լ��ٸ��Ӷ�
		for (int i = maxHeap.getSize()-1; i >0 ; i--) {//���ν����������������ĩβ�����ѳ��ȼ�һ
			General.swap(maxHeap.getHeap(), 0, i);
			maxHeap.setSize(maxHeap.getSize()-1);//���һ�����������������ڶѵ���Чֵ
			maxHeap.maxHeapify(0);//�öѸ�Ԫ�ز����ϴ󶥶ѵ�����
		}
		return maxHeap.getHeap();
	}
}
