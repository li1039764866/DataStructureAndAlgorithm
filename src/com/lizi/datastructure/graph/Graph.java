package com.lizi.datastructure.graph;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;

import com.lizi.datastructure.Bag;

public class Graph {
	private final int V;//¶¥µãÊý
	private int E;//±ßÊý
	private Bag<Integer> []adjacent;
	@SuppressWarnings("unchecked")
	public Graph(int v){
		this.V=v;
		this.E=0;
		adjacent=(Bag<Integer>[])new Bag[V];
		for (int i = 0; i < V; i++) {
			adjacent[i]=new Bag<Integer>();
		}
	}
	public Graph(DataInputStream in) throws IOException{
		this(in.readInt());
		int E=in.readInt();
		for (int i = 0; i < E; i++) {
			int v=in.readInt();
			int w=in.readInt();
			addEdge(v, w);
		}
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public void addEdge(int v,int w) {
		adjacent[v].add(w);
		adjacent[w].add(v);
		E++;
	}
	public Iterable<Integer> adjacent(int v) {
		return adjacent[v];
	}
	public void print() {
		for (int i = 0; i < V; i++) {
			System.out.print(i+":");
			Iterator<Integer> it=adjacent[i].iterator();
			while (it.hasNext()) {
				System.out.print(it.next()+"  ");
			}
			System.out.println();
		}
	}
}
