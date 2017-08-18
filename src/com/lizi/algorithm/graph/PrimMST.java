package com.lizi.algorithm.graph;

import com.lizi.datastructure.Bag;
import com.lizi.datastructure.IndexMinPQ;
import com.lizi.datastructure.graph.Edge;
import com.lizi.datastructure.graph.EdgeWeightedGraph;
//����ķ�㷨�ļ�ʱ�汾
public class PrimMST {
	private Edge[] edgeTo;//����������ı�
	private double[] distTo;//distTo[w]=edgeTo[w].weight();
	private boolean[] marked;//���v��������Ϊtrue
	private IndexMinPQ<Double> pq;//��Ч�ĺ��б�
	public PrimMST(EdgeWeightedGraph graph){
		edgeTo=new Edge[graph.V()];
		distTo=new double[graph.V()];
		marked=new boolean[graph.V()];
		for (int v = 0; v < graph.V(); v++) {
			distTo[v]=Double.POSITIVE_INFINITY;//�������
		}
		pq=new IndexMinPQ<Double>(graph.V());
		
		distTo[0]=0.0;
		pq.insert(0, 0.0);
		while (!pq.isEmpty()) {
			visit(graph,pq.delMin());//ɾ����С�߷��ض����±�
		}
	}
	
	@SuppressWarnings("deprecation")
	public void visit(EdgeWeightedGraph graph,int v) {
		marked[v]=true;
		for(Edge e:graph.adjacent(v)){//������ǰ������б�
			int w=e.other(v);//�ҵ�����һ����
			if(marked[w]) continue;//����Ѿ���ǣ�������
			if(e.weight()<distTo[w]){//������б�С�ڵ�ǰ�Ѿ��洢��ֵ�������Ϊ��С��
				edgeTo[w]=e;//������Щ��䶼�Ǹ���Ϊ��С�ߵĲ���
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
