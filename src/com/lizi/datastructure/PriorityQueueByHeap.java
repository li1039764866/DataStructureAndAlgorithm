package com.lizi.datastructure;

import com.lizi.tool.General;

public class PriorityQueueByHeap {
	MaxHeap maxHeap;
	public PriorityQueueByHeap(double []heap) {
		maxHeap=new MaxHeap(heap);
		maxHeap.buildMaxHeap();//���ȶ��д���֮���Ӧ�÷��ϴ󶥶ѵ�����
	}
	public double getMaximum() {
		return maxHeap.getHeap()[0];
	}
	//�������Ԫ��
	public double extrectMaximum() {
		//���������ЧֵС��������һ�룬��������Сһ��
		if (maxHeap.getSize()<maxHeap.getHeap().length/2) {
			maxHeap.resize(maxHeap.getHeap().length/2);
		}
		double max=maxHeap.getHeap()[0];
		maxHeap.getHeap()[0]=maxHeap.getHeap()[maxHeap.getSize()-1];
		maxHeap.setSize(maxHeap.getSize()-1);
		maxHeap.maxHeapify(0);
		return max;
	}
	//����Ӧ�±�Ԫ�ر���ϸ�����
	public void  increaseKey(int index,double key) {
		if (key<maxHeap.getHeap()[index]) {
			return;
		}
		maxHeap.getHeap()[index]=key;
		//�ϴ�ֵ�ϸ�������λ��
		while (index>0&&maxHeap.getHeap()[maxHeap.getParent(index)]<maxHeap.getHeap()[index]) {
			General.swap(maxHeap.getHeap(),maxHeap.getParent(index) , index);
			index=maxHeap.getParent(index);
		}
	}
	//����Ԫ��
	public void insert(double key) {
		//���������Ԫ�ؾͻ�Խ���ʱ�򣬽�ԭ����������ԭ��������
		if(maxHeap.getHeap().length<=maxHeap.getSize()){
			maxHeap.resize(2*maxHeap.getHeap().length);
		}
		maxHeap.setSize(maxHeap.getSize()+1);
		maxHeap.getHeap()[maxHeap.getSize()-1]=Double.MIN_VALUE;
		increaseKey(maxHeap.getSize()-1, key);
	}
	public void print() {
		maxHeap.print();
	}
}
