package command;
import shapes.Shape;

public class UnselectShape implements Command {

	private Shape shape;
	public UnselectShape(Shape shape) {
		super();
		this.shape=shape;
	}
	@Override
	public void execute() {
		shape.setSelected(false);
	}
	@Override
	public void unexecute() {
		shape.setSelected(true);
	}
	
	@Override
	public String toString() {
		return "Unselected:" + shape.toString();
	}
}
