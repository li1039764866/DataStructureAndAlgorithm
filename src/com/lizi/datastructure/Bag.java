package com.lizi.datastructure;

import java.util.Iterator;


public class Bag<Item> implements Iterable<Item>{
	
	private Node first;
	private int size;
	private class Node{
		Item item;
		Node next;
	}
	public void add(Item item){
		Node oldfirst=first;
		first=new Node();
		first.item=item;
		first.next=oldfirst;
		size++;
	}
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size==0;
	}
	private class ListIterator implements Iterator<Item>{
		private Node current=first;

		public boolean hasNext() {
			return current!=null;
		}

		public Item next() {
			Item item=current.item;
			current=current.next;
			return item;
		}
		
	}
	
}
