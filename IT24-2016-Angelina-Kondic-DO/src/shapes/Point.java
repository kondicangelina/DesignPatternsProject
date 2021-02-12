package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape implements Movable, Prototype {

	private static final long serialVersionUID=1L;
	private int x;
	private int y;
	
	public Point() {
		
	}
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public Point(int x, int y, Color color) {
		this.x=x;
		this.y=y;
		this.setColor(color);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x=x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y=y;
	}
	
	
	public double distance(Point p) {
		int rx=this.getX()-p.getX();
		int ry=this.getY()-p.getY();
		return (double) Math.sqrt(rx*rx+ry*ry);
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Point) {
			Point p=(Point) o;
			return (int) (this.distance(new Point(0,0))-p.distance(new Point(0,0)));
		}
		else {
			return 0;
		}
		
	}

	@Override
	public void moveTo(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	@Override
	public void moveFor(int x, int y) {
		this.setX(this.getX() + x);
		this.setY(this.getY() + y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawLine(x-1, y-1, x+1, y+1);
		g.drawLine(x-1, y+1, x+1, y-1);
		if(this.isSelected()) {
			this.selected(g);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		Point p=new Point(x,y);
		if(this.distance(p)<=3) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public boolean equals(Shape s) {
		if(s instanceof Point) {
			Point p= (Point) s;
			if(this.getX()==p.getX() &&
					this.getY()==p.getY() &&
					this.getColor().equals(p.getColor())) return true;
			else return false;
		}
		else return false;
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x-1, y-1, 2, 2);
	}

	@Override
	public Shape clone() {
		Point p= new Point(this.getX(), this.getY(), this.getColor());
		p.setSelected(this.isSelected());
		return p;
	}
	@Override
	public String toString() {
		return String.format("Point:(x=%d,y=%d,color=[%d-%d-%d],selected=%b)", this.x, this.y, getColor().getRed(), getColor().getGreen(),
				getColor().getBlue(),isSelected()); 
	}
}
