package com.lizi.tool;

//��ʱ������¼����
public class Stopwatch {
	private final long start;
	public Stopwatch() {
		start=System.currentTimeMillis();
	}
	public void elapsedTime() {
		long now=System.currentTimeMillis();
		double count= (now-start)/1000.0;
		System.out.println("��ǰʵ�黨����"+count+"�룡����");
	}
}
