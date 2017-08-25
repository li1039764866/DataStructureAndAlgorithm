package com.lizi.Main;

import com.lizi.algorithm.digraph.KruskalMST;
import com.lizi.datastructure.graph.Edge;
import com.lizi.datastructure.graph.EdgeWeightedGraph;

public class KruskalMSTMain {

	public static void main(String[] args) {
		EdgeWeightedGraph edgeWeightedGraph=new EdgeWeightedGraph(8);
		edgeWeightedGraph.addEgde(new Edge(0, 7, 0.16));
		edgeWeightedGraph.addEgde(new Edge(2, 3, 0.17));
		edgeWeightedGraph.addEgde(new Edge(1, 7, 0.19));
		edgeWeightedGraph.addEgde(new Edge(0, 2, 0.26));
		edgeWeightedGraph.addEgde(new Edge(5, 7, 0.28));
		edgeWeightedGraph.addEgde(new Edge(1, 3, 0.29));
		edgeWeightedGraph.addEgde(new Edge(1, 5, 0.32));
		edgeWeightedGraph.addEgde(new Edge(2, 7, 0.34));
		edgeWeightedGraph.addEgde(new Edge(4, 5, 0.35));
		edgeWeightedGraph.addEgde(new Edge(1, 2, 0.36));
		edgeWeightedGraph.addEgde(new Edge(4, 7, 0.37));
		edgeWeightedGraph.addEgde(new Edge(0, 4, 0.38));
		edgeWeightedGraph.addEgde(new Edge(6, 2, 0.40));
		edgeWeightedGraph.addEgde(new Edge(3, 6, 0.52));
		edgeWeightedGraph.addEgde(new Edge(6, 0, 0.58));
		edgeWeightedGraph.addEgde(new Edge(6, 4, 0.93));
		KruskalMST kruskalMST=new KruskalMST(edgeWeightedGraph);
		for (Edge e : kruskalMST.edges()) {
			System.out.println(e.toString());
		}
		System.out.println(kruskalMST.weight());
	}

}
