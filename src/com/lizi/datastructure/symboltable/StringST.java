package com.lizi.datastructure.symboltable;

import com.lizi.datastructure.Queue;

public class StringST<Value> {
	private static int R=256;//基数
	private Node root;//根节点
	
	public static class Node{
		private Object value;//值
		private Node[] next=new Node[R];//数组，对应下标存储值
	}
	
	@SuppressWarnings("unchecked")
	public Value get(String key){
		Node x=get(root,key,0);
		if(x==null) return null;
		return (Value)x.value;
	}

	private Node get(Node root, String key, int figure) {//返回字符串对应键
		if(root==null) return null;
		if(figure==key.length()) return root;
		char c=key.charAt(figure);//找到第d个字符所对应的子单词查找树
		return get(root.next[c], key, figure+1);
	}
	public void put(String key,Value value) {
		root=put(root, key,value,0);
	}

	private Node put(Node root, String key, Value value, int figure) {
		if(root==null) root=new Node();//节点为空，创建新节点
		if(figure==key.length()){//如果键的位数与计数相等，则找到了对应节点，更新其值
			root.value=value;
			return root;
		}
		char c=key.charAt(figure);//找寻键的下一个字符
		root.next[c]=put(root.next[c], key, value, figure+1);
		return root;
	}
	
	public Iterable<String> keys(){//返回所有键
		return keyWithPrefix("");
	}

	public Iterable<String> keyWithPrefix(String pre) {//返回含有对应前缀的键
		Queue<String> queue=new Queue<String>();
		collect(get(root, pre, 0),pre,queue);//以前缀中最后一个节点进行分开搜索
		return queue;
	}

	private void collect(Node node, String pre, Queue<String> queue) {
		if(node==null) return;
		if(node.value!=null) queue.enqueue(pre);//如果值不为空，将其入队列，作为返回值之一
		for (char c = 0; c < R; c++) {//递归出现了分叉
			collect(node.next[c], pre+c, queue);//遍历数组
		}
	}
	
	public Iterable<String> keysThatMatch(String pat){
		Queue<String> queue =new Queue<String>();
		collect(root, "",pat, queue);
		return queue;
	}

	private void collect(Node node, String pre, String pat,
			Queue<String> queue) {
		int size=pre.length();
		if(node==null) return;
		if(size==pat.length()&&node.value!=null) queue.enqueue(pre);
		if(size==pat.length()) return;
		
		char next=pat.charAt(size);
		for (char c = 0; c < R; c++) {
			if(next=='.'||next==c)//如果含有通配符，就需要递归调用处理所有的链接
				collect(node.next[c], pre+c, pat, queue);
		}
		
	}
	//键的最长前缀匹配--经过路径上“距离最近有值”的键
	public String longestPrefixOf(String s) {
		int length=search(root,s,0,0);
		return s.substring(0, length);//返回最长匹配下标，截取字符串
	}

	private int search(Node node, String s, int figure, int length) {
		if(node==null) return length;
		if(node.value!=null) length=figure;//更新长度为含有值的字符长度
		if(figure==s.length()) return length;
		char c=s.charAt(figure);
		return search(node.next[c], s, figure+1, length);
	}
	//将对应键置为NULL，检查数组是否全部为空，为空则置为NULL
	public void delete(String key) {
		root=delete(root,key,0);
	}

	private Node delete(Node node, String key, int figure) {
		if(node==null) return null;
		if(figure==key.length())  node.value=null;//发现键，将值置为NULL
		else {
			char c=key.charAt(figure);
			node.next[c]=delete(node.next[c], key, figure+1);//递归寻找
		}
		
		if(node.value!=null) return node;//如果当前值不为NULL，则无需删除操作
		for (char c = 0; c < R; c++) {
			if(node.next[c]!=null) return node;//遍历当前数组，如果存在不为空的值就返回节点
		}
		return null;//数组没有值则返回NULL
	}
	
}
