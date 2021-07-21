package com.smoothstack.javabasics.day2.assignment3;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CircPanel extends JPanel {
	private int r, cx, cy;
	
	public CircPanel(int r, int cx, int cy) {
		this.r = r;
		this.cx = cx;
		this.cy = cy;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawOval(cx,cy,r,r);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1000,1000);
	}
}
