package strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class LoadLog implements Load{

	private BufferedReader bufferedReader;
	private ArrayList<String> logCommands;
	
	public LoadLog() {
		logCommands=new ArrayList<String>();
	}
	
	@Override
	public void load(File file) {
			try {
				
				bufferedReader=new BufferedReader(new FileReader(file));
				String line = bufferedReader.readLine();
				while(line!=null) {
					logCommands.add(line);
					line=bufferedReader.readLine();
				}
				bufferedReader.close();
				
				
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "SerializableLoader fail");
			}
		
	}
	public ArrayList<String> getLogCommands() {
		return logCommands;
	}
	

}
