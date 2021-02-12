package command;

import java.util.Collections;

import mvc.DrawingModel;
import shapes.Shape;

public class ToFront implements Command {

	private DrawingModel model;
	private Shape shape;
	private int oldIndex;
	
	public ToFront(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
		this.oldIndex=model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		if(this.oldIndex < model.getShapes().size()-1) {
			Collections.swap(model.getShapes(), this.oldIndex, this.oldIndex+1);
		}
	}

	@Override
	public void unexecute() {
		if(this.oldIndex < model.getShapes().size()-1) {
			Collections.swap(model.getShapes(), this.oldIndex+1, this.oldIndex);
		}
	}
	@Override
	public String toString() {
		return "Moved to front:"+shape.toString();
	}
	
}
