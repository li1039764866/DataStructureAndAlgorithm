package com.lizi.algorithm.digraph;

import com.lizi.algorithm.graph.WeightedUnionFind;
import com.lizi.datastructure.MinPQ;
import com.lizi.datastructure.Queue;
import com.lizi.datastructure.graph.Edge;
import com.lizi.datastructure.graph.EdgeWeightedGraph;
//��³˹�����㷨-��С������
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
			Edge edge=pq.delMin();//��pq�õ�Ȩ����С�ı�
			int v=edge.either();
			int w=edge.other(v);
			if(uf.connected(v, w)) continue;//�޳���Ч��
			uf.union(v, w);//�ϲ���ͨͼ
			mst.enqueue(edge);//��С�������
			
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
