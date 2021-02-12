package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape implements Movable, Prototype {

	private static final long serialVersionUID=1L;
	private Point startPoint;
	private Point endPoint;
	
	public Line () {
		
	}
	public Line (Point sp, Point ep) {
		this.startPoint=sp;
		this.endPoint=ep;
	}
	public Line(Point sp, Point ep, Color color) {
		this(sp,ep);
		this.setColor(color);
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint=startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint=endPoint;
	}
	public double length() {
		return startPoint.distance(endPoint);
	}
	public Point middlePoint() {
		int sx=(startPoint.getX()+endPoint.getX())/2;
		int sy=(startPoint.getY()+endPoint.getY())/2;
		return new Point(sx, sy);
	}
	
	@Override
	public String toString() {
		return String.format("Line:(startX=%d,startY=%d,endX=%d,endY=%d,color=[%d-%d-%d],selected=%b)", this.getStartPoint().getX(),
				this.getStartPoint().getY(), this.getEndPoint().getX(), this.getEndPoint().getY(), 
				this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue(), this.isSelected());
	}
	
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Line) {
			Line l=(Line) o;
			return (int) (this.length()-l.length());
		}
		else {
			return 0;
		}
		
	}

	@Override
	public void moveTo(int x, int y) {
		int rx=x-startPoint.getX();
		int ry=y-startPoint.getY();
		startPoint.moveTo(x, y);
		endPoint.moveFor(rx, ry);
	}

	@Override
	public void moveFor(int x, int y) {
		this.startPoint.moveFor(x, y);
		this.endPoint.moveFor(x, y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if(this.isSelected()) {
			this.selected(g);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		Point p=new Point(x,y);
		double ds=startPoint.distance(p);
		double de=endPoint.distance(p);
		if(ds + de <= this.length() + 0.05) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public boolean equals(Shape s) {
		if(s instanceof Line) {
			Line l=(Line) s;
			if(startPoint.equals(l.getStartPoint()) && endPoint.equals(l.getEndPoint()) && this.getColor().equals(l.getColor())) {
				return true;
			}
			else return false;
		}
		else return false;
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		startPoint.selected(g);
		endPoint.selected(g);
		this.middlePoint().selected(g);
	}

	@Override
	public Shape clone() {
		Line l= new Line(this.getStartPoint(), this.getEndPoint(), this.getColor()) ;
		l.setSelected(this.isSelected());
		return l;
	}
	

}
