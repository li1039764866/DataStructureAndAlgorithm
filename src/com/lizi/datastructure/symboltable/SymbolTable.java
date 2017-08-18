package com.lizi.datastructure.symboltable;
//有序符号表接口
public abstract class SymbolTable<Key extends Comparable<Key>,Value> {
	
	//将键值对存入表中（若值为空则将建key从表中删除）
	public abstract void put(Key key,Value value);
	
	//获取键Key对应的值（若键key不存在返回空）
	public abstract Value get(Key key);
	
	//从表中删去键key（及其对应的值）
	public abstract Value delete(Key key);
	
	//表中是否存在该键
	public boolean contains(Key key){
		return get(key)!=null;
	}
	
	//表是否为空
	public boolean isEmpty(){
		return size()==0;
	}
	
	//表中的键值对数量
	public abstract int size();
	
	//最小的键
	public abstract Key min();
	
	//最大的键
	public abstract Key max();
	
	//小于等于Key的最大键
	public abstract Key floor(Key key);
	
	//大于等于Key的最小键
	public abstract Key ceiling(Key key);
	
	//小于key的键的数量
	public abstract int rank(Key key);
	
	//删除最小的键
	public void deleteMin(){
		delete(min());
	}
	
	//删除最大的键
	public void deleteMax(){
		delete(max());
	}
	//返回下标为index的键
	public abstract Key select(int index);
	//[low....high]之间键的数量,包括相等元素
	public int size(Key low,Key high){
		if (high.compareTo(low)<0)
			return 0;
		else if (contains(high))
			return rank(high)-rank(low)+1;
		else 
			return rank(high)-rank(low);
	}
	
	//[low....high]之间键的集合，已经排序
	public abstract Iterable<Key> keys(Key low,Key high);
	
	//表中所有键的集合，已经排序
	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	
}
