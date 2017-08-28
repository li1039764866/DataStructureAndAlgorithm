package com.lizi.Main;

import com.lizi.algorithm.match.KMPMatch;

public class KMPMatchMain {
	
	public static void main(String[] args) {
		KMPMatch kmpMatch=new KMPMatch();
		System.out.println(kmpMatch.search("5", "2345"));
	}
}
