package com.lizi.Main;

import com.lizi.algorithm.sort.HeapSort;
import com.lizi.datastructure.MaxHeap;

public class HeapSortMain {

	public static void main(String[] args) {
		double []heap={2,2,5,3,7,9,4,8,0,1,6};
		MaxHeap maxHeap=new MaxHeap(heap);
		
		
        maxHeap.buildMaxHeap();
        HeapSort heapSort=new HeapSort();
        heapSort.sort(heap);
        for (int i = 0; i < heap.length; i++) {
			System.out.print(heap[i]+"     ");
		}
	}

}
