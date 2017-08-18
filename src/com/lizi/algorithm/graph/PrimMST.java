package com.lizi.algorithm.graph;

import com.lizi.datastructure.Bag;
import com.lizi.datastructure.IndexMinPQ;
import com.lizi.datastructure.graph.Edge;
import com.lizi.datastructure.graph.EdgeWeightedGraph;
//普里姆算法的即时版本
public class PrimMST {
	private Edge[] edgeTo;//距离树最近的边
	private double[] distTo;//distTo[w]=edgeTo[w].weight();
	private boolean[] marked;//如果v在树中则为true
	private IndexMinPQ<Double> pq;//有效的横切边
	public PrimMST(EdgeWeightedGraph graph){
		edgeTo=new Edge[graph.V()];
		distTo=new double[graph.V()];
		marked=new boolean[graph.V()];
		for (int v = 0; v < graph.V(); v++) {
			distTo[v]=Double.POSITIVE_INFINITY;//正无穷大
		}
		pq=new IndexMinPQ<Double>(graph.V());
		
		distTo[0]=0.0;
		pq.insert(0, 0.0);
		while (!pq.isEmpty()) {
			visit(graph,pq.delMin());//删除最小边返回顶点下标
		}
	}
	
	@SuppressWarnings("deprecation")
	public void visit(EdgeWeightedGraph graph,int v) {
		marked[v]=true;
		for(Edge e:graph.adjacent(v)){//遍历当前点的所有边
			int w=e.other(v);//找到另外一个点
			if(marked[w]) continue;//如果已经标记，则跳过
			if(e.weight()<distTo[w]){//如果横切边小于当前已经存储的值，则更换为更小边
				edgeTo[w]=e;//下面这些语句都是更新为最小边的操作
				distTo[w]=e.weight();
				if(pq.contains(w)) pq.change(w, distTo[w]);
				else               pq.insert(w, distTo[w]);
			}
		}
	}
	public Iterable<Edge> edges(){
		Bag<Edge> mst=new Bag<Edge>();
		for (int i = 0; i < edgeTo.length; i++) {
			mst.add(edgeTo[i]);
		}
		return mst;
	}
}
