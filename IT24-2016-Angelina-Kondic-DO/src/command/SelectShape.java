package command;

import shapes.Shape;

public class SelectShape implements Command {

	private Shape shape;
	public SelectShape(Shape shape) {
		super();
		this.shape=shape;
	}
	@Override
	public void execute() {
		shape.setSelected(true);
	}
	@Override
	public void unexecute() {
		if(shape.isSelected()) {
			shape.setSelected(false);
		}
		else {
			shape.setSelected(true);
		}
	}
	@Override
	public String toString() {
		return "Selected:" +shape.toString();
	}
	
}
