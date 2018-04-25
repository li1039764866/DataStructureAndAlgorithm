package com.lizi.algorithm.graph;

import com.lizi.datastructure.graph.Graph;
//检测是否是二部图
public class TwoColor {
	private boolean[] marked;
	private boolean[] color;//记录每个顶点的颜色
	private boolean isTwoColorable=true;
	public TwoColor(Graph graph){
		marked=new boolean[graph.V()];
		color=new boolean[graph.V()];
		for (int i = 0; i < graph.V(); i++) {
			if(!marked[i])
				DFS(graph,i);
		}
	}
	//利用深度优先搜索，判断周围被标记的点是否与当前点颜色互互异
	private void DFS(Graph graph,int v){//涉及两个个点：i,v;i为最前面的点
		marked[v]=true;//该点已被访问
		for (int i:graph.adjacent(v)) {
			if(!marked(i)){
				color[i]=!color[v];//同边两顶点涂不同色
				DFS(graph,i);
			}else if(color[v]==color[i]){//该点已被标记，检查颜色是否互异
				isTwoColorable=false;
			}
		}	
	}
	public boolean marked(int w) {
		return marked[w];
	}
	public boolean isTwoColorable() {
		return isTwoColorable;
	}
}
