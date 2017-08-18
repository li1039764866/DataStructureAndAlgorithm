package com.lizi.Main;

import com.lizi.datastructure.symboltable.BinarySearchST;

public class BinarySearchSTMain {

	public static void main(String[] args) {
		BinarySearchST<Integer, String> binarySearchST=new BinarySearchST<Integer, String>(3);
		binarySearchST.put(1, "A");
		binarySearchST.put(2, "B");
		binarySearchST.put(3, "C");
		binarySearchST.put(4, "D");
		binarySearchST.put(5, "E");
		binarySearchST.delete(1);
		binarySearchST.delete(2);
		binarySearchST.delete(3);
		binarySearchST.delete(3);
		binarySearchST.put(5, "C");
		binarySearchST.delete(4);
		for (Integer i :binarySearchST.keys()) {
			System.out.print(i+"  :::");
		}
	}

}
