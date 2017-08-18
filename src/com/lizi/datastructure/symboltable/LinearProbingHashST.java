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
	//���ɵ¿�Ŭ�أ���֤��������1/8--1/2֮��,����̽�ⷨ����ĵ����Ǳ���ģ����������Ԫ�ؾͽ�������ѭ����
	//��������ȴ���Բ�����
	private void resize(int hashSize) {
		LinearProbingHashST<Key, Value> lpHashST;
		lpHashST=new LinearProbingHashST<Key, Value>(hashSize);
		for (int i = 0; i < hashSize; i++) {
			if(keys[i]!=null)//������Ԫ�����²��뵽�µ�ɢ��֮��
				lpHashST.put(keys[i], values[i]);
		}
		keys=lpHashST.keys;
		values=lpHashST.values;
	}
	public void put(Key key,Value value) {
		if(elementSize>=hashSize/2) resize(2*hashSize);
		
		int i=hash(key);
		for (; keys[i]!=null; i=(i+1)% hashSize) {
			if(keys[i].equals(key)){//�޸�ֵ
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
			if(keys[i].equals(key)){//�޸�ֵ
				return values[i];
			}
		}
		return null;
	}
	public boolean contains(Key key) {
		return get(key)!=null;
	}
	
	//����Ӧ��ֵ����Ϊ�գ�������ֵ�غ���Ԫ�����²���
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
			keys[i]=null;//ȡ��ֵ��ָ�Ϊδ����ü�ֵʱ������
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
