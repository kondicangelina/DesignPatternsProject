package command;

import mvc.DrawingModel;
import shapes.Shape;

public class AddShape implements Command{

	private DrawingModel model;
	private Shape shape;
	
	public AddShape(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
	}
	
	@Override
	public void execute() {
		model.addShape(shape);
	}

	@Override
	public void unexecute() {
		model.deleteShape(shape);
	}
	@Override
	public String toString() {
		return "Added:"+shape.toString();
	}

}
