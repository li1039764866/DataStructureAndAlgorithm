package com.lizi.algorithm.digraph;

import com.lizi.datastructure.IndexMinPQ;
import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.DirectedEdge;
import com.lizi.datastructure.graph.EdgeWeightedDigraph;
// 迪杰斯特拉最短路径算法-基于最小索引优先队列实现
public class DijkstraSP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	public DijkstraSP(EdgeWeightedDigraph digraph,int s) {
		edgeTo=new DirectedEdge[digraph.V()];
		distTo=new double[digraph.V()];
		pq=new IndexMinPQ<Double>(digraph.V());
		
		for (int i = 0; i < digraph.V(); i++) {
			distTo[i]=Double.POSITIVE_INFINITY;	
		}
		distTo[s]=0.0;
		pq.insert(s, 0.0);
		while (!pq.isEmpty()) {
			relax(digraph,pq.delMin());
		}
	}
	@SuppressWarnings("deprecation")
	private void relax(EdgeWeightedDigraph digraph,int v){
		for (DirectedEdge edge : digraph.adjacent(v)) {//从起点发散出去，发散v-w边，将w边作为新的起点，更新路径
			int w=edge.to();
			if(distTo[w]>distTo[v]+edge.weight()){//旧路径>新路径
				distTo[w]=distTo[v]+edge.weight();//更新为新路径
				edgeTo[w]=edge;
				if(pq.contains(w)) pq.change(w, distTo[w]);//如果已经加入一条长的路径在队列里面，需更正其值
				else               pq.insert(w, distTo[w]);//新的原点加入
			}
		}
	}
	public double distTo(int v){
		return distTo(v);
	}
	public boolean hasPathTo(int v){
		return distTo[v]<Double.POSITIVE_INFINITY;
	}
	public Iterable<DirectedEdge> pathTo(int v) {
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> path=new Stack<DirectedEdge>();
		for(DirectedEdge edge=edgeTo[v];edge!=null;edge=edgeTo[edge.from()]){
			path.push(edge);
		}
		return path;
		
	}
		
	
}
