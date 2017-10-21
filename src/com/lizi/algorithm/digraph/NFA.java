package com.lizi.algorithm.digraph;

import com.lizi.datastructure.Bag;
import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.Digraph;
//��������ʽ��NFA���㷨
public class NFA {
	private char[] re;//��(��ʼ)������������ʽ�ַ�����
	private Digraph digraph;//NFA������ͼ��ʾ�����б߶��ǡ�
	private int M;//�ַ�����Ĵ�С
	public NFA(String regexp)//regexpΪ������ʽ
	{
		Stack<Integer> ops=new Stack<Integer>();//�洢�������ջ
		re = regexp.toCharArray();
		M = re.length;
		digraph = new Digraph(M+1);//MΪ��̬
		
		for (int i = 0; i < M; i++) {
			int lp = i;
			if (re[i] == '(' || re[i] == '|') {//�������ջ
				ops.push(i);
			}else if(re[i] == ')'){//ȡ����Ե������
				int or=ops.pop();
				if(re[or] == '|'){//���������Ӧ���������
					lp =ops.pop();
					digraph.addEdge(lp,or+1);
					digraph.addEdge(or, i);
				}else lp=or;
			}
			if(i <M-1&&re[i+1] == '*'){//�հ����������
				digraph.addEdge(lp, i+1);
				digraph.addEdge(i+1, lp);
			}
			if(re[i] == '(' ||re[i] == '*'||re[i] == ')'){//�������Ĭ������
				digraph.addEdge(i, i+1);
			}
		}
		
	}
	public boolean recognizes(String txt){//���ù�����NFAʶ���ַ���txt
		Bag<Integer> pc=new Bag<Integer>();//�洢��ǰ״̬�����ģʽ
		DirectedDFS dfs=new DirectedDFS(digraph, 0);//������ͼ�������������������0����
		for (int v = 0; v < digraph.V(); v++) {//��0�ܹ���������е����PC��(����0)�������ڶ�0���СƱհ�����
			if(dfs.marked(v)) pc.add(v);
		}
		
		for (int i = 0; i < txt.length(); i++) {
			Bag<Integer> match = new Bag<Integer>();
			for(int v : pc){
				if(v < M)
					if(re[v] == txt.charAt(i) || re[v] == '.')//�ַ�ƥ������ת����һ״̬
						match.add(v+1);
			}
			pc = new Bag<Integer>();
			dfs = new DirectedDFS(digraph, match);//������ͼ������������������Ӱ�����һ����Դﵽ�ĵ�
			for (int v = 0; v < digraph.V(); v++) {//Դ��Ϊ�������е㣬����Щ�����հ�����
				if(dfs.marked(v)) pc.add(v);
			}
		}
		for(int v : pc){//�����״̬ת����ɺ���������̬����ƥ��ɹ�
			if (v == M) return true;//MΪ��̬
		}
		return false;
	}
	
}
