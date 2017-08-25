package com.lizi.algorithm.digraph;

import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.DirectedEdge;
import com.lizi.datastructure.graph.EdgeWeightedDigraph;
//基于拓扑排序的最短路径算法
public class AcyclicSP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	public AcyclicSP(EdgeWeightedDigraph digraph,int s) {
		edgeTo=new DirectedEdge[digraph.V()];
		distTo=new double[digraph.V()];
		
		for (int i = 0; i < digraph.V(); i++) {
			distTo[i]=Double.POSITIVE_INFINITY;
		}
		distTo[s]=0.0;
		EdgeWeightTopological topological=new EdgeWeightTopological(digraph);
		for (int v : topological.order()) {//使用拓扑排序
			System.out.println("拓扑排序："+v);
			relax(digraph,v);
		}
	}
	
	private void relax(EdgeWeightedDigraph digraph,int v){
		for (DirectedEdge edge : digraph.adjacent(v)) {
			int w=edge.to();
			if(distTo[w]>distTo[v]+edge.weight()){//旧路径>新路径
				distTo[w]=distTo[v]+edge.weight();//更新为新路径
				edgeTo[w]=edge;
			}
		}
	}
	
	public double distTo(int v){
		return distTo[v];
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
