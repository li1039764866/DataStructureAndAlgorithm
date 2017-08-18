package com.lizi.algorithm.graph;

import com.lizi.datastructure.graph.Digraph;

public class DirectedDFS {
	private boolean[] marked;
	public DirectedDFS(Digraph graph,int source) {//数组记录source可以到达的点
		marked=new boolean[graph.V()];
		DFS(graph,source);
	}
	//sources为点的集合，marked数组为这些点可以到达的点，一次测量多个点
	public DirectedDFS (Digraph graph,Iterable<Integer> sources) {
		marked=new boolean[graph.V()];
		for (int  s: sources) {
			if(!marked[s]) DFS(graph,s);
		}
	}
	private void DFS(Digraph graph,int v) {
		marked[v]=true;
		for (int w : graph.adjacent(v)) {
			if(!marked[w]) DFS(graph,w);
		}
	}
	public boolean marked(int v) {
		return marked[v];
	}
}
