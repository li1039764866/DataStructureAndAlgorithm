package com.lizi.datastructure.symboltable;

import java.util.ArrayList;
import java.util.List;


public class BinarySearchTreeST<Key extends Comparable<Key>,Value> extends SymbolTable<Key,Value> {
	private Node root;//���ڵ�
	private class Node{
		private Key key;  //��
		private Value value;	//ֵ
		private Node left;		//����
		private Node right;		//�Һ���
		private int size;		//�Ըýڵ�Ϊ���������нڵ�����
		public Node(Key key,Value value,int size){
			this.key=key;
			this.value=value;
			this.size=size;
		}
	}
	@Override
	public void put(Key key, Value value) {
		if(value==null) {delete(key); return;}
		root=put(root, key, value);
		
	}
	private Node put(Node root,Key key,Value value) {
		if(root==null) return new Node(key, value, 1);
		int cmp=key.compareTo(root.key);
		if(cmp<0) 		root.left=put(root.left, key, value);
		else if(cmp>0)  root.right=put(root.right, key, value);
		else    		root.value=value;
		root.size=size(root.left)+size(root.right)+1;
		return root;
		
	}
	@Override
	public Value get(Key key) {
		return get(root, key);
	}
	private Value get(Node root,Key key) {
		if(root==null)  return null;
		int cmp=key.compareTo(root.key);
		if(cmp<0) 		return get(root.left, key);
		else if(cmp>0)  return get(root.right, key);
		else 			return root.value;
	}
	@Override
	public Value delete(Key key) {
		Value value=get(key);
		root=delete(root, key);
		return value;
		
	}
	private Node delete(Node root,Key key) {
		if(root==null) return null;
		int cmp=key.compareTo(root.key);
		if  (cmp<0) root.left=delete(root.left, key);
		else if(cmp>0) root.right=delete(root.right, key);
		else{//ɾ������
			if(root.right==null) return root.left;//���Һ��Ӳ�ȫ��ֱ���油��ȥ
			if(root.left==null) return root.right;
			Node node=root;//�������Һ��ӵ�ɾ������
			root=min(node.right);
			root.right=deleteMin(node.right);
			root.left=node.left;
		}
		root.size=size(root.left)+size(root.right)+1;
		return root;
	}
	
	@Override
	public int size() {
		return size(root);
	}
	private int size(Node root) {
		if (root==null) return 0;
		else return root.size;
	}
	
	@Override
	public Key min() {
		return min(root).key;
	}
	private Node min(Node root) {
		if(root.left==null) return root;
		else return root.left;
	}
	
	@Override
	public Key max() {
		return max(root).key;
	}
	private Node max(Node root) {
		if(root.right==null) return root;
		else return root.right;
	}
	
	@Override
	public Key floor(Key key) {
		Node node=floor(root, key);
		if(node==null) return null;
		return node.key;
	}
	//�ҵ���һ��С�ڵ���key�Ľڵ㣬��������������������������Ѱ�Ƿ���С��key�Ľڵ�
	//����У���ô�����������ӽ�key�Ľڵ㣬һ��������״
	private Node floor(Node root,Key key) {
		if(root==null) return null;
		int cmp=key.compareTo(root.key);
		if(cmp==0) 		return root;
		if(cmp<0) 		return floor(root.left, key);
		Node node=floor(root.right, key);
		if (node!=null) return node;
		else 			return root;
	}
	@Override
	public Key ceiling(Key key) {
		Node node=ceiling(root, key);
		if(node==null) return null;
		return node.key;
	}
	private Node ceiling(Node root,Key key) {
		if(root==null) return null;
		int cmp=key.compareTo(root.key);
		if(cmp==0) 			return root;
		if(cmp>0) 			return floor(root.right, key);
		Node node=floor(root.left, key);
		if (node!=null) 	return node;
		else 				return root;
	}
	@Override
	public int rank(Key key) {
		return rank(root, key);
	}
	//������rootΪ���ڵ��������С��key�ļ�������
	private int rank(Node root,Key key) {
		if(root==null) return 0;
		int cmp=key.compareTo(root.key);
		if(cmp<0) 		return rank(root.left, key);
		else if(cmp>0)  return rank(root.right, key)+size(root.left)+1;
		else 			return size(root.left);
	}
	@Override
	public Key select(int index) {
		if(index>=size()) return null;
		return select(root,index).key;
	}
	//�����±�Ϊindex�ļ�
	private Node select(Node root,int index) {
		if(root==null) return null;
		int size=size(root.left);
		if(size>index)      return select(root.left, index);
		else if(size<index) return select(root.right, index);
		else 				return root;
	}
	//���±�λ��low-high֮������м�����������
	@Override
	public Iterable<Key> keys(Key low, Key high) {
		List<Key> list=new ArrayList<Key>();
		keys(root,list,low,high);
		return list;
	}
	private void keys(Node root,List<Key> list,Key low, Key high) {
		if(root==null) return;
		int cmplow=low.compareTo(root.key);
		int cmphigh=high.compareTo(root.key);
		if(cmplow<0) keys(root.left, list, low, high);
		if(cmphigh<=0&&cmphigh>=0) list.add(root.key);
		if(cmphigh>0) keys(root.right, list, low, high);
	}
	//ɾ����С�ļ�
	public void deleteMin(){
		root=deleteMin(root);
	}
	private Node deleteMin(Node root){
		if(root.left==null) return root.right;
		root.left=deleteMin(root.left);//�����С�ڵ����Һ��ӣ���ô��������Ϊ�丸�ڵ������
		root.size=size(root.left)+size(root.right)+1;//���Һ��Ӽ�������
		return root;
	}
	
	//ɾ�����ļ�
	public void deleteMax(){
		deleteMax(root);
	}
	private Node deleteMax(Node root){
		if(root.right==null) return root.left;
		root.right=deleteMin(root.right);//�����С�ڵ������ӣ���ô��������Ϊ�丸�ڵ���Һ���
		root.size=size(root.left)+size(root.right)+1;//���Һ��Ӽ�������
		return root;
	}
	public void print() {
		print(root);
	}
	private void print(Node root) {
		if(root==null) return;
		print(root.left);
		System.out.println(" ����"+root.key+" ֵ��"+root.value);
		print(root.right);
	}
}
