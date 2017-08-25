package com.lizi.tool;

public class General<T> {
	public static void swap(double []array,int i,int j) {
		double middle=array[i];
		array[i]=array[j];
		array[j]=middle;
	}
	public static void swap(int[]array,int i,int j) {
		int middle=array[i];
		array[i]=array[j];
		array[j]=middle;
	}
	public static void swap(String[]array,int i,int j) {
		String middle=array[i];
		array[i]=array[j];
		array[j]=middle;
	}
	
}
