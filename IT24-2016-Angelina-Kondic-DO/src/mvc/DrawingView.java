package mvc;

import java.awt.Graphics;

import javax.swing.JPanel;

import shapes.SurfaceShape;

public class DrawingView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawingModel model;
	
	public DrawingView() {
		
	}
	public DrawingModel getDrawingModel() {
		return model;
	}
	public void setModel(DrawingModel model) {
		this.model=model;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(model !=null) {
			for(shapes.Shape sh: model.getShapes()) {
				if(sh instanceof SurfaceShape) ((SurfaceShape) sh).fill(g);
				sh.draw(g);
			}
		}
	}

}
