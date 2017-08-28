package com.lizi.algorithm.match;
//暴力匹配
public class BruteForceMatch {
	public static int search(String pat,String txt){
		int pSize=pat.length();
		int tSize=txt.length();
		for (int i = 0; i <=tSize-pSize; i++) {
			int j;
			for (j = 0; j < pSize; j++) {
				if(txt.charAt(i+j)!=pat.charAt(j)){
					break;
				}
				if(j==pSize) return i;//返回匹配下标
			}
		}
		return -1;
	}
}
