package com.lizi.Main;

import com.lizi.datastructure.symboltable.BinarySearchTreeST;

public class BinarySearchTreeMain {

	public static void main(String[] args) {
		BinarySearchTreeST<Integer, String> binarySearchTree=new BinarySearchTreeST<Integer, String>();
		binarySearchTree.put(5, "E");
		binarySearchTree.print();
		binarySearchTree.put(5, "D");
		binarySearchTree.put(1, "A");
		binarySearchTree.delete(4);
		binarySearchTree.print();
		System.out.println(binarySearchTree.size());
		System.out.println(binarySearchTree.select(2));

	}

}
