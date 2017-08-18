package com.lizi.Main;

import java.io.IOException;

import com.lizi.algorithm.graph.DepthFirstSearch;
import com.lizi.datastructure.Stack;
import com.lizi.datastructure.graph.Graph;
public class DepthFirstSearchMain {

	public static void main(String[] args) throws IOException {
		Graph graph=new Graph(9);
		graph.addEdge(0, 2);
		graph.addEdge(0, 1);
		graph.addEdge(0, 5);
		graph.addEdge(2, 4);
		graph.addEdge(3, 2);
		graph.addEdge(3, 4);
		graph.addEdge(5, 3);
		graph.addEdge(2, 6);
		graph.addEdge(1, 7);
		graph.addEdge(1, 2);
		graph.print();
		DepthFirstSearch dfs=new DepthFirstSearch(graph, 0);
		System.out.println("Â·¾¶£º£º£º");
		Stack<Integer> stack=dfs.pathTo(6);
		
		while (!stack.isEmpty()) {
			System.out.print(stack.pop()+"-->");
			
		}
	}

}

