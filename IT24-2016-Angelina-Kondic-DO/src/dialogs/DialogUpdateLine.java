package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import shapes.Line;
import shapes.Point;

public class DialogUpdateLine extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel btnPanel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel lblStartPoint;
	private JLabel lblStartPointXCoord;
	private JTextField txtStartPointXCoord;
	private JLabel lblStartPointYCoord;
	private JTextField txtStartPointYCoord;
	private JLabel lblEndPoint;
	private JLabel lblEndPointXCoord;
	private JTextField txtEndPointXCoord;
	private JLabel lblEndPointYCoord;
	private JTextField txtEndPointYCoord;
	private JLabel lblOutline;
	private JButton btnColor;
	private Line line;

	public DialogUpdateLine() {
		generateUserInterface();
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				checkTextField();
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				dispose();
			}
		});
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				Color choosen=JColorChooser.showDialog(null,"Choose edge color", btnColor.getBackground());
				if(choosen!=null) {
					btnColor.setBackground(choosen);
				}
			}
		});
	}
	public void checkTextField() {
		if (txtStartPointXCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter X coordinate of startpoint", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if (txtStartPointYCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter Y coordinate of startpoint", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if (txtEndPointXCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter X coordinate of endpoint", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if (txtEndPointYCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter Y coordinate of endpoint", "WARNING!", JOptionPane.WARNING_MESSAGE);		
		} else {
			try {
				int xStartCoordinate = Integer.parseInt(txtStartPointXCoord.getText());
				int yStartCoordinate = Integer.parseInt(txtStartPointYCoord.getText());
				int xEndCoordinate = Integer.parseInt(txtEndPointXCoord.getText());
				int yEndCoordinate = Integer.parseInt(txtEndPointYCoord.getText());
				if (xStartCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (yStartCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (xEndCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (yEndCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					line = new Line(new Point(xStartCoordinate, yStartCoordinate), new Point(xEndCoordinate, yEndCoordinate), btnColor.getBackground());
					dispose();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getContentPane(), "The values must be number", "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	public Line getLine() {
		return line;
	}
	
	public void fillFields(Line line) {
		txtStartPointXCoord.setText((String.valueOf(line.getStartPoint().getX())));
		txtStartPointYCoord.setText((String.valueOf(line.getStartPoint().getY())));
		txtEndPointXCoord.setText(String.valueOf(line.getEndPoint().getX()));
		txtEndPointYCoord.setText(String.valueOf(line.getEndPoint().getY()));
		btnColor.setBackground(line.getColor());
	}
	
	public void generateUserInterface() {
		setModal(true);
		setTitle("Update line");
		setResizable(false);
		setBounds(50, 50, 600, 400);
		setModal(true);
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{30, 0, 81, 38, 116, 172, 0};
		gbl_mainPanel.rowHeights = new int[]{30, 16, 23, 22, 29, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		lblStartPoint = new JLabel("Start point");
		lblStartPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
		gbc_lblStartPoint.anchor = GridBagConstraints.WEST;
		gbc_lblStartPoint.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartPoint.gridx = 2;
		gbc_lblStartPoint.gridy = 1;
		mainPanel.add(lblStartPoint, gbc_lblStartPoint);
		
		lblStartPointXCoord = new JLabel("X coordinate:");
		GridBagConstraints gbc_lblStartPointXCoordinate = new GridBagConstraints();
		gbc_lblStartPointXCoordinate.anchor = GridBagConstraints.WEST;
		gbc_lblStartPointXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartPointXCoordinate.gridx = 2;
		gbc_lblStartPointXCoordinate.gridy = 2;
		mainPanel.add(lblStartPointXCoord, gbc_lblStartPointXCoordinate);
		
		txtStartPointXCoord = new JTextField();
		GridBagConstraints gbc_txtStartPointXCoordinate = new GridBagConstraints();
		gbc_txtStartPointXCoordinate.anchor = GridBagConstraints.WEST;
		gbc_txtStartPointXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtStartPointXCoordinate.gridx = 4;
		gbc_txtStartPointXCoordinate.gridy = 2;
		mainPanel.add(txtStartPointXCoord, gbc_txtStartPointXCoordinate);
		txtStartPointXCoord.setColumns(10);
		
		lblStartPointYCoord = new JLabel("Y coordinate:");
		GridBagConstraints gbc_lblStartPointYCoordinate = new GridBagConstraints();
		gbc_lblStartPointYCoordinate.anchor = GridBagConstraints.WEST;
		gbc_lblStartPointYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartPointYCoordinate.gridx = 2;
		gbc_lblStartPointYCoordinate.gridy = 3;
		mainPanel.add(lblStartPointYCoord, gbc_lblStartPointYCoordinate);
		
		txtStartPointYCoord = new JTextField();
		GridBagConstraints gbc_txtStartPointYCoordinate = new GridBagConstraints();
		gbc_txtStartPointYCoordinate.anchor = GridBagConstraints.WEST;
		gbc_txtStartPointYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtStartPointYCoordinate.gridx = 4;
		gbc_txtStartPointYCoordinate.gridy = 3;
		mainPanel.add(txtStartPointYCoord, gbc_txtStartPointYCoordinate);
		txtStartPointYCoord.setColumns(10);
		
		lblEndPoint = new JLabel("End point");
		lblEndPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
		gbc_lblEndPoint.anchor = GridBagConstraints.WEST;
		gbc_lblEndPoint.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndPoint.gridx = 2;
		gbc_lblEndPoint.gridy = 5;
		mainPanel.add(lblEndPoint, gbc_lblEndPoint);
		
		lblEndPointXCoord = new JLabel("X coordinate:");
		GridBagConstraints gbc_lblEndPointXCoordinate = new GridBagConstraints();
		gbc_lblEndPointXCoordinate.anchor = GridBagConstraints.WEST;
		gbc_lblEndPointXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndPointXCoordinate.gridx = 2;
		gbc_lblEndPointXCoordinate.gridy = 6;
		mainPanel.add(lblEndPointXCoord, gbc_lblEndPointXCoordinate);
		
		txtEndPointXCoord = new JTextField();
		GridBagConstraints gbc_txtEndPointXCoordinate = new GridBagConstraints();
		gbc_txtEndPointXCoordinate.anchor = GridBagConstraints.WEST;
		gbc_txtEndPointXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtEndPointXCoordinate.gridx = 4;
		gbc_txtEndPointXCoordinate.gridy = 6;
		mainPanel.add(txtEndPointXCoord, gbc_txtEndPointXCoordinate);
		txtEndPointXCoord.setColumns(10);
		
		lblEndPointYCoord = new JLabel("Y coordinate:");
		GridBagConstraints gbc_lblEndPointYCoordinate = new GridBagConstraints();
		gbc_lblEndPointYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndPointYCoordinate.gridx = 2;
		gbc_lblEndPointYCoordinate.gridy = 7;
		mainPanel.add(lblEndPointYCoord, gbc_lblEndPointYCoordinate);
		
		txtEndPointYCoord = new JTextField();
		GridBagConstraints gbc_txtEndPointYCoordinate = new GridBagConstraints();
		gbc_txtEndPointYCoordinate.anchor = GridBagConstraints.WEST;
		gbc_txtEndPointYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtEndPointYCoordinate.gridx = 4;
		gbc_txtEndPointYCoordinate.gridy = 7;
		mainPanel.add(txtEndPointYCoord, gbc_txtEndPointYCoordinate);
		txtEndPointYCoord.setColumns(10);
		
		lblOutline = new JLabel("Outline:");
		GridBagConstraints gbc_lblOutline = new GridBagConstraints();
		gbc_lblOutline.anchor = GridBagConstraints.WEST;
		gbc_lblOutline.insets = new Insets(0, 0, 0, 5);
		gbc_lblOutline.gridx = 2;
		gbc_lblOutline.gridy = 9;
		mainPanel.add(lblOutline, gbc_lblOutline);
		
		btnColor = new JButton("");
		btnColor.setSize(new Dimension(100, 25));
		GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
		gbc_btnEdgeColor.fill = GridBagConstraints.BOTH;
		gbc_btnEdgeColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnEdgeColor.gridx = 4;
		gbc_btnEdgeColor.gridy = 9;
		mainPanel.add(btnColor, gbc_btnEdgeColor);
		
		btnPanel = new JPanel();
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnConfirm = new JButton("Confirm");
		btnPanel.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		btnPanel.add(btnCancel);
	}
}
