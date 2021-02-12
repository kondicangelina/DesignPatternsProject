package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape implements Movable, Prototype {

	private static final long serialVersionUID=1L;
	private Point upperLeftPoint;
	private int height;
	private int width;
	
	public Rectangle() {
		
	}
	public Rectangle(Point p, int w,int h) {
		this.upperLeftPoint=p;
		this.width=w;
		this.height=h;
	}
	public Rectangle(Point p, int w, int h, Color c) {
		this(p,w,h);
		this.setColor(c);
	}
	public Rectangle(Point p, int w, int h, Color col, Color icol) {
		this(p,w,h);
		this.setColor(col);
		this.setInnerColor(icol);
	}
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint=upperLeftPoint;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height=height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width=width;
	}
	public Line diagonal() {
		int rx=this.getUpperLeftPoint().getX()+this.getWidth();
		int ry=this.getUpperLeftPoint().getY()+this.getHeight();
		Point upperRight=new Point(rx, this.getUpperLeftPoint().getY());
		Point downLeft=new Point(this.getUpperLeftPoint().getX(),ry);
		return new Line(upperRight,downLeft);
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Rectangle) {
			Rectangle rec= (Rectangle) o;
			return (int)(this.surface()-rec.surface());
		}
		return 0;
	}

	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
	}

	@Override
	public void moveFor(int x, int y) {
		upperLeftPoint.moveFor(x, y);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(this.getInnerColor());
		g.fillRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		if(this.isSelected()) this.selected(g);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		if(this.isSelected()) this.selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		if(upperLeftPoint.getX() <= x && x <= (upperLeftPoint.getX() + width) 
				&& upperLeftPoint.getY() <= y && y <=(upperLeftPoint.getY() + height)) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public boolean equals(Shape s) {
		if(s instanceof Rectangle) {
			Rectangle rec=(Rectangle) s;
			if(upperLeftPoint.equals(rec.getUpperLeftPoint()) && width==rec.getWidth() && height==rec.getHeight()
					&& this.getColor().equals(rec.getColor())
					&& this.getInnerColor().equals(rec.getInnerColor())) {
				return true;
			}
			else return false;
		}
		else return false;
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		Point downRight=new Point(getUpperLeftPoint().getX()+getWidth(), getUpperLeftPoint().getY()+getHeight());
		
		new Line (upperLeftPoint, this.diagonal().getEndPoint()).selected(g);
		new Line (this.diagonal().getEndPoint(), downRight).selected(g);
		new Line (downRight, this.diagonal().getStartPoint()).selected(g);
		new Line (this.diagonal().getStartPoint(), upperLeftPoint).selected(g);
	}

	@Override
	public Shape clone() {
		Rectangle rec= new Rectangle(this.getUpperLeftPoint(),this.getWidth(),this.getHeight(),this.getColor(), this.getInnerColor());
		rec.setSelected(this.isSelected());
		return rec;
	}
	@Override
	public double scope() {
		return 2*(this.getHeight()+this.getWidth());
	}
	@Override
	public double surface() {
		return this.getHeight()*this.getWidth();
	}
	@Override
	public String toString() {
		return String.format(
				"Rectangle:(UpperX=%d,UpperY=%d,width=%d,height=%d,outercolor=[%d-%d-%d],innercolor=[%d-%d-%d],selected=%b)",
				this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.width,this.height, getColor().getRed(), getColor().getGreen(),
				getColor().getBlue(), getInnerColor().getRed(), getInnerColor().getGreen(), getInnerColor().getBlue(),isSelected());
	}

}
