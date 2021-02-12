package command;

import shapes.Square;

public class UpdateSquare implements Command {

	private Square oldone, newone, currentone;
	
	public UpdateSquare(Square current, Square newone) {
		this.currentone=current;
		this.newone=newone;
	}

	@Override
	public void execute() {
		oldone=(Square) currentone.clone();
		currentone.setUpperLeftPoint(newone.getUpperLeftPoint());
		currentone.setWidth(newone.getWidth());
		currentone.setColor(newone.getColor());
		currentone.setInnerColor(newone.getInnerColor());
		currentone.setSelected(true);
		newone.setSelected(true);
	}

	@Override
	public void unexecute() {
		currentone.setUpperLeftPoint(oldone.getUpperLeftPoint());
		currentone.setWidth(oldone.getWidth());
		currentone.setColor(oldone.getColor());
		currentone.setInnerColor(oldone.getInnerColor());
		currentone.setSelected(oldone.isSelected());
	}
	
	@Override
	public String toString() {
		return "Updated:"+oldone.toString()+" to "+newone.toString();
	}
}
