package com.lizi.algorithm.graph;

public class WeightedUnionFind {
	private int[] id;//����������
	private int[] size;//�������ڵ�����Ӧ�ķ�����С
	private int count;//��ͨ�����Ĵ�С
	public WeightedUnionFind(int N){
		count=N;
		id=new int[N];
		for (int i = 0; i < N; i++) {
			id[i]=i;
		}
		size=new int[N];
		for (int i = 0; i < N; i++) {
			size[i]=1;//Ĭ��������Ϊһ����ͨ����
		}
	}
	public int count(){
		return count;
	}
	public boolean connected(int p,int q){
		return find(p)==find(q);
	}
	public int find(int p){
		while (p!=id[p]) {
			p=id[p];	
		}
		return p;
	}
	public void union(int p,int q){
		int i=find(p);
		int j=find(q);
		if(i==j) return;
		if(size[i]<size[j]){//С���ӵ��������治��ʹ���ĸ߶ȱ仯��Ϊ��һ��Ѱ�Ҹ��Ĳ�������һЩ����
			id[i]=j;
			size[j]+=size[i];
		}else {
			id[j]=i;
			size[i]+=size[j];
		}
		count--;
	}
}
