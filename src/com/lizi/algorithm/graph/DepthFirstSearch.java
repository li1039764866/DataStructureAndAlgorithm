package com.lizi.algorithm.graph;


import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.Graph;

public class DepthFirstSearch {
	private boolean[] marked;
	private int [] edgeTo; //从起点到一个顶点的一直路径上的最后一个顶点
	private final int source;
	public DepthFirstSearch(Graph graph,int source) {
		marked=new boolean[graph.V()];
		edgeTo=new int[graph.V()];
		for (int i = 0; i < edgeTo.length; i++) {
			edgeTo[i]=-1;
		}
		this.source=source;
		//DFSByStack(graph,source);
		DFSByRecursive(graph,source);
		
	}
	//从第一个顶点开始访问，访问相邻顶点表中第一个相邻的顶点，然后又依次访问下一个最前面的顶点，直到回到遇到重复的点，
	//此时回退到递归函数的上一次调用，循环上一次顶点相邻的第二个顶点
	private void DFSByRecursive(Graph graph,int v){
		marked[v]=true;//该点已被访问
		for (int i:graph.adjacent(v)) {
			if(!marked(i)){
				edgeTo[i]=v;//即将前往下一节点，将该节点保存
				DFSByRecursive(graph,i);
			}
		}
		
	}
	public void DFSByStack(Graph graph,int v) {
		Stack<Integer> stack=new Stack<Integer>();
		stack.push(v);//起点元素压栈，以备退回起点
		while (!stack.isEmpty()) {//getTop（）为我实现的Stack类函数，可以使用java.util.Stack中的get(stack.size()-1)代替
			int next=stack.getTop();//获取下一个顶点，起点开始
			if(!marked(next)){//如果未标记，则记录路径信息
				edgeTo[next]=v;
				v=next;	
			}else {//如果已经标记，则应该回退到上一个点
				v=stack.pop();	
			}
			marked[v]=true;
			for (int i :graph.adjacent(v)) {
				if(!marked(i)){//如果没有标记，则是该点可以到达而没有到达的点
				stack.push(i);
				break;
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
	//普通路径并不一定是最短路径,edgeTo数组值与下标相同为起点，值为-1为不可到达的点
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
