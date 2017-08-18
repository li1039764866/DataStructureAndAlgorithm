package com.lizi.datastructure.symboltable;


//ɢ�б�������ģ�һЩ����Ĳ���ʱ�临�Ӷȿ��������Եģ���ɢ�б�ʹ�������������ײ
public class SeparateChainingHashST<Key, Value> {
	//private int elmentSize=0;//��ֵ������
	private int hashSize;//ɢ�б��С
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
