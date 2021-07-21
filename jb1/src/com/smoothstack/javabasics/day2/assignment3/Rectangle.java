/**
 * 
 */
package com.smoothstack.javabasics.day2.assignment3;
import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * @author Joshua Green
 *
 */
public class Rectangle implements Shape{
	public Rectangle(int s1, int s2) {
		this.h = s1;
		this.w = s2;
	}
	
	private int h;
	private int w;
	
	@Override
	public void display() {
		JFrame frame = new JFrame();
		Graphics g = frame.getGraphics();
		g.drawLine(0, 0, 0, h);
		g.drawLine(0, h, w, h);
		g.drawLine(w, h, w, 0);
		g.drawLine(w, 0, 0, 0);
		frame.setVisible(true);
	}

	@Override
	public int calculateArea() {
		return h * w;
	}
	
	static void main(String[] args) {
		Rectangle obj = new Rectangle(2,4);
		System.out.println("The area of the rectangle is " + obj.calculateArea());
		obj.display();
	}
}