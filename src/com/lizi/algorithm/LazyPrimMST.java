package com.lizi.algorithm;

import com.lizi.datastructure.MinPQ;
import com.lizi.datastructure.Queue;
import com.lizi.datastructure.graph.Edge;
import com.lizi.datastructure.graph.EdgeWeightedGraph;
//����ķ�㷨����ʱ�汾
//��ν�ӳ٣����ǽ��¼���MST��������б߼�����У��������п��ܻὫ���㶼��MST�ı߼�����У���ʱ���ǲ������Щ�ߣ�
//��ʹ�����Ѿ�����Ч�ߣ��ȵ�������С����ɾ����Щ�ߣ�������ʱ������ʱ�汾���ǲ�����Щ��Ч�߼�����У����¼���
//��ʹֻ������бߣ��޳�����Ч��
public class LazyPrimMST {
	private boolean[] marked;//��Ǽ���ĵ�
	private Queue<Edge> mst;//��С���������еı�
	private MinPQ<Edge> pq;//��С���ȶ��У����ڲ�����С��
	public LazyPrimMST(EdgeWeightedGraph g){
		pq=new MinPQ<Edge>();
		marked=new boolean[g.V()];
		mst=new Queue<Edge>();
		visit(g,0);
		while (!pq.isEmpty()) {
			Edge edge=pq.delMin();//������������С��
			int v=edge.either();//��ȡ��������
			int w=edge.other(v);
			if(marked[v]&&marked[w]) continue;//�����С���Ѿ����������У������ѭ�������޳���Ч��
			mst.enqueue(edge);//������С��
			if(!marked[v]) visit(g, v);//��ʱ�ñ�Ϊ���бߣ�����һ�㱻����ˣ����������if��ʱֻ�����һ��
			if(!marked[w]) visit(g, w);
			
		}
	}
	//��Ƕ���v������������v��δ��Ƕ���ı߼���pq
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
