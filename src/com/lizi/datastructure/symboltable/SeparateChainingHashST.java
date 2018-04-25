package com.lizi.datastructure.symboltable;


//散列表是无序的，一些有序的操作时间复杂度可能是线性的，该散列表使用拉链法解决碰撞
public class SeparateChainingHashST<Key, Value> {
	//private int elmentSize=0;//键值对数量
	private int hashSize;//散列表大小
	private SequentialSearchST<Key, Value> []sts;
	public SeparateChainingHashST(){
		this(997);
	}
	@SuppressWarnings("unchecked")
	public SeparateChainingHashST(int hashSize) {
		this.hashSize=hashSize;
		sts=(SequentialSearchST<Key, Value>[])new SequentialSearchST[hashSize];
		for (int i = 0; i < hashSize; i++) {
			sts[i]=new SequentialSearchST<Key, Value>();
		}
	}
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff % hashSize);
	}
	public Value get(Key key) {
		return sts[hash(key)].get(key);
	}
	public void put(Key key,Value value) {
		sts[hash(key)].put(key, value);
	}
	public Value delete(Key key) {
		return sts[hash(key)].delete(key);
	}
}
