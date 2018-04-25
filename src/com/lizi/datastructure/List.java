package com.lizi.datastructure;

import java.util.Iterator;


public class List<T>implements Iterable<T> {
	private Node head;  //ָ������ͷ���
	//private Node tail;//ָ������β���
	private int count; //��������Ŀ
	
	private class Node{ //��㣬Ƕ����
		T element;
		Node next;
	}
	public List() {
		head=null;
		count=0;
	}
	public boolean isEmpty() {
		return count==0;
	}
	
	public void add(T element,int index) {
		if (index>count) {
			return;
		}
		Node node = new Node(); //��ʼ���½��
		node.element=element;
		node.next=null;
		
		int currentNode=0;
		Node current=head;
		Node previous=head;
		while (currentNode!=index) {
			previous = current;  
            current = current. next;  
            index++;		
		}
		node. next = current;  
        previous. next = node;
	}
	
	public T delete(int index) {
		if (index>count) {
			return null;
		}
		Node current = head;  
        Node previous = head;  
         int pos=0;
         while ( pos != index) {  
             pos++;  
            previous = current;  
            current = current. next;  
        }  
         if(current == head) {  
             head = head. next;  
        } else {  
            previous. next = current. next;  
        }  
         return current.element;
	}
	public Iterator<T> iterator() {
		return new ListIterator();
	}
	//�������࣬Ƕ����
	private class ListIterator implements Iterator<T> {
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
