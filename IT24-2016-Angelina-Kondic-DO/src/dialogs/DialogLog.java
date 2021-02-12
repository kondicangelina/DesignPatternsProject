package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import adapter.HexagonAdapter;
import command.AddShape;
import command.BringToBack;
import command.BringToFront;
import command.ToBack;
import command.ToFront;

import hexagon.Hexagon;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import shapes.Circle;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;

import java.awt.event.MouseAdapter;



public class DialogLog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea logTextArea;
	private JPanel mainPanel;
	private JPanel btnPanel;
	private JButton btnExecute;
	private DrawingController controller;
	private DrawingFrame frame;
	private DrawingModel model;
	private Stack<String> commandsString;
	
	public DialogLog() {
		commandsString=new Stack<String>();
		generateUserInterface();
		btnExecute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(commandsString.isEmpty()) {
					dispose();
					return;
				}
				String command=commandsString.pop();
				parseStringToCommand(command);
				}
		});
		
	}
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}
	
	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public void parseStringToCommand(String command) {
		String commandType, shapeType,shapeFields, params;
		Shape fin=null;
		String commandParts[] = command.split(":");
		commandType = commandParts[0];
		shapeType = commandParts[1];
		shapeFields = commandParts[2];
		Shape shh=null;
		Shape shh1=null;
		if(commandParts.length >3) {
			params=commandParts[3];
		}
		else params=null;
		
		
		if(shapeType.equals("Point")) shh= getPointFromString(shapeFields);
		else if(shapeType.equals("Line")) shh= getLineFromString(shapeFields);
		else if(shapeType.equals("Rectangle")) shh=getRectangleFromString(shapeFields);
		else if(shapeType.equals("Square")) shh=getSquareFromString(shapeFields);
		else if(shapeType.equals("Circle")) shh=getCircleFromString(shapeFields);
		else if(shapeType.equals("Hexagon")) shh=getHexagonFromString(shapeFields);
		
		switch(commandType) {	
			case"Moved to back":
				if(shh!=null) {
					controller.toBackFromDialog(shh);
				}
				else
					System.out.println("Shape can not be moved to back in list of shapes on model- "+fin+" - "+commandType);
				break;
			case "Moved to front":
				if(shh!=null) {
					controller.toFrontFromDialog(shh);
				}
				else
					System.out.println("Shape can not be moved to front in list of shapes on model- "+fin+" - "+commandType);
				break;
			case "Bringed to back":
				if(shh!=null) {
					controller.bringToBackFromDialog(shh);
				}
				else
					System.out.println("Shape can not be bringed to back in list of shapes on model- "+fin+" - "+commandType);
				break;
			case "Bringed to front":
				if(shh!=null) {
					controller.bringToFrontFromDialog(shh);
				}
				else
					System.out.println("Shape can not be bringed to front in list of shapes on model- "+fin+" - "+commandType);
				break;
		
			case "Added": 
				if(shh!=null)
					controller.doCommand(new AddShape(this.model, shh));
				else
					System.out.println("Shape can not be added-"+shh+" - "+commandType);
				break;
			case "Selected": 
				controller.doSelectionFromDialog(shh);
				break;
			case "Unselected":
				controller.doSelectionFromDialog(shh);
				break;
			case "Deleted":
				controller.deleteShapeFromDialog(shh);
				break;
			case "Undo":
				controller.undo();
				break;
			case "Redo":
				controller.redo();
				break;
			case "Updated":
				if(shh instanceof Point) {
					shh1= getPointFromString(params);
					controller.updatePointFromDialog(shh,shh1);
				}
				else if(shh instanceof Line) {
					shh1= getLineFromString(params);
					controller.updateLineFromDialog(shh,shh1);
				}
				else if(shh instanceof Rectangle) {
					shh1=getRectangleFromString(params);
					controller.updateRectangleFromDialog(shh,shh1);
				}
				else if(shh instanceof Square) {
					shh1=getSquareFromString(params);
					controller.updateSquareFromDialog(shh,shh1);
				}
				else if(shh instanceof Circle) {
					shh1=getCircleFromString(params);
					controller.updateCircleFromDialog(shh,shh1);
				}
				else if(shh instanceof HexagonAdapter) {
					shh1=getHexagonFromString(params);
					if(shh!=null && shh1!=null)
						controller.updateHexagonFromDialog(shh,shh1);
				}
				else 
					System.out.println("Shape can not be updated from-"+shh+" to-"+shh1);
				break;
			default:
				System.out.println("Type of method is not valid: "+commandType);
			}
		}
	public void generateUserInterface() {
		setModal(true);
		setTitle("Log");
		setResizable(false);
		setBounds(50, 50, 900, 500);
		setModal(true);
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		logTextArea = new JTextArea(15,70);
		logTextArea.setLineWrap(true);
		logTextArea.setEditable(false);
		mainPanel.add(logTextArea);
	    JScrollPane areaScrollPane = new JScrollPane(logTextArea);
	    areaScrollPane.setVerticalScrollBarPolicy(
	            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.add(areaScrollPane);
		
		btnPanel = new JPanel();
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnExecute = new JButton("Execute");
		btnExecute.setIcon(new ImageIcon(DialogLog.class.getResource("/icons/execute.png")));
		btnPanel.add(btnExecute);
		
	}
	
	public Color parseColor(String col) {
		col=col.substring(1);
		col=col.substring(0,col.length()-1);
		String[] cols=col.split("-");
		return new Color(Integer.parseInt(cols[0]), Integer.parseInt(cols[1]), Integer.parseInt(cols[2]));
	}
	
	public Point getPointFromString(String point) {
		String pointParts[] = point.split(",");
		int xCoordinate = Integer.parseInt(pointParts[0].split("=")[1]);
		int yCoordinate = Integer.parseInt(pointParts[1].split("=")[1]);
	    Color color = parseColor(pointParts[2].split("=")[1]);
	    boolean s= Boolean.parseBoolean(pointParts[3].split("=")[1]);
		 Point p= new Point(xCoordinate, yCoordinate, color);
		 p.setSelected(s);
		 return p;
	}
	
	public Line getLineFromString(String line) {
		String lineParts[] = line.split(",");
		
		int startPointX = Integer.parseInt(lineParts[0].split("=")[1]);
		int startPointY = Integer.parseInt(lineParts[1].split("=")[1]);
		int endPointX = Integer.parseInt(lineParts[2].split("=")[1]);
		int endPointY = Integer.parseInt(lineParts[3].split("=")[1]);
	    Color color = parseColor(lineParts[4].split("=")[1]);
	    boolean s=Boolean.parseBoolean(lineParts[5].split("=")[1]);
		Line l=new Line(new Point(startPointX, startPointY), new Point(endPointX, endPointY), color);
		l.setSelected(s);
		return l;
	}
	
	public Square getSquareFromString(String square) {
		String squareParts[] = square.split(",");
		int upLeftPointX = Integer.parseInt(squareParts[0].split("=")[1]);
		int upLeftPointY = Integer.parseInt(squareParts[1].split("=")[1]);
		int width = Integer.parseInt(squareParts[2].split("=")[1]);
		Color edgeColor = parseColor(squareParts[3].split("=")[1]);
		Color innerColor = parseColor(squareParts[4].split("=")[1]);
		boolean s= Boolean.parseBoolean(squareParts[5].split("=")[1]);
		Square sq = new Square(new Point(upLeftPointX, upLeftPointY), width, edgeColor, innerColor);
		sq.setSelected(s);
		return sq;
	}
	
	public Rectangle getRectangleFromString(String rectangle) {
		String rectangleParts[] = rectangle.split(",");
		
		int upLeftPointX = Integer.parseInt(rectangleParts[0].split("=")[1]);
		int upLeftPointY = Integer.parseInt(rectangleParts[1].split("=")[1]);
		int width = Integer.parseInt(rectangleParts[2].split("=")[1]);
		int height = Integer.parseInt(rectangleParts[3].split("=")[1]);
		Color edgeColor = parseColor(rectangleParts[4].split("=")[1]);
		Color innerColor = parseColor(rectangleParts[5].split("=")[1]);
		boolean s=Boolean.parseBoolean(rectangleParts[6].split("=")[1]);
		Rectangle rec=new Rectangle(new Point(upLeftPointX, upLeftPointY), width, height, edgeColor, innerColor);
		rec.setSelected(s);
		return rec;
	}
	
	public Circle getCircleFromString(String circle) {
		String circleParts[] = circle.split(",");
		int centerPointX = Integer.parseInt(circleParts[0].split("=")[1]);
		int centerPointY = Integer.parseInt(circleParts[1].split("=")[1]);
		int radius = Integer.parseInt(circleParts[2].split("=")[1]);
		Color edgeColor = parseColor(circleParts[3].split("=")[1]);
		Color innerColor = parseColor(circleParts[4].split("=")[1]);
		boolean s=Boolean.parseBoolean(circleParts[5].split("=")[1]);
		Circle cir = new Circle(new Point(centerPointX, centerPointY), radius, edgeColor, innerColor);
		cir.setSelected(s);
		return cir;
	}
	
	public HexagonAdapter getHexagonFromString(String hexagon) {
		String hexagonParts[] = hexagon.split(",");
		int pointX = Integer.parseInt(hexagonParts[0].split("=")[1]);
		int pointY = Integer.parseInt(hexagonParts[1].split("=")[1]);
		int r = Integer.parseInt(hexagonParts[2].split("=")[1]);
		Color edgeColor =parseColor(hexagonParts[3].split("=")[1]);
		Color innerColor = parseColor(hexagonParts[4].split("=")[1]);
		boolean s = Boolean.parseBoolean(hexagonParts[5].split("=")[1]);
		Hexagon h = new Hexagon(pointX, pointY, r);
		h.setBorderColor(edgeColor);
		h.setAreaColor(innerColor);
		HexagonAdapter hex=new HexagonAdapter(h,edgeColor, innerColor);
		hex.setSelected(s);
		return hex;
	}
	
	public void getCommands(ArrayList<String> commandsForDialog) {
		fillJTextArea(commandsForDialog);	
		fillCommandsStringStack(commandsForDialog);
	}
	public void fillJTextArea(ArrayList<String> commandsForDialog) {
		for (String command : commandsForDialog) {
			logTextArea.append(command + "\n");
		}	
	}
	
	public void fillCommandsStringStack(ArrayList<String> commandsForDialog) {
		Collections.reverse(commandsForDialog);
		for (String command : commandsForDialog) {
			commandsString.push(command);
		}
	}
	
	
}
