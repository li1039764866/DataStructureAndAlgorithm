package com.lizi.algorithm.digraph;

import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.Digraph;
//����ͼ���ļ��
public class DirectedCycle {
	private boolean[] marked;//����Ƿ���ʹ��õ�
	private int[] edgeTo;//��¼����ö������һ����
	private Stack<Integer> cycle;//���滷�����Ķ���
	private boolean[]onStack;//���õ��Ƿ���ջ�У�ѹջΪtrue����ջ��Ϊfalse
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
			}else if(onStack[w]){//�õ��ѱ����ʹ��Ҳ�δ��ջ��˵���ǵ�ǰǰ·�Ѿ������ĵ㣬��ʱ����������Ϊ��
				cycle=new Stack<Integer>();//�洢����·��
				for (int x = v; x !=w; x=edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);//��βѹջ
				cycle.push(v);
			}
			onStack[v]=false;//ǰ��·�߻���ʱ�õ�ָ�Ϊδ��ջ״̬
		}
	}
	public boolean hasCycle(){
		return cycle!=null;
	}
	public Iterable<Integer> cycle() {
		return cycle;
	}
}
