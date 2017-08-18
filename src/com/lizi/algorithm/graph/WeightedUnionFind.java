package com.lizi.algorithm.graph;

public class WeightedUnionFind {
	private int[] id;//父链接数组
	private int[] size;//各个根节点所对应的分量大小
	private int count;//连通分量的大小
	public WeightedUnionFind(int N){
		count=N;
		id=new int[N];
		for (int i = 0; i < N; i++) {
			id[i]=i;
		}
		size=new int[N];
		for (int i = 0; i < N; i++) {
			size[i]=1;//默认其自身为一个连通分量
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
		if(size[i]<size[j]){//小树加到大树上面不会使树的高度变化，为下一次寻找根的操作少了一些迭代
			id[i]=j;
			size[j]+=size[i];
		}else {
			id[j]=i;
			size[i]+=size[j];
		}
		count--;
	}
}
