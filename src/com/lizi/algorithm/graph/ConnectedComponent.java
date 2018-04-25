package com.lizi.algorithm.graph;

import com.lizi.datastructure.graph.Graph;

public class ConnectedComponent {
	private boolean[] marked;//记录数组是否被标记过
	private int[] id;//处于同一个连通分量中的顶点值相同
	private int count=0;//记录连通分量的数量
	public ConnectedComponent(Graph graph) {
		marked=new boolean[graph.V()];
		id=new int[graph.V()];
		for (int i = 0; i < graph.V(); i++) {
			if(!marked[i]){//从0开始，进入一次就将该点所能到达的顶点全部标记
				DFSByRecursive(graph, i);
				count++;
			}
		}
		
	}
	private void DFSByRecursive(Graph graph,int v){
		marked[v]=true;//该点已被访问
		id[v]=count;//以顶点为下标连通的顶点值都一样
		for (int i:graph.adjacent(v)) {
			if(!marked(i)){
				DFSByRecursive(graph,i);
			}
		}
	}
	public boolean marked(int v) {
		return marked[v];
	}
	public boolean connected(int v,int w) {
		return id[v]==id[w];
	}
	public int id(int v) {
		return id[v];
	}
	public int count() {
		return count;
	}
	public void print() {
		System.out.println("图中共有"+count+"个分量!!!");
		for (int i = 0; i <count ; i++) {
			for (int j = 0; j < id.length; j++) {
				if(id[j]==i)
					System.out.print(j+"  ");
			}
			System.out.println();
		}
	}
}
