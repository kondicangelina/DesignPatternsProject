package command;

import shapes.Rectangle;

public class UpdateRectangle implements Command {

	private Rectangle oldone, newone, currentone;
	public UpdateRectangle(Rectangle current, Rectangle newRect) {
		this.currentone=current;
		this.newone=newRect;
	}
	@Override
	public void execute() {
		oldone=(Rectangle) currentone.clone();
		currentone.setUpperLeftPoint(newone.getUpperLeftPoint());
		currentone.setWidth(newone.getWidth());
		currentone.setHeight(newone.getHeight());
		currentone.setColor(newone.getColor());
		currentone.setInnerColor(newone.getInnerColor());
		currentone.setSelected(true);
		newone.setSelected(true);
	}
	@Override
	public void unexecute() {
		currentone.setUpperLeftPoint(oldone.getUpperLeftPoint());
		currentone.setWidth(oldone.getWidth());
		currentone.setHeight(oldone.getHeight());
		currentone.setColor(oldone.getColor());
		currentone.setInnerColor(oldone.getInnerColor());
		currentone.setSelected(oldone.isSelected());
	}
	@Override
	public String toString() {
		return "Updated:"+oldone.toString()+" to "+newone.toString();
	}
}
