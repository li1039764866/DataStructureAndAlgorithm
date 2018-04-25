package com.lizi.datastructure.graph;

import com.lizi.datastructure.Bag;
//加权无向图，每个Edge对象被引用两次，即在邻接表中出现两次
public class EdgeWeightedGraph {
	private final int V;
	private int E;
	private Bag<Edge>[] adjacent;
	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V) {
		this.V=V;
		this.E=0;
		adjacent=(Bag<Edge>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adjacent[i]=new Bag<Edge>();
		}
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	//此处虽添加了两次，但只是一个对象被引用了两次
	public void addEgde(Edge e) {
		int v=e.either();
		int w=e.other(v);
		adjacent[v].add(e);
		adjacent[w].add(e);
		E++;
	}
	public Iterable<Edge> adjacent(int v) {
		return adjacent[v];
	}
	//返回图中所有边，避免重复，每次选择从小到大的边
	public Iterable<Edge> edges() {
		Bag<Edge> b=new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			for (Edge e : adjacent[v]) {
				if(e.other(v)>v) b.add(e);
			}
		}
		return b;
	}
}
