package com.lizi.algorithm.digraph;

import com.lizi.algorithm.graph.WeightedUnionFind;
import com.lizi.datastructure.MinPQ;
import com.lizi.datastructure.Queue;
import com.lizi.datastructure.graph.Edge;
import com.lizi.datastructure.graph.EdgeWeightedGraph;
//克鲁斯卡尔算法-最小生成树
public class KruskalMST {
	private Queue<Edge> mst;
	public KruskalMST(EdgeWeightedGraph graph) {
		mst=new Queue<Edge>();
		Edge[] edges=new Edge[graph.E()];
		int i=0;
		for (Edge e : graph.edges()) {
			edges[i]=e;
			i++;
		}
		MinPQ<Edge> pq=new MinPQ<Edge>(edges);
		WeightedUnionFind uf=new WeightedUnionFind(graph.V());
		while (!pq.isEmpty()&&mst.size()<graph.V()-1) {
			Edge edge=pq.delMin();//从pq得到权重最小的边
			int v=edge.either();
			int w=edge.other(v);
			if(uf.connected(v, w)) continue;//剔除无效边
			uf.union(v, w);//合并连通图
			mst.enqueue(edge);//最小边入队列
			
		}
	}
	public Iterable<Edge> edges(){
		return mst;
	}
	public double weight() {
		double weightSum=0.0;
		for (Edge e : edges()){
			weightSum+=e.weight();
		}
		return weightSum;
	}
}
