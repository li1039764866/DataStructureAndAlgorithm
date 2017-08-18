package com.lizi.algorithm.sort;

import com.lizi.datastructure.MaxHeap;
import com.lizi.tool.General;

public class HeapSort {
	
	public double[] sort(double []heap) {//利用大顶堆的性质，将最大数取出再恢复为大顶堆
		MaxHeap maxHeap=new MaxHeap(heap);
		maxHeap.buildMaxHeap();//构建大顶堆，实际上我们可以一直使用这个函数将去最大数后的堆恢复为大顶堆，但是由于
		//取数之后余下两个子数组依然满足大顶堆的规律，我们利用这个规律可以减少复杂度
		for (int i = maxHeap.getSize()-1; i >0 ; i--) {//依次将最大数交换至数组末尾，将堆长度减一
			General.swap(maxHeap.getHeap(), 0, i);
			maxHeap.setSize(maxHeap.getSize()-1);//最后一数存放最大数，不属于堆的有效值
			maxHeap.maxHeapify(0);//该堆根元素不符合大顶堆的性质
		}
		return maxHeap.getHeap();
	}
}
