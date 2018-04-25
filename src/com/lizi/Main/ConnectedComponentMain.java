package com.lizi.Main;

import com.lizi.algorithm.graph.ConnectedComponent;
import com.lizi.datastructure.graph.Graph;

public class ConnectedComponentMain {

	public static void main(String[] args) {
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
		ConnectedComponent connectedComponent=new ConnectedComponent(graph);
		connectedComponent.print();

	}

}
