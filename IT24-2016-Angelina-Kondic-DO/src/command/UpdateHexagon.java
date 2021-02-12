package command;

import adapter.HexagonAdapter;

public class UpdateHexagon implements Command {
	private HexagonAdapter oldone, newone, current;
	public UpdateHexagon(HexagonAdapter current, HexagonAdapter newone) {
		this.current=current;
		this.newone=newone;
	}
	@Override
	public void execute() {
		oldone=(HexagonAdapter) current.clone();
		current.setHexagon(newone.getHexagon());
		current.setColor(newone.getColor());
		current.setInnerColor(newone.getInnerColor());
		current.setSelected(true);
		newone.setSelected(true);
	}
	@Override
	public void unexecute() {
		current.setHexagon(oldone.getHexagon());
		current.setColor(oldone.getColor());
		current.setInnerColor(oldone.getInnerColor());
		current.setSelected(oldone.isSelected());
	}
	@Override
	public String toString() {
		return "Updated:"+oldone.toString()+" to "+newone.toString();
	}

}
