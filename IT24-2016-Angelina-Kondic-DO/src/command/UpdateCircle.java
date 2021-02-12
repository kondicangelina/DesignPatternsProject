package command;

import shapes.Circle;

public class UpdateCircle implements Command {

	private Circle oldone,newone,current;

	public UpdateCircle(Circle current, Circle newone) {
		this.current=current;
		this.newone=newone;
	}
	
	@Override
	public void execute() {
		oldone=(Circle) current.clone();
		current.setCenterPoint(newone.getCenterPoint());
		current.setRadius(newone.getRadius());
		current.setColor(newone.getColor());
		current.setInnerColor(newone.getInnerColor());
		current.setSelected(true);
		newone.setSelected(true);
	}

	@Override
	public void unexecute() {
		current.setCenterPoint(oldone.getCenterPoint());
		current.setRadius(oldone.getRadius());
		current.setColor(oldone.getColor());
		current.setInnerColor(oldone.getInnerColor());
		current.setSelected(oldone.isSelected());
	}
	@Override
	public String toString() {
		return "Updated:"+oldone.toString()+" to "+newone.toString();
	}
}
