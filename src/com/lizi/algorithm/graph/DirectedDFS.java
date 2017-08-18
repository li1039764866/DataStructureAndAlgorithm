package com.lizi.algorithm.graph;

import com.lizi.datastructure.graph.Digraph;

public class DirectedDFS {
	private boolean[] marked;
	public DirectedDFS(Digraph graph,int source) {//�����¼source���Ե���ĵ�
		marked=new boolean[graph.V()];
		DFS(graph,source);
	}
	//sourcesΪ��ļ��ϣ�marked����Ϊ��Щ����Ե���ĵ㣬һ�β��������
	public DirectedDFS (Digraph graph,Iterable<Integer> sources) {
		marked=new boolean[graph.V()];
		for (int  s: sources) {
			if(!marked[s]) DFS(graph,s);
		}
	}
	private void DFS(Digraph graph,int v) {
		marked[v]=true;
		for (int w : graph.adjacent(v)) {
			if(!marked[w]) DFS(graph,w);
		}
	}
	public boolean marked(int v) {
		return marked[v];
	}
}
