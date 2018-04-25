package com.lizi.datastructure.graph;

import com.lizi.datastructure.Bag;

public class Digraph {
	private final int V;//顶点个数
	private int E;//边的数目
	private Bag<Integer>[] adjacent;//邻接包
	@SuppressWarnings("unchecked")
	public Digraph(int V){
		this.V=V;
		this.E=0;
		adjacent=(Bag<Integer>[])new Bag[V];
		for (int i = 0; i < V; i++) {
			adjacent[i]=new Bag<Integer>();
		}
	}
	public int V() {
		return V;
	}
	public int E(){
		return E;
	}
	public void addEdge(int v,int w) {
		adjacent[v].add(w);
		E++;
	}
	public Iterable<Integer> adjacent(int v) {
		return adjacent[v];
	}
	public Digraph reverse() {
		Digraph R=new Digraph(V);
		for (int v = 0; v <V; v++) {
			for (int w: adjacent(v)) {
				R.addEdge(w, v);
			}
		}
		return R;
	}
}
