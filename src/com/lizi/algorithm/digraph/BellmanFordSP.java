package com.lizi.algorithm.digraph;

import com.lizi.datastructure.Queue;
import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.DirectedEdge;
import com.lizi.datastructure.graph.EdgeWeightedDigraph;
//������-�������·���㷨-���ڶ���ʵ��
public class BellmanFordSP {
	private double [] distTo;
	private DirectedEdge [] edgeTo;
	private boolean []onQ;  //�ö����Ƿ�����ڶ�����
	private Queue<Integer> queue;//���ڱ����ɵĶ���
	private int cost=0;      //relax�ĵ��ô���
	private Iterable<DirectedEdge> cycle;
	public BellmanFordSP(EdgeWeightedDigraph digraph,int s){
		distTo=new double[digraph.V()];
		edgeTo=new DirectedEdge[digraph.V()];
		onQ=new boolean[digraph.V()];
		queue=new Queue<Integer>();
		for (int i = 0; i < digraph.V(); i++) {
			distTo[i]=Double.POSITIVE_INFINITY;
		}
		distTo[s]=0.0;
		onQ[s]=true;
		while (!queue.isEmpty()&&this.hasNegativeCycle()) {
			int v=queue.dequeue();
			onQ[v]=false;
			relax(digraph,v);
			
		}
	}
	private void relax(EdgeWeightedDigraph digraph, int v) {
		for (DirectedEdge edge : digraph.adjacent(v)) {
			int w=edge.to();
			if (distTo[w]>distTo[v]+edge.weight()) {//�����+5>�����Ϊfalse
				distTo[w]=distTo[v]+edge.weight();
				edgeTo[w]=edge;
				if(!onQ[w]){
					queue.enqueue(w);
					onQ[w]=true;
				}
			}
			if(cost++ % digraph.V()==0)
				findNegativeCycle();
		}
		
	}
	private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }
	private boolean hasNegativeCycle() {
		return cycle!=null;
	}
	public Iterable<DirectedEdge> negativeCycle() {
		return cycle;
	}
	public double distTo(int v) {
        validateVertex(v);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return distTo[v];
    }
	public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
	public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
	
	private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
