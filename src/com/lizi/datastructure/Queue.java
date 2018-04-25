package com.lizi.datastructure;

import java.util.Iterator;

public class Queue<T>implements Iterable<T> {
	private Node head;  //指向队列头结点
	private Node tail;  //指向队列尾结点
	private int count;  //链表结点数目
	
	private class Node{ //结点，嵌套类
		T element;
		Node next;
	}
	 public Queue() {
		head=null;
		tail=null;
		count=0;
	}
	public boolean isEmpty() {
		return count==0;
	}
	public int size() {
		return count;
	}
	public void enqueue(T element) {
		Node node=new Node();
		node.element=element;
		node.next=null;
		if (isEmpty()) {
			head=node;
			tail=node;
		}else {
			tail.next=node;
			tail=tail.next;
		}
		count++;
	}
	public T dequeue() {
		if (isEmpty()) {
			return null;
		}
		Node node=head;
		head=head.next;
		count--;
		return node.element;
	}
	public Iterator<T> iterator() {
		return new QueueIterator();
	}
	
	//迭代器类，嵌套类
	private class QueueIterator implements Iterator<T>{
        private Node current=head;
		public boolean hasNext() {
			return current!=null;
		}

		public T next() {
			T element=current.element;
			current=current.next;
			return element;
		}

		public void remove() {}
		
	}
}
