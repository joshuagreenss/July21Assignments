package com.smoothstack.javabasics.day2.assignment3;

import javax.swing.JFrame;

public class Circle implements Shape{
	private int r, cx, cy;
	
	public Circle(int r, int cx, int cy) {
		this.r = r;
		this.cx = cx;
		this.cy = cy;
	}
	
	public double calculateArea() {
		return r*r*Math.PI;
	}
	
	public void display() {
		JFrame frame = new JFrame("Triangle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CircPanel shape = new CircPanel(r,cx-r,cy-r);
		frame.add(shape);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		try {
			Circle c = new Circle(Integer.valueOf(args[0]),Integer.valueOf(args[1]),Integer.valueOf(args[2]));
			System.out.println("Area of the given circle is " + c.calculateArea());
			c.display();
		} catch(NumberFormatException e){
			System.out.println("At least one input not an integer");
			return;
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Not enough inputs");
			return;
		}
	}
}
