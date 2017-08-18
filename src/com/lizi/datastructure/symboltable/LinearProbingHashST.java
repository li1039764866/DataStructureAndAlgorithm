package com.lizi.datastructure.symboltable;


public class LinearProbingHashST<Key,Value> {
	private int elementSize;
	private int hashSize=16;
	private Key[] keys;
	private Value[] values;
	@SuppressWarnings("unchecked")
	public LinearProbingHashST() {
		keys=(Key [])new Object[hashSize];
		values=(Value [])new Object[hashSize];
	}
	@SuppressWarnings("unchecked")
	public LinearProbingHashST(int capacity) {
		hashSize=capacity;
		keys=(Key [])new Object[hashSize];
		values=(Value [])new Object[hashSize];
	}
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff % hashSize);
	}
	//唐纳德克努特：保证利用率在1/8--1/2之间,线性探测法数组的调整是必须的，如果被塞满元素就进入无限循环了
	//而拉链法却可以不调整
	private void resize(int hashSize) {
		LinearProbingHashST<Key, Value> lpHashST;
		lpHashST=new LinearProbingHashST<Key, Value>(hashSize);
		for (int i = 0; i < hashSize; i++) {
			if(keys[i]!=null)//将现有元素重新插入到新的散列之中
				lpHashST.put(keys[i], values[i]);
		}
		keys=lpHashST.keys;
		values=lpHashST.values;
	}
	public void put(Key key,Value value) {
		if(elementSize>=hashSize/2) resize(2*hashSize);
		
		int i=hash(key);
		for (; keys[i]!=null; i=(i+1)% hashSize) {
			if(keys[i].equals(key)){//修改值
				values[i]=value;
				return;
			}
		}
			keys[i]=key;
			values[i]=value;
			elementSize++;
		
	}
	public Value get(Key key) {
		for (int i=hash(key); keys[i]!=null; i=(i+1)% hashSize) {
			if(keys[i].equals(key)){//修改值
				return values[i];
			}
		}
		return null;
	}
	public boolean contains(Key key) {
		return get(key)!=null;
	}
	
	//将相应键值对置为空，并将键值簇后面元素重新插入
	public void delete(Key key) {
		if(!contains(key)) return;
		int i=hash(key);
		while(!key.equals(keys[i])){
			i=(i+1)%hashSize;
		}
		keys[i]=null;
		values[i]=null;
		i=(i+1)%hashSize;
		while(keys[i]!=null){
			Key k=keys[i];
			Value v=values[i];
			keys[i]=null;//取出值后恢复为未插入该键值时的条件
			values[i]=null;
			elementSize--;
			put(k, v);
			i=(i+1)%hashSize;
		}
		elementSize--;
		if(elementSize>0 && elementSize<=hashSize/8)
			resize(hashSize/2);
	}
}
