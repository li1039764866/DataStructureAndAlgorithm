package com.lizi.datastructure;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
	private Node top;  //栈顶
	private int count;  //链表结点数目
	
	private class Node{ //结点，嵌套类
		T element;
		Node next;
	}
	 public Stack() {
		top=null;
		count=0;
	}
	public boolean isEmpty() {
		return count==0;
	}
	public int size() {
		return count;
	}
	public void push(T element) {
		Node node=new Node();
		node.element=element;
		node.next=null;
		if (isEmpty()) {
			top=node;
		}else {
			node.next=top;
			top=node;
		}
		count++;
	}
	public T pop() {
		if (isEmpty()) {
			return null;
		}
		Node node=top;
		top=top.next;
		count--;
		return node.element;
	}
	public T getTop() {
		return top.element;
	}
	public Iterator<T> iterator() {
		return new StackIterator();
	}
	//迭代器类，嵌套类
		private class StackIterator implements Iterator<T>{
	        private Node current=top;
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
