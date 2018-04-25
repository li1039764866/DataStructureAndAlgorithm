package com.lizi.algorithm.graph;

import com.lizi.algorithm.digraph.DirectedDFS;
import com.lizi.datastructure.graph.Digraph;
//�ɴ��Լ�⣬������������ڶ�ά����
public class TransitiveClosure {
	private DirectedDFS[] all;//��ά���飬�洢ÿ�����㵽��Ķ����Ƿ�ɴ�
	public TransitiveClosure(Digraph digraph) {
		all=new DirectedDFS[digraph.V()];
		for (int i = 0; i < digraph.V(); i++) {//ÿ�����㶼����һ���������
			all[i]=new DirectedDFS(digraph, i);
		}
	}
	boolean reachable(int v,int w){
		return all[v].marked(w);
	}
}
