package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends SurfaceShape implements Movable, Prototype {

	private static final long serialVersionUID=1L;
	private Point upperLeftPoint;
	private int width;
	
	public Square() {
		
	}
	public Square(Point p, int w) {
		this.upperLeftPoint=p;
		this.width=w;
	}
	public Square(Point p, int w, Color col) {
		this(p,w);
		setColor(col);
	}
	public Square(Point p, int w, Color col, Color inncol) {
		this(p,w);
		setColor(col);
		setInnerColor(inncol);
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point leftPoint) {
		this.upperLeftPoint=leftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width=width;
	}
	public Line diagonal() {
		int rx=this.getUpperLeftPoint().getX()+width;
		int ry= this.getUpperLeftPoint().getY()+width;
		Point upperRight=new Point(rx, upperLeftPoint.getY());
		Point downLeft=new Point(upperLeftPoint.getX(), ry);
		return new Line(upperRight, downLeft);
	}
	public Point center() {
		return this.diagonal().middlePoint();
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Square) {
			Square sq=(Square) o;
			return (int) (this.surface()- sq.surface());
		}
		else {
			return 0;
		}
		
	}

	@Override
	public void moveTo(int x, int y) {
		this.upperLeftPoint.moveTo(x, y);
	}

	@Override
	public void moveFor(int x, int y) {
		this.upperLeftPoint.moveFor(x, y);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX(), this.getUpperLeftPoint().getY(), width, width);
		if(this.isSelected()) this.selected(g);
	}

	@Override
	public double scope() {
		return 4*width;
	}

	@Override
	public double surface() {
		return width*width;
	}

	@Override
	public void draw(Graphics g) {
		this.fill(g);
		g.setColor(this.getColor());
		g.drawRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), width, width);
		if(this.isSelected()) this.selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		if(upperLeftPoint.getX() <= x && x <= (upperLeftPoint.getX()+width) 
				&& upperLeftPoint.getY() <= y && y <= (upperLeftPoint.getY()+width)) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public boolean equals(Shape s) {
		if(s instanceof Square) {
			Square k=(Square) s;
			if(upperLeftPoint.equals(k.getUpperLeftPoint()) &&
					width==k.getWidth() && this.getColor().equals(k.getColor()) && this.getInnerColor().equals(k.getInnerColor())) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	@Override
	public String toString() {
		return String.format("Square:(UpperX=%d,UpperY=%d,a=%d,outercolor=[%d-%d-%d],innercolor=[%d-%d-%d],selected=%b)",
				this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.width, getColor().getRed(), getColor().getGreen(),
				getColor().getBlue(), getInnerColor().getRed(), getInnerColor().getGreen(), getInnerColor().getBlue(),isSelected());
	}
	
	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		Point downRight= new Point(upperLeftPoint.getX()+width, upperLeftPoint.getY()+width);
		
		new Line(upperLeftPoint, this.diagonal().getEndPoint()).selected(g);
		new Line(this.diagonal().getEndPoint(), downRight).selected(g);
		new Line(downRight, this.diagonal().getStartPoint()).selected(g);
		new Line(this.diagonal().getStartPoint(), upperLeftPoint).selected(g);
	}

	@Override
	public Shape clone() {
		Square sq= new Square(this.getUpperLeftPoint(), this.getWidth(), this.getColor(), this.getInnerColor());
		sq.setSelected(this.isSelected());
		return sq;
	}
	

}
