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
	//下沉操作
	public void maxHeapify (int index) {//假定以index为根节点以下的子树都是大顶堆，该函数将根节点交换到合适位置
		int left=getLeftChildren(index);
		int right=getRightChildren(index);
		int largest=0;
		if (left<size&&heap[left]>heap[index]) {//找出较大的孩子与父节点交换
			largest=left;
		}else {
			largest=index;
		}
		if (right<size&&heap[right]>heap[largest]) {
			largest=right;
		}
		if (largest!=index) {//如果最大数不是根节点，则交换两数位置
			General.swap(heap, index, largest);
			maxHeapify(largest);
	    }//若果不需交换，则说明以index为根节点的树已经符合大顶堆的性质
	}
	public void print() {
		System.out.print("大顶堆：");
		for (int i = 0; i < this.size; i++) {
			System.out.print(heap[i]+"  ");
		}System.out.println();
	}
	public void  buildMaxHeap() {//将无序数组初始化为大顶堆
		for (int i = (size/2-1); i >=0; i--) {//从最后一个不是叶节点的节点开始检查是否为大顶堆
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
