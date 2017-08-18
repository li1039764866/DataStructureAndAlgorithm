package com.lizi.datastructure.symboltable;

import java.util.ArrayList;
import java.util.List;

//链表实现的无序符号表
public class SequentialSearchST<Key,Value> {
	private Node first=null;
	int size=0;
	private class Node{
		Key key;
		Value value;
		Node next;
		public Node(Key key,Value value,Node next) {
			this.key=key;
			this.value=value;
			this.next=next;
		}
	}
	//给出的键找到值
	public Value get(Key key) {
		for (Node x=first; x!=null; x=x.next) {
			if(key.equals(x.key))
				return x.value;
		}
		return null;
	}
	//查找给定的键，找到则更新其值，否则在头节点处新建节点
	public void put(Key key,Value value) {
		if(key==null||value==null) return;
		for (Node x=first; x!=null; x=x.next) {
			if(key.equals(x.key)){
				x.value=value;
				return;
			}	
		}
		first=new Node(key, value, first);
		size++;
	}
	//删除对应的键值对节点，返回其值
	public Value delete(Key key) {
		Node pre=first;
		Value value=null;
		for (Node now=first; now!=null;pre=now, now=now.next) {
			if(key.equals(now.key)){
				value=now.value;
				size--;
				if(key.equals(first.key)) 	first=now.next;
				else                        pre.next=now.next;
			}
				
		}
		return value;
	}
	//未排序的所有键加入到迭代器中
	public Iterable<Key> keys() {
		List<Key> keys=new ArrayList<Key>(size);
		for (Node x=first; x!=null; x=x.next) {
			keys.add(x.key);
		}
		return keys;
	}
	public boolean contains(Key key) {
		return get(key)!=null;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public int size() {
		return size;
	}
	public void print() {
		System.out.println();
		for (Node x=first; x!=null; x=x.next) {
			System.out.print(x.value+"   ");
		}
	}
	
}
