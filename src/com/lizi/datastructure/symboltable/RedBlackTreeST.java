package com.lizi.datastructure.symboltable;

import java.util.ArrayList;
import java.util.List;


//左偏红黑树并未完成delete函数
public class RedBlackTreeST<Key extends Comparable<Key>,Value> extends SymbolTable<Key, Value> {
	
	private static final boolean RED=true;
	private static final boolean BLACK=false;
	
	private Node root;
	private class Node{
		Key key;
		Value value;
		Node left;
		Node right;
		int size;
		boolean color;//由其父节点指向它的链接的颜色
		public Node(Key key,Value value,int size,boolean color){
			this.key=key;
			this.value=value;
			this.size=size;
		}
	}
	private boolean isRed(Node node) {
		if(node==null) return false;
		return node.color==RED;
	}
	
	@Override
	public void put(Key key, Value value) {
		root=put(root, key, value);
		root.color=BLACK;
		
	}
	private Node put(Node root, Key key, Value value) {
		if(root==null)
			return new Node(key, value, 1, RED);
		int cmp=key.compareTo(root.key);
		if(cmp<0) 		root.left=put(root.left, key, value);
		else if(cmp>0)  root.right=put(root.right, key, value);
		else root.key=key;
		//以上为二叉树插入操作，以下三句为平衡调整
		if(isRed(root.right)&&!isRed(root.left)) root=rotateLeft(root);
		if(isRed(root.left)&&isRed(root.left.left)) root=rotateRight(root);
		if(isRed(root.left)&&isRed(root.right)) flipColors(root);
		root.size=size(root.left)+size(root.right)+1;
		return root;
		
	}

	private void flipColors(Node root) {
		root.color=RED;
		root.left.color=BLACK;
		root.right.color=BLACK;	
	}

	private Node rotateLeft(Node node) {
		Node right=node.right;
		node.right=right.left;
		right.left=node;
		right.color=node.color;
		node.color=RED;
		right.size=node.size;
		node.size=size(node.left)+size(node.right)+1;
		return right;
	}
	private Node rotateRight(Node node) {
		Node left=node.left;
		node.left=left.right;
		left.right=node;
		left.color=node.color;
		node.color=RED;
		left.size=node.size;
		node.size=size(node.left)+size(node.right)+1;
		return left;
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
		return null;
	}
	@SuppressWarnings("unused")
	private Node balance(Node root) {
		if(isRed(root.right)) root=rotateLeft(root);
		if(isRed(root.right)&&!isRed(root.left)) root=rotateLeft(root);
		if(isRed(root.left)&&isRed(root.left.left)) root=rotateRight(root);
		if(isRed(root.left)&&isRed(root.right)) flipColors(root);
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

}
