package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import shapes.Point;

public class DialogUpdatePoint extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel btnPanel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JLabel lblXCoordinate;
	private JLabel lblYCoordinate;
	private JLabel lblOutline;
	private JButton btnColor;
	private Point point;
	public DialogUpdatePoint() {
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
			public void mouseClicked(MouseEvent e) {
				Color choosen=JColorChooser.showDialog(null, "Choose edge color", btnColor.getBackground());
				if(choosen!=null) {
					btnColor.setBackground(choosen);
				}
			}
		});
	}
	public void checkTextField() {
		if (txtXCoordinate.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter X coordinate", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if (txtYCoordinate.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter Y", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				int xCoordinate = Integer.parseInt(txtXCoordinate.getText());
				int yCoordinate = Integer.parseInt(txtYCoordinate.getText());
				if (xCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (yCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					point = new Point(xCoordinate, yCoordinate, btnColor.getBackground());
					dispose();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getContentPane(), "The values must be number", "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	public Point getPoint() {
		return point;
	}
	public void fillFields(Point point) {
		txtXCoordinate.setText(String.valueOf(point.getX()));
		txtYCoordinate.setText(String.valueOf(point.getY()));
		btnColor.setBackground(point.getColor());
	}
	
	public void generateUserInterface() {
		setModal(true);
		setTitle("Update point");
		setResizable(false);
		setBounds(50, 50, 600, 400);
		setModal(true);
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(339, 9));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 175, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		lblXCoordinate = new JLabel("X Coordinate:");
		GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
		gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblXCoordinate.gridx = 2;
		gbc_lblXCoordinate.gridy = 1;
		mainPanel.add(lblXCoordinate, gbc_lblXCoordinate);
		
		txtXCoordinate = new JTextField();
		GridBagConstraints gbc_txtXCoordinate = new GridBagConstraints();
		gbc_txtXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtXCoordinate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtXCoordinate.gridx = 4;
		gbc_txtXCoordinate.gridy = 1;
		mainPanel.add(txtXCoordinate, gbc_txtXCoordinate);
		txtXCoordinate.setColumns(10);
		
		lblYCoordinate = new JLabel("Y Coordinate:");
		GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
		gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblYCoordinate.gridx = 2;
		gbc_lblYCoordinate.gridy = 2;
		mainPanel.add(lblYCoordinate, gbc_lblYCoordinate);
		
		txtYCoordinate = new JTextField();
		GridBagConstraints gbc_txtYCoordinate = new GridBagConstraints();
		gbc_txtYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtYCoordinate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYCoordinate.gridx = 4;
		gbc_txtYCoordinate.gridy = 2;
		mainPanel.add(txtYCoordinate, gbc_txtYCoordinate);
		txtYCoordinate.setColumns(10);
		
		lblOutline = new JLabel("Outline:");
		GridBagConstraints gbc_lblOutline = new GridBagConstraints();
		gbc_lblOutline.insets = new Insets(0, 0, 0, 5);
		gbc_lblOutline.gridx = 2;
		gbc_lblOutline.gridy = 3;
		mainPanel.add(lblOutline, gbc_lblOutline);
		
		btnColor = new JButton("");
		GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
		gbc_btnEdgeColor.fill = GridBagConstraints.BOTH;
		gbc_btnEdgeColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnEdgeColor.gridx = 4;
		gbc_btnEdgeColor.gridy = 3;
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
