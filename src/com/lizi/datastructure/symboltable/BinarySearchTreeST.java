package com.lizi.datastructure.symboltable;

import java.util.ArrayList;
import java.util.List;


public class BinarySearchTreeST<Key extends Comparable<Key>,Value> extends SymbolTable<Key,Value> {
	private Node root;//根节点
	private class Node{
		private Key key;  //键
		private Value value;	//值
		private Node left;		//左孩子
		private Node right;		//右孩子
		private int size;		//以该节点为根的子树中节点总数
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
		else{//删除操作
			if(root.right==null) return root.left;//左右孩子不全，直接替补上去
			if(root.left==null) return root.right;
			Node node=root;//包含左右孩子的删除操作
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
	//找到第一个小于等于key的节点，再在它的右子树的左子树中找寻是否有小于key的节点
	//如果有，那么这个键就是最接近key的节点，一个闪电形状
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
	//返回以root为根节点的子树中小于key的键的数量
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
	//返回下标为index的键
	private Node select(Node root,int index) {
		if(root==null) return null;
		int size=size(root.left);
		if(size>index)      return select(root.left, index);
		else if(size<index) return select(root.right, index);
		else 				return root;
	}
	//将下标位于low-high之间的所有键放入链表中
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
	//删除最小的键
	public void deleteMin(){
		root=deleteMin(root);
	}
	private Node deleteMin(Node root){
		if(root.left==null) return root.right;
		root.left=deleteMin(root.left);//如果最小节点有右孩子，那么替代自身成为其父节点的左孩子
		root.size=size(root.left)+size(root.right)+1;//左右孩子加上自身
		return root;
	}
	
	//删除最大的键
	public void deleteMax(){
		deleteMax(root);
	}
	private Node deleteMax(Node root){
		if(root.right==null) return root.left;
		root.right=deleteMin(root.right);//如果最小节点有左孩子，那么替代自身成为其父节点的右孩子
		root.size=size(root.left)+size(root.right)+1;//左右孩子加上自身
		return root;
	}
	public void print() {
		print(root);
	}
	private void print(Node root) {
		if(root==null) return;
		print(root.left);
		System.out.println(" 键："+root.key+" 值："+root.value);
		print(root.right);
	}
}
