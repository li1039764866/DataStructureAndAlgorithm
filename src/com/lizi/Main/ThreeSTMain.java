package com.lizi.Main;

import com.lizi.datastructure.symboltable.ThreeST;

public class ThreeSTMain {
	public static void main(String []args) {
		ThreeST<Integer> threeST =new ThreeST<Integer>();
		threeST.put("sh",12);
		threeST.put("she",13);
		threeST.put("ashe",13);
		System.out.println(threeST.get("sh"));
		
		System.out.println(threeST.keysWithPrefix("sh"));
		for (String key : threeST.keysWithPrefix("s")) {
			System.out.println(key);
		}
		//System.out.println(threeST.keysThatMatch("sh"));
		for (String key : threeST.keysThatMatch("..")) {
			System.out.println("Æ¥Åä½á¹û£º"+key);
		}
		
	}
	
}
