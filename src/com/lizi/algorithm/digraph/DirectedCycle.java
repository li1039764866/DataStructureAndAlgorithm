package com.lizi.algorithm.digraph;

import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.Digraph;
//有向图环的检测
public class DirectedCycle {
	private boolean[] marked;//标记是否访问过该点
	private int[] edgeTo;//记录到达该顶点的上一顶点
	private Stack<Integer> cycle;//保存环经过的顶点
	private boolean[]onStack;//检测该点是否在栈中，压栈为true，退栈则为false
	public DirectedCycle(Digraph graph) {
		onStack =new boolean[graph.V()];
		edgeTo=new int[graph.V()];
		marked=new boolean[graph.V()];
		for (int i = 0; i < graph.V(); i++) {
			if(!marked[i]) DFS(graph,i);
		}
	}
	private void DFS(Digraph graph,int v) {
		onStack[v]=true;
		marked[v]=true;
		for (int w: graph.adjacent(v)) {
			if(this.hasCycle()) return;
			else if(!marked[w]){
				edgeTo[w]=v;
				DFS(graph,w);
			}else if(onStack[w]){//该点已被访问过且并未退栈，说明是当前前路已经经过的点，此时又遇见，则为环
				cycle=new Stack<Integer>();//存储访问路径
				for (int x = v; x !=w; x=edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);//首尾压栈
				cycle.push(v);
			}
			onStack[v]=false;//前进路线回退时该点恢复为未入栈状态
		}
	}
	public boolean hasCycle(){
		return cycle!=null;
	}
	public Iterable<Integer> cycle() {
		return cycle;
	}
}
