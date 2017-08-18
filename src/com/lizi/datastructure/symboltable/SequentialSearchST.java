package com.lizi.datastructure.symboltable;

import java.util.ArrayList;
import java.util.List;

//����ʵ�ֵ�������ű�
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
	//�����ļ��ҵ�ֵ
	public Value get(Key key) {
		for (Node x=first; x!=null; x=x.next) {
			if(key.equals(x.key))
				return x.value;
		}
		return null;
	}
	//���Ҹ����ļ����ҵ��������ֵ��������ͷ�ڵ㴦�½��ڵ�
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
	//ɾ����Ӧ�ļ�ֵ�Խڵ㣬������ֵ
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
	//δ��������м����뵽��������
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
