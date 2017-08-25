package com.lizi.algorithm.digraph;

import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.DirectedEdge;
import com.lizi.datastructure.graph.EdgeWeightedDigraph;
//����������������·���㷨
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
		for (int v : topological.order()) {//ʹ����������
			System.out.println("��������"+v);
			relax(digraph,v);
		}
	}
	
	private void relax(EdgeWeightedDigraph digraph,int v){
		for (DirectedEdge edge : digraph.adjacent(v)) {
			int w=edge.to();
			if(distTo[w]>distTo[v]+edge.weight()){//��·��>��·��
				distTo[w]=distTo[v]+edge.weight();//����Ϊ��·��
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
