package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

import mvc.DrawingFrame;
import mvc.DrawingModel;

public class SaveDraw implements Save {

	private DrawingModel model;
	private DrawingFrame frame;

	public SaveDraw(DrawingModel model, DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}
	

	@Override
	public void save(File file) {
		try {
			 FileOutputStream files = new FileOutputStream(file+".ser"); 
	         ObjectOutputStream out = new ObjectOutputStream(files); 
	           
	         out.writeObject(model.getShapes()); 
	         
	         out.close(); 
	         files.close(); 
	         JOptionPane.showMessageDialog(null, "The file was saved.");
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error while writing."); 
			}
		
	}

}
