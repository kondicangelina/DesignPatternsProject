package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import command.AddShape;
import command.BringToBack;
import command.BringToFront;
import command.Command;
import command.DeleteShape;
import command.SelectShape;
import command.ToBack;
import command.ToFront;
import command.UnselectShape;
import command.UpdateCircle;
import command.UpdateHexagon;
import command.UpdateLine;
import command.UpdatePoint;
import command.UpdateRectangle;
import command.UpdateSquare;
import dialogs.DialogAddCircle;
import dialogs.DialogAddHexagon;
import dialogs.DialogAddRectangle;
import dialogs.DialogAddSquare;
import dialogs.DialogLog;
import dialogs.DialogUpdateCircle;
import dialogs.DialogUpdateHexagon;
import dialogs.DialogUpdateLine;
import dialogs.DialogUpdatePoint;
import dialogs.DialogUpdateRectangle;
import dialogs.DialogUpdateSquare;
import hexagon.Hexagon;
import shapes.Circle;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;
import strategy.LoadDraw;
import strategy.LoadLog;
import strategy.LoadManager;
import strategy.SaveDraw;
import strategy.SaveLog;
import strategy.SaveManager;

public class DrawingController {

	private DrawingModel model;
	private DrawingFrame frame;
	private Color edgeColor=Color.BLACK;
	private Color innerColor=Color.WHITE;
	private Color tempEdgeColor;
	private boolean lineDrawingClick;
	private Point lineStart;
	private Line line;
	
	private AddShape addShape;
	private SelectShape selectShape;
	private DeleteShape deleteShape;
	private UpdatePoint updatePoint;
	private UpdateLine updateLine;
	private UpdateRectangle updateRectangle;
	private UpdateSquare updateSquare;
	private UpdateCircle updateCircle;
	private UpdateHexagon updateHexagon;
	private ToFront toFront;
	private ToBack toBack;
	private BringToBack bringToBack;
	private BringToFront bringToFront;
	private Point point;
	private Rectangle rectangle;
	private Square square;
	private Circle circle;
	
	private PropertyChangeSupport propertyChangeSupport;
	private Stack<Command> commandStack;
	private Stack<Command> redoStack;
	
	private ArrayList<String> logCommands;
	private ArrayList<Shape> selectedShapes;

