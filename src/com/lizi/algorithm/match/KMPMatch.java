package com.lizi.algorithm.match;
//KMP匹配
public class KMPMatch {
	private  int[] next;
	public  int search(String pat,String txt){
		this.next(pat);
		int i,j;
		int pSize=pat.length();
		int tSize=txt.length();
		for (i = 0,j=0; i < tSize&&j<pSize; ) {
			if(txt.charAt(i)==pat.charAt(j)){
				j++;
				i++;
			}
			else {
				i-=next[j];
			}
		}
		if(j==pSize) return i-pSize;
		else         return -1;
	}
	 void next(String pat)  
	 {  
	     int pSize = pat.length();  
	     next=new int[pSize];
	     next[0] = -1;  
	     int k = -1;  
	     int j = 0;  
	     while (j < pSize - 1)  
	     {  
	         //p[k]表示前缀，p[j]表示后缀  
	         if (k == -1 || pat.charAt(j) == pat.charAt(k))   
	         {  
	             ++k;  
	             ++j;  
	             next[j] = k;  
	         }  
	         else   
	         {  
	             k = next[k];  
	         }  
	     } 
	 } 
}
