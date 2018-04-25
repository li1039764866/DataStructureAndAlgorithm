package com.lizi.datastructure.symboltable;

import com.lizi.datastructure.Queue;

public class StringST<Value> {
	private static int R=256;//����
	private Node root;//���ڵ�
	
	public static class Node{
		private Object value;//ֵ
		private Node[] next=new Node[R];//���飬��Ӧ�±�洢ֵ
	}
	
	@SuppressWarnings("unchecked")
	public Value get(String key){
		Node x=get(root,key,0);
		if(x==null) return null;
		return (Value)x.value;
	}

	private Node get(Node root, String key, int figure) {//�����ַ�����Ӧ��
		if(root==null) return null;
		if(figure==key.length()) return root;
		char c=key.charAt(figure);//�ҵ���d���ַ�����Ӧ���ӵ��ʲ�����
		return get(root.next[c], key, figure+1);
	}
	public void put(String key,Value value) {
		root=put(root, key,value,0);
	}

	private Node put(Node root, String key, Value value, int figure) {
		if(root==null) root=new Node();//�ڵ�Ϊ�գ������½ڵ�
		if(figure==key.length()){//�������λ���������ȣ����ҵ��˶�Ӧ�ڵ㣬������ֵ
			root.value=value;
			return root;
		}
		char c=key.charAt(figure);//��Ѱ������һ���ַ�
		root.next[c]=put(root.next[c], key, value, figure+1);
		return root;
	}
	
	public Iterable<String> keys(){//�������м�
		return keyWithPrefix("");
	}

	public Iterable<String> keyWithPrefix(String pre) {//���غ��ж�Ӧǰ׺�ļ�
		Queue<String> queue=new Queue<String>();
		collect(get(root, pre, 0),pre,queue);//��ǰ׺�����һ���ڵ���зֿ�����
		return queue;
	}

	private void collect(Node node, String pre, Queue<String> queue) {
		if(node==null) return;
		if(node.value!=null) queue.enqueue(pre);//���ֵ��Ϊ�գ���������У���Ϊ����ֵ֮һ
		for (char c = 0; c < R; c++) {//�ݹ�����˷ֲ�
			collect(node.next[c], pre+c, queue);//��������
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
			if(next=='.'||next==c)//�������ͨ���������Ҫ�ݹ���ô������е�����
				collect(node.next[c], pre+c, pat, queue);
		}
		
	}
	//�����ǰ׺ƥ��--����·���ϡ����������ֵ���ļ�
	public String longestPrefixOf(String s) {
		int length=search(root,s,0,0);
		return s.substring(0, length);//�����ƥ���±꣬��ȡ�ַ���
	}

	private int search(Node node, String s, int figure, int length) {
		if(node==null) return length;
		if(node.value!=null) length=figure;//���³���Ϊ����ֵ���ַ�����
		if(figure==s.length()) return length;
		char c=s.charAt(figure);
		return search(node.next[c], s, figure+1, length);
	}
	//����Ӧ����ΪNULL����������Ƿ�ȫ��Ϊ�գ�Ϊ������ΪNULL
	public void delete(String key) {
		root=delete(root,key,0);
	}

	private Node delete(Node node, String key, int figure) {
		if(node==null) return null;
		if(figure==key.length())  node.value=null;//���ּ�����ֵ��ΪNULL
		else {
			char c=key.charAt(figure);
			node.next[c]=delete(node.next[c], key, figure+1);//�ݹ�Ѱ��
		}
		
		if(node.value!=null) return node;//�����ǰֵ��ΪNULL��������ɾ������
		for (char c = 0; c < R; c++) {
			if(node.next[c]!=null) return node;//������ǰ���飬������ڲ�Ϊ�յ�ֵ�ͷ��ؽڵ�
		}
		return null;//����û��ֵ�򷵻�NULL
	}
	
}
