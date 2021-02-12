package command;

import shapes.Point;

public class UpdatePoint implements Command {

	private Point oldPoint, newPoint, currentPoint;

	public UpdatePoint(Point current, Point newone) {
		this.currentPoint=current;
		this.newPoint=newone;
	}
	@Override
	public void execute() {
		oldPoint=(Point) currentPoint;
		currentPoint.setX(newPoint.getX());
		currentPoint.setY(newPoint.getY());
		currentPoint.setColor(newPoint.getColor());
		currentPoint.setSelected(true);
		newPoint.setSelected(true);
	}

	@Override
	public void unexecute() {
		currentPoint.setX(oldPoint.getX());
		currentPoint.setY(oldPoint.getY());
		currentPoint.setColor(oldPoint.getColor());
		currentPoint.setSelected(oldPoint.isSelected());
	}

	@Override
	public String toString() {
		return "Updated:"+currentPoint.toString()+" to "+newPoint.toString();
	}
}
