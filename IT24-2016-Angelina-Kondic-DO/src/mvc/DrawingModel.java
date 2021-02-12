package mvc;

import java.io.Serializable;
import java.util.ArrayList;

import shapes.Shape;

public class DrawingModel implements Serializable {

	private static final long serialVersionUID=1L;
	private ArrayList<Shape> shapes=new ArrayList<Shape>();
	
	public ArrayList<Shape> getShapes(){
		return this.shapes;
	}
	public Shape getOne(int index) {
		return shapes.get(index);
	}
	public void addShape(Shape shape) {
		shapes.add(shape);
	}
	public void addShapes(ArrayList<Shape> shapesForDelete) {
		shapes.addAll(shapesForDelete);
	}
	public void deleteShape(Shape shape) {
		shapes.remove(shape);
	}
	public void deleteShapes(ArrayList<Shape> shapesForDelete) {
		shapes.removeAll(shapesForDelete);
	}
	public int getIndexOfShape(Shape shape) {
		return shapes.indexOf(shape);
	}
	
	public void addShapeToIndex(int index, Shape shape) {
		shapes.add(index, shape);
	}
}
