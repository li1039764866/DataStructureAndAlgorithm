package com.lizi.datastructure.graph;

import com.lizi.datastructure.Bag;

public class EdgeWeightedDigraph {
	private final int V;
	private int E;
	private Bag<DirectedEdge> [] adjacent;//ÏàÁÚ±ßÊı×é
	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int v) {
		this.V=v;
		this.E=0;
		adjacent=(Bag<DirectedEdge> [])new Bag[V];
		for (int i=0; i<V;i++) {
			adjacent[i]=new Bag<DirectedEdge>();
		}
	}
	public int V() {
		return V;
	}
	public int E(){
		return E;
	}
	public void addEdge(DirectedEdge e) {
		adjacent[e.from()].add(e);
		E++;
	}
	public Iterable<DirectedEdge> adjacent(int v) {
		return adjacent[v];
	}
	public Iterable<DirectedEdge> edges() {
		Bag<DirectedEdge> bag=new Bag<DirectedEdge>();
		for (int v = 0; v < V; v++) {
			for (DirectedEdge e : adjacent[v]) {
				bag.add(e);
			}
		}
		return bag;
	}
}
