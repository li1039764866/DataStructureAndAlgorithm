package com.lizi.algorithm;

import com.lizi.datastructure.MinPQ;
import com.lizi.datastructure.Queue;
import com.lizi.datastructure.graph.Edge;
import com.lizi.datastructure.graph.EdgeWeightedGraph;
//普里姆算法的延时版本
//所谓延迟，就是将新加入MST顶点的所有边加入队列，而这样有可能会将两点都在MST的边加入队列，此时我们不会管这些边，
//即使它们已经是无效边，等到弹出最小边再删除这些边，即是延时处理，即时版本就是不将这些无效边加入队列，在新加入
//边使只加入横切边，剔除了无效边
public class LazyPrimMST {
	private boolean[] marked;//标记加入的点
	private Queue<Edge> mst;//最小生成树所有的边
	private MinPQ<Edge> pq;//最小优先队列，便于查找最小边
	public LazyPrimMST(EdgeWeightedGraph g){
		pq=new MinPQ<Edge>();
		marked=new boolean[g.V()];
		mst=new Queue<Edge>();
		visit(g,0);
		while (!pq.isEmpty()) {
			Edge edge=pq.delMin();//弹出队列中最小边
			int v=edge.either();//获取两个顶点
			int w=edge.other(v);
			if(marked[v]&&marked[w]) continue;//如果最小边已经被加入树中，则结束循环，即剔除无效边
			mst.enqueue(edge);//加入最小树
			if(!marked[v]) visit(g, v);//此时该边为横切边，已有一点被标记了，则最后两个if此时只会调用一次
			if(!marked[w]) visit(g, w);
			
		}
	}
	//标记顶点v并将所有连接v和未标记顶点的边加入pq
	public void visit(EdgeWeightedGraph g,int v){
		marked[v]=true;
		for (Edge e : g.adjacent(v)) {
			if(!marked[e.other(v)]) pq.insert(e);
		}
	}
	public Iterable<Edge> edges() {
		return mst;
	}
	public double weight() {
		return 0;
	}
}
