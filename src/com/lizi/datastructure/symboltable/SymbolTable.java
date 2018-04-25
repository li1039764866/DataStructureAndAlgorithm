package com.lizi.datastructure.symboltable;
//������ű�ӿ�
public abstract class SymbolTable<Key extends Comparable<Key>,Value> {
	
	//����ֵ�Դ�����У���ֵΪ���򽫽�key�ӱ���ɾ����
	public abstract void put(Key key,Value value);
	
	//��ȡ��Key��Ӧ��ֵ������key�����ڷ��ؿգ�
	public abstract Value get(Key key);
	
	//�ӱ���ɾȥ��key�������Ӧ��ֵ��
	public abstract Value delete(Key key);
	
	//�����Ƿ���ڸü�
	public boolean contains(Key key){
		return get(key)!=null;
	}
	
	//���Ƿ�Ϊ��
	public boolean isEmpty(){
		return size()==0;
	}
	
	//���еļ�ֵ������
	public abstract int size();
	
	//��С�ļ�
	public abstract Key min();
	
	//���ļ�
	public abstract Key max();
	
	//С�ڵ���Key������
	public abstract Key floor(Key key);
	
	//���ڵ���Key����С��
	public abstract Key ceiling(Key key);
	
	//С��key�ļ�������
	public abstract int rank(Key key);
	
	//ɾ����С�ļ�
	public void deleteMin(){
		delete(min());
	}
	
	//ɾ�����ļ�
	public void deleteMax(){
		delete(max());
	}
	//�����±�Ϊindex�ļ�
	public abstract Key select(int index);
	//[low....high]֮���������,�������Ԫ��
	public int size(Key low,Key high){
		if (high.compareTo(low)<0)
			return 0;
		else if (contains(high))
			return rank(high)-rank(low)+1;
		else 
			return rank(high)-rank(low);
	}
	
	//[low....high]֮����ļ��ϣ��Ѿ�����
	public abstract Iterable<Key> keys(Key low,Key high);
	
	//�������м��ļ��ϣ��Ѿ�����
	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	
}
