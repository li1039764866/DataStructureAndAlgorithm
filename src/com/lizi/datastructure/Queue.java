package com.lizi.datastructure;

import java.util.Iterator;

public class Queue<T>implements Iterable<T> {
	private Node head;  //ָ�����ͷ���
	private Node tail;  //ָ�����β���
	private int count;  //��������Ŀ
	
	private class Node{ //��㣬Ƕ����
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
	
	//�������࣬Ƕ����
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
