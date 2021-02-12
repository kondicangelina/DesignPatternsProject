package command;

import shapes.Line;

public class UpdateLine implements Command{

	private Line oldLine, newLine, currentLine;
	public UpdateLine(Line oldone, Line newone) {
		this.currentLine=oldone;
		this.newLine=newone;
	}
	
	@Override
	public void execute() {
		oldLine=(Line) currentLine.clone();
		currentLine.setStartPoint(newLine.getStartPoint());
		currentLine.setEndPoint(newLine.getEndPoint());
		currentLine.setColor(newLine.getColor());
		currentLine.setSelected(true);
		newLine.setSelected(true);
	}

	@Override
	public void unexecute() {
		currentLine.setStartPoint(oldLine.getStartPoint());
		currentLine.setEndPoint(oldLine.getEndPoint());
		currentLine.setColor(oldLine.getColor());
		currentLine.setSelected(oldLine.isSelected());
	}

	@Override
	public String toString() {
		return "Updated:" +oldLine.toString()+" to "+newLine.toString();
	}
}
