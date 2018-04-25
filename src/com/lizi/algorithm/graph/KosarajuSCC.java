package com.lizi.algorithm.graph;

import com.lizi.algorithm.digraph.DepthFirstOrder;
import com.lizi.datastructure.graph.Digraph;

public class KosarajuSCC {
	private boolean[] marked;
	private int []id;
	private int count;
	public KosarajuSCC(Digraph digraph){
		marked=new boolean[digraph.V()];
		id=new int[digraph.V()];
		DepthFirstOrder order=new DepthFirstOrder(digraph);
		for (int s : order.reversePost()) {
			if(!marked[s]){
				DFS(digraph,s);
				count++;
			}
		}
	}
	private void DFS(Digraph digraph,int v){
		marked[v]=true;//该点已被访问
		id[v]=count;
		for (int i:digraph.adjacent(v)) {
			if(!marked[i]){
				DFS(digraph,i);
			}
		}	
	}
	public boolean stronglyConnected(int v,int w){
		return id[v]==id[w];
	}
	public int id(int v){
		return id[v];
	}
	public int count(){
		return count;
	}
}
