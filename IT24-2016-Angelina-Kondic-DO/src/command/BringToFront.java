package command;

import java.util.Collections;

import mvc.DrawingModel;
import shapes.Shape;

public class BringToFront implements Command {

	private DrawingModel model;
	private Shape shape;
	private int oldIndex;
	
	public BringToFront(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
		this.oldIndex=model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		if(this.oldIndex < model.getShapes().size()-1) {
			Collections.swap(model.getShapes(), this.oldIndex, model.getShapes().size()-1);
		}
	}

	@Override
	public void unexecute() {
		if(this.oldIndex < model.getShapes().size()-1) {
			Collections.swap(model.getShapes(), model.getShapes().size()-1, this.oldIndex);
		}
	}
	@Override
	public String toString() {
		return "Bringed to front:"+shape.toString();
	}
}
