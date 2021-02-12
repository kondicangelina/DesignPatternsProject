package adapter;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;
import shapes.Movable;
import shapes.Point;
import shapes.Prototype;
import shapes.Shape;
import shapes.SurfaceShape;

public class HexagonAdapter extends SurfaceShape implements Movable, Prototype{

	private static final long serialVersionUID=1L;
	private Hexagon hexagon;
	
	public HexagonAdapter(Hexagon hex) {
		this.hexagon=hex;
	}
	public HexagonAdapter(Hexagon hex, Color col, Color ic) {
		this(hex);
		hex.setBorderColor(col);
		hex.setAreaColor(ic);
	}
	public HexagonAdapter(Point startPoint, int radius) {
		this.hexagon=new Hexagon(startPoint.getX(), startPoint.getY(), radius);
	}
	
	public Hexagon getHexagon() {
		return hexagon;
	}
	public void setHexagon(Hexagon h) {
		this.hexagon=h;
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof HexagonAdapter) {
			HexagonAdapter hex=(HexagonAdapter)o;
			return hexagon.getR()-hex.getHexagon().getR();
		}
		else {
			return 0;
		}
		
	}

	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public void moveFor(int x, int y) {
		hexagon.setX(hexagon.getX()+x);
		hexagon.setY(hexagon.getY()+y);
	}

	@Override
	public void fill(Graphics g) {
		hexagon.setAreaColor(this.getInnerColor());
		if(this.isSelected()) this.selected(g);
	}

	@Override
	public double scope() {
		return 6*this.hexagon.getR();
	}

	@Override
	public double surface() {
		return ((3*Math.sqrt(3)/2)*this.hexagon.getR()*this.hexagon.getR());
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		hexagon.setSelected(isSelected());
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public boolean equals(Shape s) {
		if(s instanceof HexagonAdapter) {
			HexagonAdapter hex=(HexagonAdapter) s;
			if(hexagon.getX()==hex.getHexagon().getX() && hexagon.getY()==hex.getHexagon().getY() 
					&& hexagon.getR()==hex.getHexagon().getR() && this.getColor().equals(hex.getColor()) && this.getInnerColor().equals(hex.getInnerColor())) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	public boolean isSelected() {
		return hexagon.isSelected();
	}
	public void setSelected(boolean selected) {
		this.hexagon.setSelected(selected);
		super.setSelected(selected);
	}
	public String toString() {
		return String.format("Hexagon:(X=%d,Y=%d,r=%d,outercolor=[%d-%d-%d],innercolor=[%d-%d-%d],selected=%b)", this.getHexagon().getX(),
				this.getHexagon().getY(), this.getHexagon().getR(), getColor().getRed(), getColor().getGreen(), getColor().getBlue(),
				getInnerColor().getRed(), getInnerColor().getGreen(), getInnerColor().getBlue(),isSelected());	
		}
	
	@Override
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape clone() {
		Hexagon hex=new Hexagon(this.getHexagon().getX(), this.getHexagon().getY(), this.getHexagon().getR());
		hex.setBorderColor(getColor());
		hex.setAreaColor(getInnerColor());
		hex.setSelected(this.isSelected());
		return new HexagonAdapter(hex);
	}
	@Override
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}

	@Override
	public void setInnerColor(Color innerColor) {
		hexagon.setAreaColor(innerColor);
	}

	@Override
	public Color getColor() {
		return hexagon.getBorderColor();
	}

	@Override
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public void setY(int y) {
		hexagon.setY(y);
	}
	
	public void setR(int r) {
		hexagon.setR(r);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public int getR() {
		return hexagon.getR();
	}
	
}
