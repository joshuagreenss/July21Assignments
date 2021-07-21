package com.smoothstack.javabasics.day2.assignment3;

import javax.swing.JFrame;

public class Triangle implements Shape {
	private int[] xs;
	private int[] ys;
	
	public Triangle(int[] x, int[] y) {
		xs = new int[3];
		ys = new int[3];
		xs = x;
		ys = y;
	}
	
	public double calculateArea() {
		return Math.abs((xs[0]*ys[1]+xs[1]*ys[2]+xs[2]*ys[0]-xs[1]*ys[0]-xs[2]*ys[1]-xs[0]*ys[2])/2.0);
	}
	
	public void display() {
		JFrame frame = new JFrame("Triangle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TriPanel shape = new TriPanel(xs,ys);
		frame.add(shape);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * 
	 * @param Arguments are of form x1 y1 x2 y2 x3 y3
	 */
	public static void main(String[] args) {
		// Builds triangle from three points in command line
		int[] xs = {Integer.valueOf(args[0]),Integer.valueOf(args[2]),Integer.valueOf(args[4])};
		int[] ys = {Integer.valueOf(args[1]),Integer.valueOf(args[3]),Integer.valueOf(args[5])};
		Triangle t = new Triangle(xs,ys);
		System.out.println("Area of given triangle is "+t.calculateArea());
		t.display();
	}
}
