package command;

import java.util.Collections;

import mvc.DrawingModel;
import shapes.Shape;

public class BringToBack implements Command{

	private DrawingModel model;
	private Shape shape;
	private int oldIndex;
	
	public BringToBack(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
		this.oldIndex=model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		if(this.oldIndex>0) {
			Collections.swap(model.getShapes(), this.oldIndex, 0);
		}
	}

	@Override
	public void unexecute() {
		if(this.oldIndex>0) {
			Collections.swap(model.getShapes(), 0, this.oldIndex);
		}
	}
	@Override
	public String toString() {
		return "Bringed to back:"+shape.toString();
	}
}
