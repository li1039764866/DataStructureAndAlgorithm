package com.lizi.algorithm.compress;

import com.lizi.datastructure.symboltable.ThreeST;
import com.lizi.tool.BinaryStdIn;
import com.lizi.tool.BinaryStdOut;
//LZW����
public class LZW {
	private static final int R=256;
	private static final int L=4096; //2^12����ʾ��ͬ���ַ����������ֵ
	private static final int W=12;   //ÿ���ַ���������
	public static void compress() {
		String input=BinaryStdIn.readString();
		ThreeST<Integer> st=new ThreeST<Integer>();//���򵥴ʲ�����
		
		for (int i = 0; i < R; i++) {//�����ַ���Ӧ�����������
			st.put(""+(char)i, i);
		}
		int code=R+1; //RΪ�ļ������ı���
		while(input.length()>0){
			String string=st.longestPrefixOf(input);//�ҵ����ƥ��ǰ׺������ַ��������Ѿ�����
			BinaryStdOut.write(st.get(string),W); //������ַ�12λ����
			
			int length=string.length();
			if(length<input.length()&&code<L)//�Ƿ��Ѿ��Ƕ�ȡ������Դ��β����ͳ�������Ѵ����
				st.put(input.substring(0, length+1), code++);//���µ��ַ��������µı��룬����String->int��ת��
			
			input=input.substring(length);//����length-1֮����ַ���,ע����һ��������ַ�����
			}//��׺�����һ���ַ�����Ȼ����������
		BinaryStdOut.write(R,W);//д���ļ��������
		BinaryStdOut.close();
	}
	
	public static void decompress() {
		String[] st=new String[L];//�����ַ�������ķ��ű�
		int i=0;
		for (int j = 0; j < R; j++) {
			st[j]=""+(char)j;//�����±�洢���ַ�
		}
		
		st[i++]=" ";//��ǰλ��Ϊ������־��û���ַ�
		int codeWord=BinaryStdIn.readInt(W);
		String val=st[codeWord];//��Ѱ�±��Ӧ�ַ���
		while (true) {
			BinaryStdOut.write(val);//�������õ����ַ���д��
			codeWord=BinaryStdIn.readInt(W);
			
			if(codeWord == R) break;//������־
			String s=st[codeWord];
			
			if(i==codeWord)//�����һ�ַ�������
				s=val+val.charAt(0);//������һ���ַ���������ĸ�õ�������ַ���
			
			if(i < L)
				st[i++] =val +s.charAt(0);//����int->String��ת��
			
			val=s;
			
		}
		BinaryStdOut.close();
	}
}
