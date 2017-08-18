package com.lizi.algorithm.graph;

import com.lizi.datastructure.graph.Graph;
//检测图中是否含有环
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
	//利用深度优先搜索，判断周围顶点（且不是刚经过的点）是否已经被标记过，如果已经被标记过，则说明是环
	private void DFS(Graph graph,int v,int u){//涉及三个点：i,v,u;i为最前面的点
		marked[v]=true;//该点已被访问
		for (int i:graph.adjacent(v)) {
			if(!marked(i)){
				DFS(graph,i,v);
			}else if(i!=u){//排除刚经过的点
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
