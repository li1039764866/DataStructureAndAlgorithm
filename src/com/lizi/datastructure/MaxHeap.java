package com.lizi.datastructure;

import com.lizi.tool.General;

public class MaxHeap {
	private double[] heap;
	private int size=0;
	public MaxHeap (double[] heap) {
		this.heap=heap;
		this.setSize(heap.length);
	}
	public int  getParent(int children) {
		return children/2;
	}
	public int  getLeftChildren(int parent) {
		return parent*2+1;
	}
	public int  getRightChildren(int parent) {
		return parent*2+2;
	}
	//�³�����
	public void maxHeapify (int index) {//�ٶ���indexΪ���ڵ����µ��������Ǵ󶥶ѣ��ú��������ڵ㽻��������λ��
		int left=getLeftChildren(index);
		int right=getRightChildren(index);
		int largest=0;
		if (left<size&&heap[left]>heap[index]) {//�ҳ��ϴ�ĺ����븸�ڵ㽻��
			largest=left;
		}else {
			largest=index;
		}
		if (right<size&&heap[right]>heap[largest]) {
			largest=right;
		}
		if (largest!=index) {//�����������Ǹ��ڵ㣬�򽻻�����λ��
			General.swap(heap, index, largest);
			maxHeapify(largest);
	    }//�������轻������˵����indexΪ���ڵ�����Ѿ����ϴ󶥶ѵ�����
	}
	public void print() {
		System.out.print("�󶥶ѣ�");
		for (int i = 0; i < this.size; i++) {
			System.out.print(heap[i]+"  ");
		}System.out.println();
	}
	public void  buildMaxHeap() {//�����������ʼ��Ϊ�󶥶�
		for (int i = (size/2-1); i >=0; i--) {//�����һ������Ҷ�ڵ�Ľڵ㿪ʼ����Ƿ�Ϊ�󶥶�
			maxHeapify(i);
		}
	}
	public void resize(int length) {
		double []heap=new double[length];
		for (int i = 0; i < this.heap.length; i++) {
			heap[i]=this.heap[i];
		}
		this.setHeap(heap);
	}
	public boolean isEmpty() {
		return this.size==0;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double[] getHeap() {
		return heap;
	}
	public void setHeap(double[] heap) {
		this.heap = heap;
	}
}
