package com.lizi.algorithm.graph;

import com.lizi.algorithm.digraph.DirectedDFS;
import com.lizi.datastructure.graph.Digraph;
//可达性检测，将检测结果保存在二维数组
public class TransitiveClosure {
	private DirectedDFS[] all;//二维数组，存储每个顶点到别的顶点是否可达
	public TransitiveClosure(Digraph digraph) {
		all=new DirectedDFS[digraph.V()];
		for (int i = 0; i < digraph.V(); i++) {//每个顶点都调用一次深度搜索
			all[i]=new DirectedDFS(digraph, i);
		}
	}
	boolean reachable(int v,int w){
		return all[v].marked(w);
	}
}
