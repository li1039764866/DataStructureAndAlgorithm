package com.lizi.algorithm.graph;

import com.lizi.datastructure.Queue;
import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.Digraph;

public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre;//ǰ�����
	private Queue<Integer> post;//�������
	private Stack<Integer> reversePost;//��������
	public DepthFirstOrder(Digraph digraph) {
		pre=new Queue<Integer>();
		post=new Queue<Integer>();
		reversePost=new Stack<Integer>();
		marked=new boolean[digraph.V()];
		for (int i = 0; i < digraph.V(); i++) {
			if(!marked[i]) DFS(digraph,i);
		}
	}
	private void DFS(Digraph digraph,int v) {//ͬ�ʼ����ȱ���һ����ֻ�ǽ������Ķ��㰴�ղ�ͬ˳��洢����
		pre.enqueue(v);
		marked[v]=true;
		for (int w : digraph.adjacent(v)) {
			if(!marked[w]) DFS(digraph,w);
		}
		post.enqueue(v);
		reversePost.push(v);
	}
	public Iterable<Integer> pre(){
		return pre;
	}
	public Iterable<Integer> post(){
		return post;
	}
	public Iterable<Integer> reversePost(){
		return reversePost;
	}
}
