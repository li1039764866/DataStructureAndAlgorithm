package com.lizi.datastructure.symboltable;

import java.util.ArrayList;
import java.util.List;
//二分搜索，基于排序数组
public class BinarySearchST<Key extends Comparable<Key>,Value> extends SymbolTable<Key, Value> {

	private Key[] keys;
	private Value[] values;
	private int size=0;
	@SuppressWarnings("unchecked")
	public BinarySearchST(int capacity) {
		keys=(Key[]) new Comparable[capacity];
		values=(Value[]) new Object[capacity];
		this.size=0;
	}
	@Override
	public void put(Key key, Value value) {
		if(value==null) {delete(key); return;}
		//如果键存在，则修改键值
		int pos=rank(key);
		if (pos<size&&keys[pos].compareTo(key)==0) {
			values[pos]=value;
			return;
		}
		//键值不存在，判断数组是否越界并将数组扩容
		if(size==keys.length) resize(2*keys.length);
		for (int i =size; i>pos; i--) {
			keys[i]=keys[i-1];
			values[i]=values[i-1];
		}
		keys[pos]=key;
		values[pos]=value;
		size++;
		System.out.println("添加了键："+key+" 值："+value);
		
	}

	@Override
	public Value get(Key key) {
		if(isEmpty()) return null;
		int pos=rank(key);
		if (pos<size&&keys[pos].compareTo(key)==0) 
			return values[pos];
		else return null;
	}

	@Override
	public Value delete(Key key) {
		int pos=rank(key);
		//没找到则返回空
		if (pos<size&&keys[pos].compareTo(key)!=0) {
			return null;
		}
		Value value = values[pos];
		if(size<keys.length/2) resize(keys.length/2);
		for (int i = pos; i < size - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
		size--;
		System.out.println("删除了键："+key+" 值：");
		return value;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Key min() {
		return keys[0];
	}

	@Override
	public Key max() {
		return keys[size-1];
	}

	@Override
	public Key floor(Key key) {
		int pos=rank(key);
		if (pos<size&&keys[pos].compareTo(key)==0) {
			return keys[pos];
		}
		return keys[pos-1];
	}

	@Override
	public Key ceiling(Key key) {
		int pos=rank(key);
		return keys[pos];
	}
	//非递归的二分查找
	@Override
	public int rank(Key key) {
		int low=0;
		int high=size-1;
		while (low<=high) {
			int middle=low+(high-low)/2;
			int cmp=key.compareTo(keys[middle]);
			if (cmp<0) high=middle-1;
			else if(cmp>0) low=middle+1;
			else return middle;
		}
		return low;
	}
	//递归的二分查找
	public int rank(Key key,int low,int high) {
		if(low>high) return low;
		int pos=rank(key);
		int cmp=key.compareTo(keys[pos]);
		if (cmp>0) return rank(key, pos+1, high);
		else if(cmp<0) return rank(key,low,pos-1);
		else return pos;
	}
	@Override
	public Key select(int index) {
		return keys[index];
	}
	@Override
	public Iterable<Key> keys(Key low, Key high) {
		List<Key> keys=new ArrayList<Key>(size);
		for (int i = 0; i <size; i++) {
			keys.add(this.keys[i]);
		}
		return keys;
	}
	//该函数仅仅将容量扩大，但是有效元素数量并未改变，所以大小还是size
	@SuppressWarnings("unchecked")
	public void resize(int capacity) {
		Key[] newKeys=(Key[]) new Comparable[capacity];
		Value[] newValues=(Value[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			newKeys[i]=keys[i];
			newValues[i]=values[i];
		}
		keys=newKeys;
		values=newValues;
	}
	

}