	private SaveManager saveManager;
	private LoadManager loadManager;
	private SaveLog saveLog;
	private SaveDraw saveDraw;
	private LoadLog loadLog;
	private LoadDraw loadDraw;

	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
		propertyChangeSupport=new PropertyChangeSupport(this);
		commandStack=new Stack<Command>();
		redoStack=new Stack<Command>();
		logCommands=new ArrayList<String>();
		selectedShapes=new ArrayList<Shape>();
		checkCommandAndRedoStackState();
	}
	
	
	public void addChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	public Color changeInnerColor() {
		Color choosen=JColorChooser.showDialog(null, "Choose inner color", Color.WHITE);
		if(choosen!=null) {
			innerColor=choosen;
		}
		return innerColor;
	}
	public Color changeColor() {
		Color choosen=JColorChooser.showDialog(null, "Choose edge color", Color.BLACK);
		if(choosen!=null) {
			edgeColor=choosen;
		}
		return edgeColor;
	}
	public void switchColors() {
		tempEdgeColor=edgeColor;
		edgeColor=innerColor;
		innerColor=tempEdgeColor;
	}
	
	public Color getEdgeColor() {
		return edgeColor;
	}
	public Color getInnerColor() {
		return innerColor;
	}
	
	public void doSelection(MouseEvent click) {
		ArrayList<Shape> shapes=model.getShapes();
		for(Shape shape: shapes) {
			if(shape.contains(click.getX(), click.getY())) {
				if(!shape.isSelected()) {
					shape.setSelected(true);
					selectedShapes.add(shape);
					selectShape=new SelectShape(shape);
					doCommand(selectShape);
				} else {
					shape.setSelected(false);
					doCommand(new UnselectShape(shape));
				}
			}
		}
	}
	public void doSelectionFromDialog(Shape shh) {
		Shape shape=check(shh);
		for(Shape s: model.getShapes()) {
			if(s.equals(shape)) {
				if(!shape.isSelected()) {
					s.setSelected(true);
					selectedShapes.add(s);
					selectShape=new SelectShape(s);
					doCommand(selectShape);
				}
				else {
					s.setSelected(false);
					doCommand(new UnselectShape(s));
				}
			}
		}
		
	}
	public void doCommand(Command command) {
		command.execute();
		commandStack.push(command);
		frame.getLogTextArea().append(command.toString()+"\n");
		logCommands.add(command.toString());
		propertyChangeSupport.firePropertyChange("enableUndo",false, true);
		redoStack.clear();
		checkDrawingFrameState();
		frame.getView().repaint();
		frame.setTglBtnSelect(true);
	}
	
	public void drawPoint(MouseEvent click) {
		point = new Point(click.getX(), click.getY(), edgeColor);
		addShape=new AddShape(model, point);
		doCommand(addShape);
	}
	public void drawLine(MouseEvent click) {
		if(lineDrawingClick) {
			lineDrawingClick=!lineDrawingClick;
			line=new Line(lineStart, new Point(click.getX(), click.getY()), edgeColor);
			addShape=new AddShape(model, line);
			doCommand(addShape);
			
		} else {
			lineDrawingClick=!lineDrawingClick;
			lineStart=new Point(click.getX(),click.getY(), edgeColor);
			
		}
	}
	public void drawRectangle(MouseEvent click) {
		DialogAddRectangle dialogRect=new DialogAddRectangle();
		dialogRect.fillAddingFields(click.getX(), click.getY(), edgeColor, innerColor);
		dialogRect.setVisible(true);
		if(dialogRect.getRectangle()!=null) {
			rectangle=dialogRect.getRectangle();
			addShape=new AddShape(model, rectangle);
			doCommand(addShape);
		}
	}
	public void drawSquare(MouseEvent click) {
		DialogAddSquare dialogSquare = new DialogAddSquare();
		dialogSquare.fillAddingField(click.getX(), click.getY(), edgeColor, innerColor);
		dialogSquare.setVisible(true);
		if (dialogSquare.getSquare() != null) {
			square = dialogSquare.getSquare();
			addShape = new AddShape(model, square);
			doCommand(addShape);
		}
	}
	public void drawCircle(MouseEvent click) {
		DialogAddCircle dialogCircle = new DialogAddCircle();
		dialogCircle.fillAddingFields(click.getX(), click.getY(), edgeColor, innerColor);
		dialogCircle.setVisible(true);
		if (dialogCircle.getCircle() != null) {
			circle = dialogCircle.getCircle();
			addShape = new AddShape(model, circle);
			doCommand(addShape);
		}
	}
	public void drawHexagon(MouseEvent click) {
		DialogAddHexagon dialogAddHexagon = new DialogAddHexagon();
		dialogAddHexagon.fillAddingFields(click.getX(), click.getY(), edgeColor, innerColor);
		dialogAddHexagon.setVisible(true);
		if (dialogAddHexagon.getHexagonAdapter() != null) {
			HexagonAdapter hexagonAdapter = dialogAddHexagon.getHexagonAdapter();
			addShape = new AddShape(model, hexagonAdapter);
			doCommand(addShape);
		}
	}
	 
	public void updateShape() {
		ArrayList<Shape> shapes=model.getShapes();
		for(Shape shape: shapes) {
			if(shape.isSelected()) {
				if(shape instanceof Point) {
					updatePoint((Point) shape);
				} else if(shape instanceof Line) {
					updateLine((Line) shape);
				} else if(shape instanceof Rectangle) {
					updateRectangle((Rectangle) shape);
				} else if(shape instanceof Square) {
					updateSquare((Square) shape);
				} else if(shape instanceof Circle) {
					updateCircle((Circle) shape);
				} else if(shape instanceof HexagonAdapter) {
					updateHexagon((HexagonAdapter) shape);
				}
			}
		}
	}
	
	private void updatePoint(Point shape) {
		DialogUpdatePoint dialogUpdatePoint = new DialogUpdatePoint();
		dialogUpdatePoint.fillFields(shape);
		dialogUpdatePoint.setVisible(true);
		if (dialogUpdatePoint.getPoint() != null) {
			Point updatedPoint = dialogUpdatePoint.getPoint();
			updatePoint = new UpdatePoint(shape, updatedPoint);
			doCommand(updatePoint);
		}
	}
	public void updatePointFromDialog(Shape shh,Shape shh1) {
		Point p= (Point) check(shh);
		if(shh1 instanceof Point && p!=null) {
			updatePoint=new UpdatePoint(p,(Point)shh1);
			doCommand(updatePoint);	
		}
	}
	private void updateLine(Line shape) {
		DialogUpdateLine dialogUpdateLine = new DialogUpdateLine();
		dialogUpdateLine.fillFields(shape);
		dialogUpdateLine.setVisible(true);
		if (dialogUpdateLine.getLine() != null) {
			Line updatedLine = dialogUpdateLine.getLine();
			updateLine = new UpdateLine(shape, updatedLine);
			doCommand(updateLine);
		}
	}
	public void updateLineFromDialog(Shape shh, Shape shh1) {
		checkDrawingFrameState();
		Line l=(Line) check(shh);
		if(shh1 instanceof Line && l!=null) {
			updateLine=new UpdateLine(l,(Line)shh1);
			doCommand(updateLine);
		}
	}
	private void updateRectangle(Rectangle shape) {
		DialogUpdateRectangle dialogUpdateRectangle = new DialogUpdateRectangle();
		dialogUpdateRectangle.fillFields(shape);
		dialogUpdateRectangle.setVisible(true);
		if (dialogUpdateRectangle.getRectangle() != null) {
			Rectangle updatedRectangle = dialogUpdateRectangle.getRectangle();
			updateRectangle = new UpdateRectangle(shape, updatedRectangle);
			doCommand(updateRectangle);
		}
	}
	public void updateRectangleFromDialog(Shape shh, Shape shh1) {
		Rectangle r=(Rectangle) check(shh);
		if(shh1 instanceof Rectangle && r!=null) {
			updateRectangle= new UpdateRectangle(r,(Rectangle)shh1);
			doCommand(updateRectangle);
		}
	}
	private void updateSquare(Square shape) {
		DialogUpdateSquare dialogUpdateSquare = new DialogUpdateSquare();
		dialogUpdateSquare.fillFields(shape);
		dialogUpdateSquare.setVisible(true);
		if (dialogUpdateSquare.getSquare() != null) {
			Square updatedSquare = dialogUpdateSquare.getSquare();
			updateSquare = new UpdateSquare(shape, updatedSquare);
			doCommand(updateSquare);
			}
	}
	public void updateSquareFromDialog(Shape shh, Shape shh1) {
		Square sq= (Square)check(shh);
		if(shh1 instanceof Square && sq!=null) {
			updateSquare=new UpdateSquare(sq,(Square)shh1);
			doCommand(updateSquare);
		}
	}
	private void updateCircle(Circle shape) {
		DialogUpdateCircle dialogUpdateCircle = new DialogUpdateCircle();
		dialogUpdateCircle.fillFields(shape);
		dialogUpdateCircle.setVisible(true);
		if (dialogUpdateCircle.getCircle() != null) {
			Circle updatedCircle = dialogUpdateCircle.getCircle();
			updateCircle = new UpdateCircle(shape, updatedCircle);
			doCommand(updateCircle);
		}
	}
	public void updateCircleFromDialog(Shape shh, Shape shh1) {
		Circle c=(Circle) check(shh);
		if(shh1 instanceof Circle && c!=null) {
			updateCircle=new UpdateCircle(c,(Circle)shh1);
			doCommand(updateCircle);
		}
	}
	private void updateHexagon(HexagonAdapter shape) {
		DialogUpdateHexagon dialogUpdateHexagon = new DialogUpdateHexagon();
		dialogUpdateHexagon.fillFields(shape);
		dialogUpdateHexagon.setVisible(true);
		if (dialogUpdateHexagon.getHexagon() != null) {
			Hexagon updatedHexagon = dialogUpdateHexagon.getHexagon();
			HexagonAdapter hexAdapter = new HexagonAdapter(updatedHexagon);
			updateHexagon = new UpdateHexagon(shape, hexAdapter);
			doCommand(updateHexagon);
		}
	}
	public void updateHexagonFromDialog(Shape shh, Shape shh1) {
		HexagonAdapter hex=(HexagonAdapter)check(shh);
		if(shh1 instanceof HexagonAdapter && hex!=null) {
			updateHexagon=new UpdateHexagon(hex,(HexagonAdapter)shh1);
			doCommand(updateHexagon);
		}
	}
	
	public void deleteShape() {
		if(JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete selected shape?", "Warning!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			Iterator<Shape> iterator= selectedShapes.iterator();
			while(iterator.hasNext()) {
				Shape shForDel=iterator.next();
				if(shForDel.isSelected()) {
					deleteShape=new DeleteShape(model, shForDel);
				}
				doCommand(deleteShape);
			}
			
			propertyChangeSupport.firePropertyChange("enableEditAndDelete", true, false);
			if(model.getShapes().size()==0) {
				frame.setTglBtnSelect(false);
			}
			else frame.setTglBtnSelect(true);
		}
	}
	public void deleteShapeFromDialog(Shape shh) {
		
		if(check(shh)!=null) {
			Iterator<Shape> iterator= model.getShapes().iterator();
			while(iterator.hasNext()) {
				Shape shForDel=iterator.next();
				if(shh.equals(shForDel)) {
					shForDel.setSelected(true);
					deleteShape=new DeleteShape(model, shForDel);
				}
			}
			doCommand(deleteShape);
			propertyChangeSupport.firePropertyChange("enableEditAndDelete", true, false);
			if(model.getShapes().size()==0) {
				frame.setTglBtnSelect(false);
			}
			else frame.setTglBtnSelect(true);
				
		}
		else
			System.out.println("Delete don't work - Shape is not in the list of shapes on model- "+shh);
	}
	

	public void undo() {
		propertyChangeSupport.firePropertyChange("enableRedo", false, true);
		Command command= commandStack.pop();
		frame.getLogTextArea().append("Undo:"+command.toString()+"\n");
		logCommands.add("Undo:"+command.toString());
		command.unexecute();
		redoStack.push(command);
		checkDrawingFrameState();
		frame.getView().repaint();
		if(commandStack.isEmpty()) {
			frame.getBtnUndo().setEnabled(false);
			return;
		}
	}
	public void redo() {
		propertyChangeSupport.firePropertyChange("enableUndo", false, true);
		Command command=redoStack.pop();
		frame.getLogTextArea().append("Redo:"+command.toString()+"\n");
		logCommands.add("Redo:"+command.toString());
		command.execute();
		commandStack.push(command);
		checkDrawingFrameState();
		frame.getView().repaint();
		if(redoStack.isEmpty()) {
			frame.getBtnRedo().setEnabled(false);
			return;
		}
	}
	
	public void checkDrawingFrameState() {
		if(model.getShapes().isEmpty()) {
			propertyChangeSupport.firePropertyChange("enableEditAndDelete", true, false);
		}
		checkSelectedShapes();
	}
	public void checkSelectedShapes() {
		int selectedShCounter=0;
		for(int i=0; i<model.getShapes().size();i++) {
			if(model.getShapes().get(i).isSelected()) {
				selectedShCounter++;
				if(selectedShCounter==1) {
					propertyChangeSupport.firePropertyChange("enableEditAndDelete", false, true);
					propertyChangeSupport.firePropertyChange("enableMoveToZ", false, true);
					if(model.getShapes().get(0).isSelected()) {
						if(model.getShapes().size()==1) {
							propertyChangeSupport.firePropertyChange("disableToFrontAndToBack", true, false);
						} else {
							propertyChangeSupport.firePropertyChange("disableToBack", true, false);
						}
					} else if(model.getShapes().get(model.getShapes().size()-1).isSelected()) {
						propertyChangeSupport.firePropertyChange("disableToFront", true, false);
					}
				} else {
					propertyChangeSupport.firePropertyChange("enableOnlyDelete", false, true);
					propertyChangeSupport.firePropertyChange("disableMoveToZ", true, false);
					checkCommandAndRedoStackState();
				}
				
			} 
		}
		if(selectedShCounter==0) {
			propertyChangeSupport.firePropertyChange("enableEditAndDelete", true, false);
			propertyChangeSupport.firePropertyChange("disableMoveToZ", true, false);
			if(model.getShapes().size()==0) 
				frame.setTglBtnSelect(false);
		}
		else {
			frame.setTglBtnSelect(true);
			propertyChangeSupport.firePropertyChange("disableEditAndDelete", true, false);
		}
	}
	public void checkCommandAndRedoStackState() {
		if(commandStack.isEmpty()) {
			propertyChangeSupport.firePropertyChange("disableUndo", true, false);
			
		} else if(redoStack.isEmpty()) {
			propertyChangeSupport.firePropertyChange("disableRedo", true, false);
		} 
	}
	
	
	
	public void toFront() {
		Iterator<Shape> iterator=model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shapeForMoving=iterator.next();
			if(shapeForMoving.isSelected()) {
				toFront=new ToFront(model, shapeForMoving);
			}
		}
		doCommand(toFront);
	}
	public void toFrontFromDialog(Shape shh) {
		if(check(shh)!=null) 
		{
			Iterator<Shape>iterator=model.getShapes().iterator();
			while(iterator.hasNext()) {
				Shape shapeForMoving=iterator.next();
				if(shapeForMoving.isSelected() && shapeForMoving.equals(shh)) {
					toFront=new ToFront(model,shapeForMoving);
				}
			}
			doCommand(toFront);
		}
	}
	public void toBack() {
		Iterator<Shape> iterator=model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shapeForMoving=iterator.next();
			if(shapeForMoving.isSelected()) {
				toBack=new ToBack(model, shapeForMoving);
			}
		}
		doCommand(toBack);
	}
	public void toBackFromDialog(Shape shh) {
		if(check(shh)!=null) 
		{
			Iterator<Shape>iterator=model.getShapes().iterator();
			while(iterator.hasNext()) {
				Shape shapeForMoving=iterator.next();
				if(shapeForMoving.isSelected() && shapeForMoving.equals(shh)) {
					toBack=new ToBack(model,shapeForMoving);
				}
			}
			doCommand(toBack);
		}
		
	}
	public void bringToFront() {
		Iterator<Shape> iterator=model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shapeForMoving=iterator.next();
			if(shapeForMoving.isSelected()) {
				bringToFront=new BringToFront(model, shapeForMoving);
			}
		}
		doCommand(bringToFront);
	}
	public void bringToFrontFromDialog(Shape shh) {
		if(check(shh)!=null) 
		{
			Iterator<Shape>iterator=model.getShapes().iterator();
			while(iterator.hasNext()) {
				Shape shapeForMoving=iterator.next();
				if(shapeForMoving.isSelected() && shapeForMoving.equals(shh)) {
					bringToFront=new BringToFront(model,shapeForMoving);
				}
			}
			doCommand(bringToFront);
		}
		
	}
	public void bringToBack() {
		Iterator<Shape> iterator=model.getShapes().iterator();
		while(iterator.hasNext()) {
			Shape shapeForMoving=iterator.next();
			if(shapeForMoving.isSelected()) {
				bringToBack=new BringToBack(model, shapeForMoving);
			}
		}
		doCommand(bringToBack);
	}
	public void bringToBackFromDialog(Shape shh) 
	{
		if(check(shh)!=null) 
		{
			Iterator<Shape>iterator=model.getShapes().iterator();
			while(iterator.hasNext()) {
				Shape shapeForMoving=iterator.next();
				if(shapeForMoving.isSelected() && shapeForMoving.equals(shh)) {
					bringToBack=new BringToBack(model,shapeForMoving);
				}
			}
			doCommand(bringToBack);
		}
	}
	public void saveLog() {
		JFileChooser jFileChooser= new JFileChooser();
		if(jFileChooser.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION) {
			saveLog=new SaveLog();
			saveLog.setCommands(logCommands);
			saveManager=new SaveManager(saveLog);
			saveManager.save(jFileChooser.getSelectedFile());
		}
	}
	public void openLog() {
		JFileChooser jFileChooser=new JFileChooser();
		if (jFileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			
			loadLog= new LoadLog();
			loadManager = new LoadManager(loadLog);
			loadManager.load(jFileChooser.getSelectedFile());
			ArrayList<String> commandsForDialog = loadLog.getLogCommands();
			DialogLog logDialog = new DialogLog();
			logDialog.setModel(model);
			logDialog.setController(this);
			logDialog.setFrame(frame);
			logDialog.getCommands(commandsForDialog);
			refreshFrameAndStack();
			logDialog.setVisible(true);
		}
	}
	public void saveDraw() {
		JFileChooser jFileChooser= new JFileChooser();
		if(jFileChooser.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION) {
			saveDraw=new SaveDraw(model,frame);
			saveManager=new SaveManager(saveDraw);
			saveManager.save(jFileChooser.getSelectedFile());
		}
	}
	public void openDraw() {
		JFileChooser jFileChooser= new JFileChooser();
		if(jFileChooser.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION) {
			refreshFrameAndStack();
			loadDraw=new LoadDraw(model, frame);
			loadManager=new LoadManager(loadDraw);
			loadManager.load(jFileChooser.getSelectedFile());
		}
	}
	
	public DrawingModel getModel() {
		return model;
	}
	public Stack<Command> getCommandStack(){
		return commandStack;
	}
	public void refreshFrameAndStack() {
		this.model.getShapes().clear();
		this.commandStack.clear();
		this.redoStack.clear();
		this.logCommands.clear();
		this.frame.getLogTextArea().setText(null);
		checkCommandAndRedoStackState();
		this.frame.repaint();
	}
	public Shape check(Shape forCheck) {
		Shape sh=null;
		for(Shape var: model.getShapes()) 
		{
			if(var.equals(forCheck)==true) 
				sh=var;
			
		}
		return sh;
	}
}
