package com.lizi.algorithm.string;
//��λ�����ַ������򣨽������ڵȳ��ַ�������-n�μ�������--����ʱ��
public class LSD {
	//ͨ����W���ַ���a[]����
	public static void sort(String [] a,int W){
		int N=a.length;
		int R=256;//�ַ��������˴�ָASCII��
		String[] aux=new String[N];
		
		for (int d = W-1; d >=0; d--) {//W�μ�������
			int [] count=new int[R+1];
			
			for (int i=0; i<N; i++) {//ͳ�Ƶ�dλ�����ַ����ֵ�Ƶ��
				count[a[i].charAt(d)+1]++;
			}
			for (int r = 0; r < R; r++) {//��������ַ���������λ��
				count[r+1]+=count[r];
			}
			
			for (int i = 0; i < N; i++) {//Ԫ�طŵ���������ĺ���λ��
				aux[count[a[i].charAt(d)]++]=a[i];
			}
			
			for (int i = 0; i < N; i++) {//��д
				a[i]=aux[i];
			}
		}
	}
}
