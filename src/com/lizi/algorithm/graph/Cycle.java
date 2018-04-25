package com.lizi.algorithm.graph;

import com.lizi.datastructure.graph.Graph;
//���ͼ���Ƿ��л�
public class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	public Cycle(Graph graph){
		marked=new boolean[graph.V()];
		for (int i = 0; i < graph.V(); i++) {
			if(!marked[i])
				DFS(graph,i,i);
		}
	}
	//������������������ж���Χ���㣨�Ҳ��Ǹվ����ĵ㣩�Ƿ��Ѿ�����ǹ�������Ѿ�����ǹ�����˵���ǻ�
	private void DFS(Graph graph,int v,int u){//�漰�����㣺i,v,u;iΪ��ǰ��ĵ�
		marked[v]=true;//�õ��ѱ�����
		for (int i:graph.adjacent(v)) {
			if(!marked(i)){
				DFS(graph,i,v);
			}else if(i!=u){//�ų��վ����ĵ�
				hasCycle=true;
			}
		}	
	}
	public boolean marked(int w) {
		return marked[w];
	}
	public boolean hasCycle() {
		return hasCycle;
	}
}
