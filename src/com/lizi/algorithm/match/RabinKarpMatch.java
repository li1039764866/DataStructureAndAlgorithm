package com.lizi.algorithm.match;

import java.math.BigInteger;
import java.util.Random;


public class RabinKarpMatch {
	private String pat;
	private long patHash;
	private int pSize;
	private long Q;
	private int R=256;
	private long RM; //R^(M-1)%Q
	public RabinKarpMatch(String pat){
		this.pat=pat;
		this.pSize=pat.length();
		Q=longRandomPrime();
		RM=1;
		for (int i = 1; i <=pSize-1; i++) {
			RM=(R * RM) % Q;//���ڼ�ȥ��һ������ʱ�ļ���
		}
		patHash=hash(pat,pSize);
		
	}
	public int search(String txt){
		int tSize=txt.length();
		long txtHash=hash(txt, pSize);
		if(patHash==txtHash&&check(txt,0)) return 0;
		for (int i = pSize; i <tSize ; i++) {
			//��ȥ��һ�����֣��������һ�����֣��ٴμ��ƥ��
			txtHash = (txtHash + Q - RM*txt.charAt(i-pSize) % Q) % Q;
			txtHash = (txtHash *R +txt.charAt(i)) % Q;
			if(patHash == txtHash){
				if(check(txt,i-pSize+1)) return i-pSize+1;
			}
		}
		return -1;
	}
	private long hash(String key, int pSize) {//ģʽ��hashֵ����
		long h=0;
		for (int j = 0; j < pSize; j++) {
			h=(R * h+key.charAt(j)) % Q;
		}
		return h;
	}
	private boolean check(String txt, int i) {//hashֵ��ͬ���ַ������
        for (int j = 0; j < pSize; j++) 
            if (pat.charAt(j) != txt.charAt(i + j)) 
                return false; 
        return true;
    }
	protected static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
	}
	
}
