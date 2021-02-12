package mvc;

import javax.swing.JFrame;

public class DrawingApp {
	public static void main(String[] args) {
		DrawingModel model=new DrawingModel();
		DrawingFrame frame=new DrawingFrame();
	
		frame.getView().setModel(model);
		DrawingController controller=new DrawingController(model, frame);
		frame.setController(controller);
		frame.setTitle("IT24/2016-Angelina Kondiæ");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}

}
