package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape implements Prototype, Movable {

	
	private static final long serialVersionUID=1L;
	private Point centerPoint;
	private int radius;
	
	public Circle() {
		
	}
	public Circle(Point center, int radius) {
		this.centerPoint=center;
		this.radius=radius;
	}
	public Circle(Point center, int radius, Color color) {
		this(center,radius);
		setColor(color);
	}
	public Circle(Point center, int radius, Color color, Color innercolor) {
		this(center,radius);
		setColor(color);
		setInnerColor(innercolor);
	}
	public Point getCenterPoint() {
		return centerPoint;
	}
	public void setCenterPoint(Point center) {
		this.centerPoint=center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius=radius;
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Circle) {
			Circle c= (Circle) o;
			return this.radius-c.radius;
		}
		else {
			return 0;
		}
		
	}

	@Override
	public void moveTo(int x, int y) {
		this.centerPoint.moveTo(x, y);
	}

	@Override
	public void moveFor(int x, int y) {
		this.centerPoint.moveFor(x, y);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(this.getInnerColor());
		g.fillOval(centerPoint.getX()-radius, centerPoint.getY()-radius, 2*radius, 2*radius);
		if(this.isSelected()) this.selected(g);
	}

	@Override
	public double scope() {
		return 2*radius*Math.PI;
	}

	@Override
	public double surface() {
		return radius*radius*Math.PI;
	}

	@Override
	public void draw(Graphics g) {
		this.fill(g);
		g.setColor(this.getColor());
		g.drawOval(centerPoint.getX()-radius, centerPoint.getY()-radius, 2*radius, 2*radius);
		if(this.isSelected()) this.selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		Point p=new Point(x,y);
		if(centerPoint.distance(p)<=radius) return true;
		else return false;
	}

	@Override
	public boolean equals(Shape s) {
		if(s instanceof Circle) {
			Circle c= (Circle) s;
			if(centerPoint.equals(c.getCenterPoint()) && radius==c.radius
					&& this.getColor().equals(c.getColor()) && this.getInnerColor().equals(c.getInnerColor())) {
				return true;
			}
			else return false;
		}
		else return false;
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		centerPoint.selected(g);
		new Point(centerPoint.getX()+radius, centerPoint.getY()).selected(g);
		new Point(centerPoint.getX(),centerPoint.getY()+radius).selected(g);
		new Point(centerPoint.getX()-radius, centerPoint.getY()).selected(g);
		new Point(centerPoint.getX(),centerPoint.getY()-radius).selected(g);
	}

	@Override
	public Shape clone() {
		Circle c=new Circle(this.getCenterPoint(), this.getRadius(), this.getColor(), this.getInnerColor());
		c.setSelected(this.isSelected());
		return c;
	}
	@Override
	public String toString() {
		return String.format("Circle:(X=%d,Y=%d,r=%d,outercolor=[%d-%d-%d],innercolor=[%d-%d-%d],selected=%b)", this.getCenterPoint().getX(),
				this.getCenterPoint().getY(), this.radius, getColor().getRed(), getColor().getGreen(), getColor().getBlue(),
				getInnerColor().getRed(), getInnerColor().getGreen(), getInnerColor().getBlue(),isSelected());
	}

}
