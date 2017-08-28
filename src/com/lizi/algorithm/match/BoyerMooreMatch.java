package com.lizi.algorithm.match;

public class BoyerMooreMatch {
	private int[] makeSkip;
	private int[] makeShift;
	private String pat;
	public BoyerMooreMatch(String pat) {
		this.pat=pat;
		makeSkip();
		makeShift();
	}
	public int search(String txt) {
		int tSize=txt.length();
		int pSize=pat.length();
		int skip;
		for (int i = 0; i <= tSize-pSize; i+=skip) {
			skip=0;
			for (int j = pSize-1; j >=0; j--) {
				if(pat.charAt(j)!=txt.charAt(i+j)){
					skip=j-makeSkip[txt.charAt(i+j)];
					if(skip<1) skip=1;
					break;
				}
			}
			if(skip==0) return i;
		}
		return -1;
	}
	//坏字符表
	private void makeSkip(){
		int pSize=pat.length();
		int R=256;
		makeSkip=new int[R];
		for (int i = 0; i < R; i++) {
			makeSkip[i]=-1;
		}
		for (int j = 0; j < pSize; j++) {
			makeSkip[pat.charAt(j)]=j;
		}
	}
	//好后缀表
	private void makeShift(){
		
	}
}
