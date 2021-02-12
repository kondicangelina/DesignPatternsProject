package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import observer.DrawingObserver;

public class DrawingFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel westPanel;
	private JPanel eastPanel;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnColor;
	private JButton btnInnerColor;
	private JButton btnSwitchColor;
	private ButtonGroup btnGroup;
	private JToggleButton tglbtnSelect;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JLabel lblShapes;
	private JLabel lblColor;
	private JLabel lblInnerColor;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnSquare;
	private JToggleButton tglbtnHexagon;
	private JToggleButton tglbtnCircle;
	private DrawingObserver observer;
	private Color initialColor=Color.BLACK;
	private Color innerInitialColor=Color.WHITE;
	private JTextArea logTextArea;
	private JLabel lblLog;
	private JButton btnOpenLogFile;
	private JButton btnSaveLog;
	private JButton btnOpenDraw;
	private JButton btnSaveDraw;
	private JLabel lblFileActions;
	private JButton  btnNewDraw;
	
	
	public DrawingView getView() {
		return view;
	}
	public void setController(DrawingController controller) {
		this.controller=controller;
		controller.addChangeListener(observer);
	}
	public DrawingFrame() {
		generateUserInterface();
		observer=new DrawingObserver(this);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if(tglbtnPoint.isSelected()) {
					controller.drawPoint(click);
				} else if (tglbtnLine.isSelected()) {
					controller.drawLine(click);
				} else if(tglbtnRectangle.isSelected()) {
					controller.drawRectangle(click);
				} else if(tglbtnSquare.isSelected()) {
					controller.drawSquare(click);
				} else if(tglbtnCircle.isSelected()) {
					controller.drawCircle(click);
				} else if(tglbtnHexagon.isSelected()) {
					controller.drawHexagon(click);
				} else if(tglbtnSelect.isSelected()) {
					controller.doSelection(click);
				}
			}
		});
		btnInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				innerInitialColor=controller.changeInnerColor();
				btnInnerColor.setBackground(innerInitialColor);
			}
		});
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				initialColor=controller.changeColor();
				btnColor.setBackground(initialColor);
			}
		});
		btnSwitchColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.switchColors();
				btnColor.setBackground(initialColor);
				btnInnerColor.setBackground(innerInitialColor);
			}
		});
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if(btnDelete.isEnabled()) {
					controller.deleteShape();
				}
			}
		});
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.updateShape();
			}
		});
		btnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.undo();
			}
		});		
		btnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.redo();
			}
		});
		btnToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if(btnToBack.isEnabled()) {
					controller.toBack();
				}
			}
		});
		btnToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if(btnToFront.isEnabled()) {
					controller.toFront();
				}
			}
		});
		btnBringToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if(btnBringToBack.isEnabled()) {
					controller.bringToBack();
				}
			}
		});
		btnBringToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if(btnBringToFront.isEnabled()) {
					controller.bringToFront();
				}
			}
		});
		btnSaveLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.saveLog();
			}
		});
		btnSaveDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.saveDraw();
			}
		});
		btnOpenLogFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.openLog();
			}
		});
		btnOpenDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.openDraw();
			}
		});
		btnNewDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if(btnNewDraw.isEnabled()) {
					tglbtnSelect.setEnabled(false);
					getBtnEdit().setEnabled(false);
					getBtnDelete().setEnabled(false);
					getBtnToFront().setEnabled(false);
					getBtnToBack().setEnabled(false);
					getBtnBringToFront().setEnabled(false);
					getBtnBringToBack().setEnabled(false);
					controller.refreshFrameAndStack();
				}
			}
		});
	}
	
	public void generateUserInterface() {
		getContentPane().add(view, BorderLayout.CENTER);
		view.setBackground(Color.WHITE);
		view.setBorder(new EmptyBorder(0,0,0,0));
		btnGroup=new ButtonGroup();
		northPanel=new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		
		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/undo.png")));
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/redo.png")));
		tglbtnSelect = new JToggleButton("Select");
		tglbtnSelect.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/select.png")));
		tglbtnSelect.setEnabled(false);
		btnGroup.add(tglbtnSelect);
		btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/edit.png")));
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/delete.png")));
		btnToFront = new JButton("To Front");
		btnToFront.setEnabled(false);
		btnToFront.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/tofront.png")));
		btnToBack = new JButton("To Back");
		btnToBack.setEnabled(false);
		btnToBack.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/toback.png")));
		btnBringToFront = new JButton("Bring To Front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/bringtofront.png")));
		btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.setEnabled(false);
		btnBringToBack.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/bringtoback.png")));
		lblColor = new JLabel("Outline: ");
		btnColor = new JButton("");
		btnColor.setPreferredSize(new Dimension(80, 40));
		btnColor.setBackground(initialColor);
		lblInnerColor = new JLabel("Fill: ");
		btnInnerColor = new JButton("");
		btnInnerColor.setPreferredSize(new Dimension(80, 40));
		btnInnerColor.setBackground(innerInitialColor);
		
		btnSwitchColor = new JButton("Switch color");
		btnSwitchColor.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/switch-color.png")));
		
		northPanel.add(btnUndo);
		northPanel.add(btnRedo);
		northPanel.add(tglbtnSelect);
		northPanel.add(btnEdit);
		northPanel.add(btnDelete);
		northPanel.add(btnToFront);
		northPanel.add(btnToBack);
		northPanel.add(btnBringToFront);
		northPanel.add(btnBringToBack);
		northPanel.add(lblColor);
		northPanel.add(btnColor);
		northPanel.add(lblInnerColor);
		northPanel.add(btnInnerColor);
		northPanel.add(btnSwitchColor);

		
		southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		lblLog = new JLabel("Log:");
		southPanel.add(lblLog);
		
		logTextArea = new JTextArea(15, 120);
		logTextArea.setLineWrap(true);
		logTextArea.setEditable(false);
		southPanel.add(logTextArea);
	    JScrollPane areaScrollPane = new JScrollPane(logTextArea);
	    areaScrollPane.setVerticalScrollBarPolicy(
	            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
		southPanel.add(areaScrollPane);
		
		westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		GridBagLayout gbl_westPanel = new GridBagLayout();
		gbl_westPanel.columnWidths = new int[]{0, 0};
		gbl_westPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_westPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_westPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		westPanel.setLayout(gbl_westPanel);
		
		lblShapes = new JLabel("Shapes");
		GridBagConstraints gbc_lblShapes = new GridBagConstraints();
		gbc_lblShapes.insets = new Insets(0, 0, 5, 0);
		gbc_lblShapes.gridx = 0;
		gbc_lblShapes.gridy = 0;
		westPanel.add(lblShapes, gbc_lblShapes);
		
		tglbtnPoint = new JToggleButton("");
		tglbtnPoint.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/point.png")));
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnPoint.gridx = 0;
		gbc_tglbtnPoint.gridy = 1;
		westPanel.add(tglbtnPoint, gbc_tglbtnPoint);
		btnGroup.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("");
		tglbtnLine.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/line.png")));
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnLine.gridx = 0;
		gbc_tglbtnLine.gridy = 2;
		westPanel.add(tglbtnLine, gbc_tglbtnLine);
		btnGroup.add(tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("");
		tglbtnRectangle.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/rectangle.png")));
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnRectangle.gridx = 0;
		gbc_tglbtnRectangle.gridy = 3;
		westPanel.add(tglbtnRectangle, gbc_tglbtnRectangle);
		btnGroup.add(tglbtnRectangle);
		
		tglbtnSquare = new JToggleButton("");
		tglbtnSquare.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/square.png")));
		GridBagConstraints gbc_tglbtnSquare = new GridBagConstraints();
		gbc_tglbtnSquare.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnSquare.gridx = 0;
		gbc_tglbtnSquare.gridy = 4;
		westPanel.add(tglbtnSquare, gbc_tglbtnSquare);
		btnGroup.add(tglbtnSquare);
		
		tglbtnCircle = new JToggleButton("");
		tglbtnCircle.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/circle.png")));
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnCircle.gridx = 0;
		gbc_tglbtnCircle.gridy = 5;
		westPanel.add(tglbtnCircle, gbc_tglbtnCircle);
		btnGroup.add(tglbtnCircle);
		
		tglbtnHexagon = new JToggleButton("");
		tglbtnHexagon.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/hexagon.png")));
		GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
		gbc_tglbtnHexagon.gridx = 0;
		gbc_tglbtnHexagon.gridy = 6;
		westPanel.add(tglbtnHexagon, gbc_tglbtnHexagon);
		btnGroup.add(tglbtnHexagon);
		
		eastPanel = new JPanel();
		getContentPane().add(eastPanel, BorderLayout.EAST);
		GridBagLayout gbl_eastPanel = new GridBagLayout();
		gbl_eastPanel.columnWidths = new int[]{97, 0};
		gbl_eastPanel.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_eastPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_eastPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		eastPanel.setLayout(gbl_eastPanel);
		
		lblFileActions = new JLabel("File actions:");
		GridBagConstraints gbc_lblFileActions = new GridBagConstraints();
		gbc_lblFileActions.insets = new Insets(0, 0, 5, 0);
		gbc_lblFileActions.gridx = 0;
		gbc_lblFileActions.gridy = 0;
		
		btnNewDraw = new JButton("New Draw");
		btnNewDraw.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/new-draw.png")));
		GridBagConstraints gbc_btnNewDraw = new GridBagConstraints();
		gbc_btnNewDraw.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewDraw.gridx = 0;
		gbc_btnNewDraw.gridy = 1;
		eastPanel.add(btnNewDraw, gbc_btnNewDraw);
		
		eastPanel.add(lblFileActions, gbc_lblFileActions);
		btnOpenLogFile = new JButton("Open log");
		btnOpenLogFile.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/open-log.png")));
		GridBagConstraints gbc_btnOpenLogFile = new GridBagConstraints();
		gbc_btnOpenLogFile.fill = GridBagConstraints.BOTH;
		gbc_btnOpenLogFile.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpenLogFile.gridx = 0;
		gbc_btnOpenLogFile.gridy = 2;
		eastPanel.add(btnOpenLogFile, gbc_btnOpenLogFile);
		
		btnSaveLog = new JButton("Save log");
		btnSaveLog.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/save-log.png")));
		GridBagConstraints gbc_btnSaveLog = new GridBagConstraints();
		gbc_btnSaveLog.fill = GridBagConstraints.BOTH;
		gbc_btnSaveLog.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveLog.gridx = 0;
		gbc_btnSaveLog.gridy = 3;
		eastPanel.add(btnSaveLog, gbc_btnSaveLog);
		
		btnOpenDraw = new JButton("Open draw");
		btnOpenDraw.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/open-draw.png")));
		GridBagConstraints gbc_btnOpenDraw = new GridBagConstraints();
		gbc_btnOpenDraw.fill = GridBagConstraints.BOTH;
		gbc_btnOpenDraw.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpenDraw.gridx = 0;
		gbc_btnOpenDraw.gridy = 4;
		eastPanel.add(btnOpenDraw, gbc_btnOpenDraw);
		
		btnSaveDraw = new JButton("Save draw");
		btnSaveDraw.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/save-draw.png")));
		GridBagConstraints gbc_btnSaveDraw = new GridBagConstraints();
		gbc_btnSaveDraw.fill = GridBagConstraints.BOTH;
		gbc_btnSaveDraw.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaveDraw.gridx = 0;
		gbc_btnSaveDraw.gridy = 5;
		eastPanel.add(btnSaveDraw, gbc_btnSaveDraw);
	}
	
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}
	public JButton getBtnUndo() {
		return btnUndo;
	}
	public JButton getBtnRedo() {
		return btnRedo;
	}
	public JButton getBtnToFront() {
		return btnToFront;
	}
	public JButton getBtnToBack() {
		return btnToBack;
	}
	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}
	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}
	public JButton getBtnNewDraw() {
		return btnNewDraw;
	}
	public JTextArea getLogTextArea() {
		return logTextArea;
	}
	public void setTglBtnSelect(Boolean bo) 
	{
		 tglbtnSelect.setEnabled(bo);
		
	}
	
	
}
