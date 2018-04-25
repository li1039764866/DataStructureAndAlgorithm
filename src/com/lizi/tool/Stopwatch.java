package com.lizi.tool;

//计时器，记录秒数
public class Stopwatch {
	private final long start;
	public Stopwatch() {
		start=System.currentTimeMillis();
	}
	public void elapsedTime() {
		long now=System.currentTimeMillis();
		double count= (now-start)/1000.0;
		System.out.println("当前实验花费了"+count+"秒！！！");
	}
}
