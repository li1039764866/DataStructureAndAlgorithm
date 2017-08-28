package com.lizi.algorithm.compress;

import com.lizi.datastructure.MinPQ;
import com.lizi.tool.BinaryStdIn;
import com.lizi.tool.BinaryStdOut;
//霍夫曼压缩与解压
public class Huffman {
	private static int R=256;//字符数目
	private static class Node implements Comparable<Node>{//节点类
		private char ch;
		private int freq;
		private final Node left,right;
		Node(char ch,int freq,Node left,Node right){
			this.ch=ch;
			this.freq=freq;
			this.left=left;
			this.right=right;
		}
		public boolean isLeaf() {//是否是叶子节点
			return left==null && right==null;
		}
		public int compareTo(Node that) {
			return this.freq-that.freq;
		}
	}
	//BinaryStdIn从控制台文件输入
	public static void compress(){
		String string=BinaryStdIn.readString();
		char[] input=string.toCharArray();
		int [] freq=new int[R];//字符频数统计
		for(int i=0;i<input.length;i++)
			freq[input[i]]++;
		
		Node root=buildTree(freq);//霍夫曼树建立
		String[] st=buildCode(root);
		
		writeTree(root);//将树节点按照前序遍历写入
		BinaryStdOut.write(input.length);//字符长度
		for (int i = 0; i < input.length; i++) {//按照字符对照编码表写入压缩后的编码
			String code=st[input[i]];
			for (int j = 0; j < code.length(); j++) {
				if(code.charAt(j)=='1')
					BinaryStdOut.write(true);
				else BinaryStdOut.write(false);
			}
		}
		BinaryStdOut.close();
		
	}
	//解压缩
	public static void decompress() {
		Node root=readTree();//按照前序重新构建霍夫曼树
		int N=BinaryStdIn.readInt();//读取字符数量
		for (int i = 0; i < N; i++) {//将编码转换为字符
			Node x=root;
			while(!x.isLeaf()){
				if(BinaryStdIn.readBoolean()) x=x.right;
				else                         x=x.left;		
			}
			BinaryStdOut.write(x.ch);
		}
		BinaryStdOut.close();
	}
	//按照霍夫曼树建立字符对应编码
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
	//利用频数构建霍夫曼树，基于最小优先队列
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
	//二叉树前序遍历打印节点
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
	//重建霍夫曼树，此时已经不需要字符频率
	private static Node readTree(){
		if(BinaryStdIn.readBoolean())
			return new Node(BinaryStdIn.readChar(), 0, null, null);
		return	new Node('\0', 0, readTree(), readTree());
	}
	
}
