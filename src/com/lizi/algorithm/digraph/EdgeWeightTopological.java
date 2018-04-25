package com.lizi.algorithm.digraph;


import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.DirectedEdge;
import com.lizi.datastructure.graph.EdgeWeightedDigraph;

public class EdgeWeightTopological {
	private boolean[] marked;
	private Stack<Integer> reversePost;//��������
	public EdgeWeightTopological(EdgeWeightedDigraph digraph) {
		reversePost=new Stack<Integer>();
		marked=new boolean[digraph.V()];
		for (int i = 0; i < digraph.V(); i++) {
			if(!marked[i]) DFS(digraph,i);
		}
	}
	private void DFS(EdgeWeightedDigraph digraph,int v) {//ͬ�ʼ����ȱ���һ����ֻ�ǽ������Ķ��㰴�ղ�ͬ˳��洢����
		marked[v]=true;
		for (DirectedEdge edge: digraph.adjacent(v)) {
			if(!marked[edge.to()]) DFS(digraph,edge.to());
		}
		reversePost.push(v);
	}
	public Iterable<Integer> order(){
		return reversePost;
	}
}

