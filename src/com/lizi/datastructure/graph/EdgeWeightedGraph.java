package com.lizi.datastructure.graph;

import com.lizi.datastructure.Bag;
//��Ȩ����ͼ��ÿ��Edge�����������Σ������ڽӱ��г�������
public class EdgeWeightedGraph {
	private final int V;
	private int E;
	private Bag<Edge>[] adjacent;
	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V) {
		this.V=V;
		this.E=0;
		adjacent=(Bag<Edge>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adjacent[i]=new Bag<Edge>();
		}
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	//�˴�����������Σ���ֻ��һ����������������
	public void addEgde(Edge e) {
		int v=e.either();
		int w=e.other(v);
		adjacent[v].add(e);
		adjacent[w].add(e);
		E++;
	}
	public Iterable<Edge> adjacent(int v) {
		return adjacent[v];
	}
	//����ͼ�����бߣ������ظ���ÿ��ѡ���С����ı�
	public Iterable<Edge> edges() {
		Bag<Edge> b=new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			for (Edge e : adjacent[v]) {
				if(e.other(v)>v) b.add(e);
			}
		}
		return b;
	}
}
