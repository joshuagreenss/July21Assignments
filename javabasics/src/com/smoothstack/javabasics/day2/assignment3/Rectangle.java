/**
 * 
 */
package com.smoothstack.javabasics.day2.assignment3;
import javax.swing.JFrame;

/**
 * @author Joshua Green
 *
 */
public class Rectangle implements Shape {

	/**
	 * @param args
	 */
	public Rectangle(int s1, int s2) {
		w = s1;
		h = s2;
	}
	
	private int h;
	private int w;
	
	public double calculateArea() {
		return h * w;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void display() {
		JFrame frame = new JFrame("Rectangle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RectPanel shape = new RectPanel(w,h);
		frame.add(shape);
		
		frame.pack();
		frame.setVisible(true);
	}
	/**
	 * 
	 * @param First argument height of rectangle, second width
	 */
	public static void main(String[] args) {
		// Builds rectangle from command line inputs
		try {
			Rectangle r = new Rectangle(Integer.valueOf(args[0]),Integer.valueOf(args[1]));
			System.out.println("The area of the rectangle is " + r.calculateArea());
			r.display();
		} catch(NumberFormatException e){
			System.out.println("At least one input is not an integer");
			return;
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Not enough inputs");
			return;
		}
	}
}
