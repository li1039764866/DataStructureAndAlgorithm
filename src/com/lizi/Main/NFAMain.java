package com.lizi.Main;

import com.lizi.algorithm.digraph.NFA;

public class NFAMain {
	public static void main(String[] args) {
		String regexp="((A*B|AC)D)";
		NFA nfa=new NFA(regexp);
		System.out.println(nfa.recognizes("ACD"));
	}
}
