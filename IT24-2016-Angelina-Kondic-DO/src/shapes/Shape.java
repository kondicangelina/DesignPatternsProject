package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Comparable, Serializable, Prototype {

	private static final long serialVersionUID = 1L;
	private Color color=Color.BLACK;
	private boolean selected=false;
	
	public Shape() {
		
	}
	
	public Shape(Color c) {
		this.color=c;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color=color;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected=selected;
	}
	public abstract void draw(Graphics g);
	public abstract boolean contains(int x, int y);
	public abstract boolean equals(Shape s);
	public abstract void selected(Graphics g);
	public abstract Shape clone(); 
}
