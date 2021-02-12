package strategy;


import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import mvc.DrawingFrame;
import mvc.DrawingModel;


public class SaveLog implements Save{

	
	private ArrayList<String> logCommands;
	private FileWriter fw;
	
	public DrawingModel model;
	public DrawingFrame frame;
	public SaveLog() {
		
	}
	public SaveLog(DrawingModel d, DrawingFrame f) {
		this.model = d;
		this.frame = f;
	}
	
	public void setCommands(ArrayList<String> commands) {
		this.logCommands=commands;
	}
	
	@Override
	public void save(File file) {
		try {
			fw = new FileWriter(file+".log");
			for(String c: logCommands) {
				fw.write(c+"\n");
			}
			fw.close();
            JOptionPane.showMessageDialog(null, "The file was saved.");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error while writing.");
		}
	}

	
	

}
