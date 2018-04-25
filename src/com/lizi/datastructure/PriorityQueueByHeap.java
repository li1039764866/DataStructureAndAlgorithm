package com.lizi.datastructure;

import com.lizi.tool.General;

public class PriorityQueueByHeap {
	MaxHeap maxHeap;
	public PriorityQueueByHeap(double []heap) {
		maxHeap=new MaxHeap(heap);
		maxHeap.buildMaxHeap();//优先队列创建之后就应该符合大顶堆的性质
	}
	public double getMaximum() {
		return maxHeap.getHeap()[0];
	}
	//弹出最大元素
	public double extrectMaximum() {
		//如果数组有效值小于容量的一半，则将数组缩小一半
		if (maxHeap.getSize()<maxHeap.getHeap().length/2) {
			maxHeap.resize(maxHeap.getHeap().length/2);
		}
		double max=maxHeap.getHeap()[0];
		maxHeap.getHeap()[0]=maxHeap.getHeap()[maxHeap.getSize()-1];
		maxHeap.setSize(maxHeap.getSize()-1);
		maxHeap.maxHeapify(0);
		return max;
	}
	//将对应下标元素变大，上浮操作
	public void  increaseKey(int index,double key) {
		if (key<maxHeap.getHeap()[index]) {
			return;
		}
		maxHeap.getHeap()[index]=key;
		//较大值上浮到合适位置
		while (index>0&&maxHeap.getHeap()[maxHeap.getParent(index)]<maxHeap.getHeap()[index]) {
			General.swap(maxHeap.getHeap(),maxHeap.getParent(index) , index);
			index=maxHeap.getParent(index);
		}
	}
	//插入元素
	public void insert(double key) {
		//数组再添加元素就会越界的时候，将原数组扩容至原来的两倍
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
