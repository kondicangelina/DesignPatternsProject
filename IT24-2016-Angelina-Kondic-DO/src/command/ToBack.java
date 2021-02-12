package command;

import java.util.Collections;

import mvc.DrawingModel;
import shapes.Shape;

public class ToBack implements Command {
	private DrawingModel model;
	private Shape shape;
	private int oldIndex;
	public ToBack(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
		this.oldIndex=model.getShapes().indexOf(shape);
	}
	@Override
	public void execute() {
		if(this.oldIndex>0) {
			Collections.swap(model.getShapes(), this.oldIndex, this.oldIndex-1);
		}
	}
	@Override
	public void unexecute() {
		if(this.oldIndex>0) {
			Collections.swap(model.getShapes(), this.oldIndex-1, this.oldIndex);
		}
	}
	@Override
	public String toString() {
		return "Moved to back:"+shape.toString();
	}

}
