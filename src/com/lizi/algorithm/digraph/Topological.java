package com.lizi.algorithm.digraph;

import com.lizi.algorithm.graph.DepthFirstOrder;
import com.lizi.datastructure.graph.Digraph;
//拓扑排序
public class Topological {
	private Iterable<Integer> order;
	public Topological(Digraph digraph) {
		DirectedCycle cycleFinder=new DirectedCycle(digraph);
		if(!cycleFinder.hasCycle()){
			DepthFirstOrder dfs=new DepthFirstOrder(digraph);
			order=dfs.reversePost();
		}
	}
	public Iterable<Integer> order(){
		return order;
	}
	public boolean isDAG() {//是否是有向无环图
		return order!=null;
	}
}
