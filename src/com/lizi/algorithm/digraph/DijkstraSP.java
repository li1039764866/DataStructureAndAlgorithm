package com.lizi.algorithm.digraph;

import com.lizi.datastructure.IndexMinPQ;
import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.DirectedEdge;
import com.lizi.datastructure.graph.EdgeWeightedDigraph;
// �Ͻ�˹�������·���㷨-������С�������ȶ���ʵ��
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
		for (DirectedEdge edge : digraph.adjacent(v)) {//����㷢ɢ��ȥ����ɢv-w�ߣ���w����Ϊ�µ���㣬����·��
			int w=edge.to();
			if(distTo[w]>distTo[v]+edge.weight()){//��·��>��·��
				distTo[w]=distTo[v]+edge.weight();//����Ϊ��·��
				edgeTo[w]=edge;
				if(pq.contains(w)) pq.change(w, distTo[w]);//����Ѿ�����һ������·���ڶ������棬�������ֵ
				else               pq.insert(w, distTo[w]);//�µ�ԭ�����
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
