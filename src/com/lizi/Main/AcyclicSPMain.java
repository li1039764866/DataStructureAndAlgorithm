package com.lizi.Main;

import com.lizi.algorithm.digraph.AcyclicSP;
import com.lizi.datastructure.graph.DirectedEdge;
import com.lizi.datastructure.graph.EdgeWeightedDigraph;

public class AcyclicSPMain {
	public static void main(String[] args) {
		EdgeWeightedDigraph digraph=new EdgeWeightedDigraph(8);
		digraph.addEdge(new DirectedEdge(5, 4, 0.35));
		digraph.addEdge(new DirectedEdge(4, 7, 0.37));
		digraph.addEdge(new DirectedEdge(5, 7, 0.28));
		digraph.addEdge(new DirectedEdge(5, 1, 0.32));
		digraph.addEdge(new DirectedEdge(4, 0, 0.38));
		digraph.addEdge(new DirectedEdge(0, 2, 0.26));
		digraph.addEdge(new DirectedEdge(3, 7, 0.39));
		digraph.addEdge(new DirectedEdge(1, 3, 0.29));
		digraph.addEdge(new DirectedEdge(7, 2, 0.34));
		digraph.addEdge(new DirectedEdge(6, 2, 0.40));
		digraph.addEdge(new DirectedEdge(3, 6, 0.52));
		digraph.addEdge(new DirectedEdge(6, 0, 0.58));
		digraph.addEdge(new DirectedEdge(6, 4, 0.93));
		AcyclicSP acyclicSP=new AcyclicSP(digraph, 5);
		System.out.println(acyclicSP.distTo(6));
		for (DirectedEdge edge : acyclicSP.pathTo(6)) {
			System.out.println(edge.toString());
		}
	}
}
