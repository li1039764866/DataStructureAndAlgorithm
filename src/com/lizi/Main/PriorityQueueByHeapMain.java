package com.lizi.Main;

import com.lizi.datastructure.PriorityQueueByHeap;

public class PriorityQueueByHeapMain {
	public static void main(String[] args) {
		double []heap={2,2,5,3,7,9,4,8,0,1,6};
		PriorityQueueByHeap priorityQueueByHeap=new PriorityQueueByHeap(heap);
		priorityQueueByHeap.print();
		priorityQueueByHeap.extrectMaximum();
		priorityQueueByHeap.print();
		priorityQueueByHeap.insert(10);
		priorityQueueByHeap.insert(9);
		priorityQueueByHeap.print();
	}
}
