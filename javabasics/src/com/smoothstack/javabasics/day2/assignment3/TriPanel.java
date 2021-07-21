/**
 * 
 */
package com.smoothstack.javabasics.day2.assignment3;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author joshu
 *
 */
public class TriPanel extends JPanel {
	private int[] xs, ys;
	
	TriPanel(int[] x, int[] y){
		xs = x;
		ys = y;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawPolygon(xs, ys, 3);
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1000,1000);
	}
}
