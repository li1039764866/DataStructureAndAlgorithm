package com.lizi.algorithm.graph;



import com.lizi.datastructure.Queue;
import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.Graph;

public class BreadthFirstSearch {
	private boolean[] marked;
	private int [] edgeTo; //����㵽һ�������һֱ·���ϵ����һ������
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
	//��ͨ·������һ�������·����ֵΪ-1Ϊ���ɵ���ĵ�
	public Stack<Integer> pathTo(int v) {
		if(v==source) {
			System.out.println("�����յ�һ��!!!");
			return null;
		}else if (edgeTo[v]==-1) {
			System.out.println("���ܴ�"+source+"����"+v+"!!!");
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
