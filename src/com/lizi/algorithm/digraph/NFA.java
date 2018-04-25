package com.lizi.algorithm.digraph;

import com.lizi.datastructure.Bag;
import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.Digraph;
//从正则表达式到NFA的算法
public class NFA {
	private char[] re;//以(开始)结束的正则表达式字符数组
	private Digraph digraph;//NFA的有向图表示，所有边都是∑
	private int M;//字符数组的大小
	public NFA(String regexp)//regexp为正则表达式
	{
		Stack<Integer> ops=new Stack<Integer>();//存储运算符的栈
		re = regexp.toCharArray();
		M = re.length;
		digraph = new Digraph(M+1);//M为终态
		
		for (int i = 0; i < M; i++) {
			int lp = i;
			if (re[i] == '(' || re[i] == '|') {//运算符入栈
				ops.push(i);
			}else if(re[i] == ')'){//取出配对的运算符
				int or=ops.pop();
				if(re[or] == '|'){//或操作符对应顶点的连接
					lp =ops.pop();
					digraph.addEdge(lp,or+1);
					digraph.addEdge(or, i);
				}else lp=or;
			}
			if(i <M-1&&re[i+1] == '*'){//闭包运算符连接
				digraph.addEdge(lp, i+1);
				digraph.addEdge(i+1, lp);
			}
			if(re[i] == '(' ||re[i] == '*'||re[i] == ')'){//运算符后默认连接
				digraph.addEdge(i, i+1);
			}
		}
		
	}
	public boolean recognizes(String txt){//利用构建的NFA识别字符串txt
		Bag<Integer> pc=new Bag<Integer>();//存储当前状态到达的模式
		DirectedDFS dfs=new DirectedDFS(digraph, 0);//对有向图进行深度优先搜索，从0出发
		for (int v = 0; v < digraph.V(); v++) {//将0能够到达的所有点存入PC边(包含0)，类似于对0进行∑闭包运算
			if(dfs.marked(v)) pc.add(v);
		}
		
		for (int i = 0; i < txt.length(); i++) {
			Bag<Integer> match = new Bag<Integer>();
			for(int v : pc){
				if(v < M)
					if(re[v] == txt.charAt(i) || re[v] == '.')//字符匹配则跳转到下一状态
						match.add(v+1);
			}
			pc = new Bag<Integer>();
			dfs = new DirectedDFS(digraph, match);//对有向图进行深度优先搜索，从包内任一点可以达到的点
			for (int v = 0; v < digraph.V(); v++) {//源点为包内所有点，对这些点做闭包运算
				if(dfs.marked(v)) pc.add(v);
			}
		}
		for(int v : pc){//如果对状态转换完成后，最后包含终态，则匹配成功
			if (v == M) return true;//M为终态
		}
		return false;
	}
	
}
