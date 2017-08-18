package com.lizi.Main;

import com.lizi.datastructure.symboltable.SequentialSearchST;

public class SequentialSearchMain {

	public static void main(String[] args) {
		SequentialSearchST<Integer, String> sequentialSearchST=new SequentialSearchST<Integer, String>();
		sequentialSearchST.put(2, "ctr");
		sequentialSearchST.print();
		sequentialSearchST.put(2, "s123tr");
		sequentialSearchST.print();
		sequentialSearchST.put(3, "str");
		sequentialSearchST.delete(2);
		sequentialSearchST.print();
		

	}

}
