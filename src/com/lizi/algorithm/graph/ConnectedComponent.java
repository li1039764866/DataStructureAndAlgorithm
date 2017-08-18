package com.lizi.algorithm.graph;

import com.lizi.datastructure.graph.Graph;

public class ConnectedComponent {
	private boolean[] marked;//��¼�����Ƿ񱻱�ǹ�
	private int[] id;//����ͬһ����ͨ�����еĶ���ֵ��ͬ
	private int count=0;//��¼��ͨ����������
	public ConnectedComponent(Graph graph) {
		marked=new boolean[graph.V()];
		id=new int[graph.V()];
		for (int i = 0; i < graph.V(); i++) {
			if(!marked[i]){//��0��ʼ������һ�ξͽ��õ����ܵ���Ķ���ȫ�����
				DFSByRecursive(graph, i);
				count++;
			}
		}
		
	}
	private void DFSByRecursive(Graph graph,int v){
		marked[v]=true;//�õ��ѱ�����
		id[v]=count;//�Զ���Ϊ�±���ͨ�Ķ���ֵ��һ��
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
		System.out.println("ͼ�й���"+count+"������!!!");
		for (int i = 0; i <count ; i++) {
			for (int j = 0; j < id.length; j++) {
				if(id[j]==i)
					System.out.print(j+"  ");
			}
			System.out.println();
		}
	}
}
