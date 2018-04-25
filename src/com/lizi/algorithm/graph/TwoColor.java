package com.lizi.algorithm.graph;

import com.lizi.datastructure.graph.Graph;
//����Ƿ��Ƕ���ͼ
public class TwoColor {
	private boolean[] marked;
	private boolean[] color;//��¼ÿ���������ɫ
	private boolean isTwoColorable=true;
	public TwoColor(Graph graph){
		marked=new boolean[graph.V()];
		color=new boolean[graph.V()];
		for (int i = 0; i < graph.V(); i++) {
			if(!marked[i])
				DFS(graph,i);
		}
	}
	//������������������ж���Χ����ǵĵ��Ƿ��뵱ǰ����ɫ������
	private void DFS(Graph graph,int v){//�漰�������㣺i,v;iΪ��ǰ��ĵ�
		marked[v]=true;//�õ��ѱ�����
		for (int i:graph.adjacent(v)) {
			if(!marked(i)){
				color[i]=!color[v];//ͬ��������Ϳ��ͬɫ
				DFS(graph,i);
			}else if(color[v]==color[i]){//�õ��ѱ���ǣ������ɫ�Ƿ���
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
