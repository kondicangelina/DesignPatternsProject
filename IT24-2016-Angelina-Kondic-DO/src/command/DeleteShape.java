package command;

import mvc.DrawingModel;
import shapes.Shape;

public class DeleteShape implements Command {
	private DrawingModel model;
	private Shape shape;
	private int oldPosition;
	
	public DeleteShape(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
		this.oldPosition=model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		model.deleteShape(shape);
	}

	@Override
	public void unexecute() {
		model.addShapeToIndex(oldPosition, shape);
	}
	@Override
	public String toString() {
		return "Deleted:"+shape.toString();
	}

}
