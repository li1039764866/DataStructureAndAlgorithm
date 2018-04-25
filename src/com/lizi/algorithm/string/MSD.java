package com.lizi.algorithm.string;
//高位优先字符串排序（适用于变长字符串排序）-递归调用n次计数排序
//默认ab优先于abc
public class MSD {
	private static int R=256;
	private static final int M=15;//小数组切换阀值
	private static String[] aux;
	private static int charAt(String s,int d){
		if(d<s.length()) return s.charAt(d);
		else 			 return -1;
	}
	
	public static void sort(String[] a){
		int N=a.length;
		aux=new String[N];
		sort(a,0,N-1,0);
	}

	private static void sort(String[] a, int low, int high, int d) {
		/*if (high<=low+M) {//长度小于M的数组使用插入排序性能更高
			Insertion.Sort(a,low,high,d);
			return;
		}*/
		if(high<=low) return;//大小为0或1的数组忽略
		int [] count=new int[R+2];//统计数组
		
		for (int i = low; i < high; i++) {
			count[charAt(a[i], d)+2]++;
		}
		for (int r = 0; r < R+1; r++) {
			count[r+1]+=count[r];
		}
		for (int i = low; i <=high; i++) {
			aux[count[charAt(a[i], d)+1]++]=a[i];
		}
		
		for (int i = low; i <= high; i++) {
			a[i]=aux[i-low];
		}
		
		for (int r = 0; r < R; r++) {
			sort(a, low+count[r], high+count[r+1], d+1);
		}
	}
}
