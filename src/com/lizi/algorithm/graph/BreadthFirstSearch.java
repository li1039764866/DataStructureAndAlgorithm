package com.lizi.algorithm.graph;



import com.lizi.datastructure.Queue;
import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.Graph;

public class BreadthFirstSearch {
	private boolean[] marked;
	private int [] edgeTo; //从起点到一个顶点的一直路径上的最后一个顶点
	private final int source;
	public BreadthFirstSearch(Graph graph,int source) {
		marked=new boolean[graph.V()];
		edgeTo=new int[graph.V()];
		for (int i = 0; i < edgeTo.length; i++) {
			edgeTo[i]=-1;
		}
		this.source=source;
		BFS(graph,source);
		
	}
	private void BFS(Graph graph,int v){
		Queue<Integer> queue=new Queue<Integer>();
		marked[v]=true;
		queue.enqueue(v);
		while(!queue.isEmpty()){
			v=queue.dequeue();
			for (int i:graph.adjacent(v)) {
				if(!marked(i)){
					edgeTo[i]=v;
					marked[i]=true;
					queue.enqueue(i);
				}
			}
		}
	}
		
	public boolean marked(int w) {
		return marked[w];
	}
	public boolean hasPathTo(int v){
		return marked[v];
	}
	//普通路径并不一定是最短路径，值为-1为不可到达的点
	public Stack<Integer> pathTo(int v) {
		if(v==source) {
			System.out.println("起点和终点一样!!!");
			return null;
		}else if (edgeTo[v]==-1) {
			System.out.println("不能从"+source+"到达"+v+"!!!");
			return null;
		}
		//if(!hasPathTo(v)) return null;
		Stack<Integer> path=new Stack<Integer>();
		for (int i = v; i!=source; i=edgeTo[i]) {
			path.push(i);
		}
		path.push(source);
		return path;
	}
}
