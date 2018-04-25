package com.lizi.algorithm.string;
//低位优先字符串排序（仅适用于等长字符串排序）-n次计数排序--线性时间
public class LSD {
	//通过后W个字符将a[]排序
	public static void sort(String [] a,int W){
		int N=a.length;
		int R=256;//字符基数，此处指ASCII码
		String[] aux=new String[N];
		
		for (int d = W-1; d >=0; d--) {//W次计数排序
			int [] count=new int[R+1];
			
			for (int i=0; i<N; i++) {//统计第d位各个字符出现的频数
				count[a[i].charAt(d)+1]++;
			}
			for (int r = 0; r < R; r++) {//计算各个字符串排序后的位置
				count[r+1]+=count[r];
			}
			
			for (int i = 0; i < N; i++) {//元素放到辅助数组的合适位置
				aux[count[a[i].charAt(d)]++]=a[i];
			}
			
			for (int i = 0; i < N; i++) {//回写
				a[i]=aux[i];
			}
		}
	}
}
