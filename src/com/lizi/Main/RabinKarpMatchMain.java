package com.lizi.Main;

import com.lizi.algorithm.match.RabinKarpMatch;

public class RabinKarpMatchMain {
	public static void main(String[] args) {
		RabinKarpMatch rabinKarpMatch=new RabinKarpMatch("56");
		System.out.println(rabinKarpMatch.search("123456"));;
	}
	
	
}
