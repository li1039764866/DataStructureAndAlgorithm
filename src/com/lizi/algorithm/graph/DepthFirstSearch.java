package com.lizi.algorithm.graph;


import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.Graph;

public class DepthFirstSearch {
	private boolean[] marked;
	private int [] edgeTo; //����㵽һ�������һֱ·���ϵ����һ������
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
	//�ӵ�һ�����㿪ʼ���ʣ��������ڶ�����е�һ�����ڵĶ��㣬Ȼ�������η�����һ����ǰ��Ķ��㣬ֱ���ص������ظ��ĵ㣬
	//��ʱ���˵��ݹ麯������һ�ε��ã�ѭ����һ�ζ������ڵĵڶ�������
	private void DFSByRecursive(Graph graph,int v){
		marked[v]=true;//�õ��ѱ�����
		for (int i:graph.adjacent(v)) {
			if(!marked(i)){
				edgeTo[i]=v;//����ǰ����һ�ڵ㣬���ýڵ㱣��
				DFSByRecursive(graph,i);
			}
		}
		
	}
	public void DFSByStack(Graph graph,int v) {
		Stack<Integer> stack=new Stack<Integer>();
		stack.push(v);//���Ԫ��ѹջ���Ա��˻����
		while (!stack.isEmpty()) {//getTop����Ϊ��ʵ�ֵ�Stack�ຯ��������ʹ��java.util.Stack�е�get(stack.size()-1)����
			int next=stack.getTop();//��ȡ��һ�����㣬��㿪ʼ
			if(!marked(next)){//���δ��ǣ����¼·����Ϣ
				edgeTo[next]=v;
				v=next;	
			}else {//����Ѿ���ǣ���Ӧ�û��˵���һ����
				v=stack.pop();	
			}
			marked[v]=true;
			for (int i :graph.adjacent(v)) {
				if(!marked(i)){//���û�б�ǣ����Ǹõ���Ե����û�е���ĵ�
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
	//��ͨ·������һ�������·��,edgeTo����ֵ���±���ͬΪ��㣬ֵΪ-1Ϊ���ɵ���ĵ�
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
