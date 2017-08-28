package com.lizi.algorithm.compress;

import com.lizi.datastructure.MinPQ;
import com.lizi.tool.BinaryStdIn;
import com.lizi.tool.BinaryStdOut;
//������ѹ�����ѹ
public class Huffman {
	private static int R=256;//�ַ���Ŀ
	private static class Node implements Comparable<Node>{//�ڵ���
		private char ch;
		private int freq;
		private final Node left,right;
		Node(char ch,int freq,Node left,Node right){
			this.ch=ch;
			this.freq=freq;
			this.left=left;
			this.right=right;
		}
		public boolean isLeaf() {//�Ƿ���Ҷ�ӽڵ�
			return left==null && right==null;
		}
		public int compareTo(Node that) {
			return this.freq-that.freq;
		}
	}
	//BinaryStdIn�ӿ���̨�ļ�����
	public static void compress(){
		String string=BinaryStdIn.readString();
		char[] input=string.toCharArray();
		int [] freq=new int[R];//�ַ�Ƶ��ͳ��
		for(int i=0;i<input.length;i++)
			freq[input[i]]++;
		
		Node root=buildTree(freq);//������������
		String[] st=buildCode(root);
		
		writeTree(root);//�����ڵ㰴��ǰ�����д��
		BinaryStdOut.write(input.length);//�ַ�����
		for (int i = 0; i < input.length; i++) {//�����ַ����ձ����д��ѹ����ı���
			String code=st[input[i]];
			for (int j = 0; j < code.length(); j++) {
				if(code.charAt(j)=='1')
					BinaryStdOut.write(true);
				else BinaryStdOut.write(false);
			}
		}
		BinaryStdOut.close();
		
	}
	//��ѹ��
	public static void decompress() {
		Node root=readTree();//����ǰ�����¹�����������
		int N=BinaryStdIn.readInt();//��ȡ�ַ�����
		for (int i = 0; i < N; i++) {//������ת��Ϊ�ַ�
			Node x=root;
			while(!x.isLeaf()){
				if(BinaryStdIn.readBoolean()) x=x.right;
				else                         x=x.left;		
			}
			BinaryStdOut.write(x.ch);
		}
		BinaryStdOut.close();
	}
	//���ջ������������ַ���Ӧ����
	private static String[] buildCode(Node root){
		String[] st=new String[R];
		buildCode(st,root,"");
		return st;
	}
	private static void buildCode(String[] st, Node x, String s) {
		if(!x.isLeaf()){
			st[x.ch]=s;
			return;
		}
		buildCode(st, x.left, s+'0');
		buildCode(st, x.right, s+'1');
	}
	//����Ƶ����������������������С���ȶ���
	private static Node buildTree(int[] freq) {
		MinPQ<Node> pq=new MinPQ<Huffman.Node>();
		for (char c = 0; c < R; c++) {
			if(freq[c]>0)
				pq.insert(new Node(c, freq[c], null, null));
		}
		while(pq.size()>1){
			Node x=pq.delMin();
			Node y=pq.delMin();
			Node parent=new Node('\0', x.freq+y.freq, x, y);
			pq.insert(parent);
		}
		return pq.delMin();
	}
	//������ǰ�������ӡ�ڵ�
	private static void writeTree(Node x) {
		if(x.isLeaf()){
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch);
			return;
		}
		BinaryStdOut.write(false);
		writeTree(x.left);
		writeTree(x.right);
	}
	//�ؽ�������������ʱ�Ѿ�����Ҫ�ַ�Ƶ��
	private static Node readTree(){
		if(BinaryStdIn.readBoolean())
			return new Node(BinaryStdIn.readChar(), 0, null, null);
		return	new Node('\0', 0, readTree(), readTree());
	}
	
}
