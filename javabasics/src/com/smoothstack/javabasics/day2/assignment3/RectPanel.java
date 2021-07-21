package com.smoothstack.javabasics.day2.assignment3;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RectPanel extends JPanel {
	private int h, w;
	public RectPanel(int h, int w) {
		this.h = h;
		this.w = w;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int[] xs = new int[4];
		int[] ys = new int[4];
		xs[0] = 500-h/2;
		xs[1] = 500+h/2;
		xs[2] = 500+h/2;
		xs[3] = 500-h/2;
		ys[0] = 500-w/2;
		ys[1] = 500-w/2;
		ys[2] = 500+w/2;
		ys[3] = 500+w/2;
		g.drawPolygon(xs, ys, 4);
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1000,1000);
	}
}
