package strategy;



import java.io.File;
import java.io.FileInputStream;

import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import command.AddShape;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import shapes.Shape;

public class LoadDraw implements Load {
	private DrawingModel model;
	private DrawingFrame frame;
	AddShape addSh;
	public LoadDraw(DrawingModel model, DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}
	
	@Override
	public void load(File file) {
		try {
			FileInputStream files = new FileInputStream(file); 
	        ObjectInputStream in = new ObjectInputStream(files); 
			@SuppressWarnings("unchecked")
			ArrayList<Shape> shapes=(ArrayList<Shape>)in.readObject();
			for(Shape s : shapes) {
				model.addShape(s);
				addSh= new AddShape(model, s);
			}
			in.close(); 
	        files.close();
	        
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "SerializableLoader fail");
		}
	}

}
