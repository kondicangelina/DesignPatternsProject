package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import shapes.Point;
import shapes.Square;

public class DialogAddSquare extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtXCoord;
	private JTextField txtYCoord;
	private JTextField txtWidth;
	private JLabel lblYCoord;
	private JLabel lblXCoord;
	private JLabel lblWidth;
	private JPanel mainPanel;
	private JPanel btnPanel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private Color color;
	private Color innerColor;
	
	private Square square;
	
	public DialogAddSquare() {
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
	}
	private void checkTextField() {
		if (txtXCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter X coordinate", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if (txtYCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter Y", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if (txtWidth.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter width", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				int xCoordinate = Integer.parseInt(txtXCoord.getText());
				int yCoordinate = Integer.parseInt(txtYCoord.getText());
				int width = Integer.parseInt(txtWidth.getText());
				if (xCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (yCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (width <= 0 ) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					square = new Square(new Point(xCoordinate, yCoordinate), width, color, innerColor);
					dispose();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getContentPane(), "The values must be number", "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	public void generateUserInterface() {
		setModal(true);
		setTitle("Add square");
		setResizable(false);
		setBounds(50, 50, 600, 400);
		setModal(true);
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 163, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		lblXCoord = new JLabel("X Coordinate:");
		GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
		gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblXCoordinate.gridx = 1;
		gbc_lblXCoordinate.gridy = 1;
		mainPanel.add(lblXCoord, gbc_lblXCoordinate);
		
		txtXCoord = new JTextField();
		txtXCoord.setEditable(false);
		GridBagConstraints gbc_txtXCoordinate = new GridBagConstraints();
		gbc_txtXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtXCoordinate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtXCoordinate.gridx = 3;
		gbc_txtXCoordinate.gridy = 1;
		mainPanel.add(txtXCoord, gbc_txtXCoordinate);
		txtXCoord.setColumns(10);
		
		lblYCoord = new JLabel("Y Coordinate:");
		GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
		gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblYCoordinate.gridx = 1;
		gbc_lblYCoordinate.gridy = 2;
		mainPanel.add(lblYCoord, gbc_lblYCoordinate);
		
		txtYCoord = new JTextField();
		txtYCoord.setEditable(false);
		GridBagConstraints gbc_txtYCoordinate = new GridBagConstraints();
		gbc_txtYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtYCoordinate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYCoordinate.gridx = 3;
		gbc_txtYCoordinate.gridy = 2;
		mainPanel.add(txtYCoord, gbc_txtYCoordinate);
		txtYCoord.setColumns(10);
		
		lblWidth = new JLabel("Width:");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.insets = new Insets(0, 0, 0, 5);
		gbc_lblWidth.gridx = 1;
		gbc_lblWidth.gridy = 3;
		mainPanel.add(lblWidth, gbc_lblWidth);
		
		txtWidth = new JTextField();
		GridBagConstraints gbc_txtWidth = new GridBagConstraints();
		gbc_txtWidth.insets = new Insets(0, 0, 0, 5);
		gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWidth.gridx = 3;
		gbc_txtWidth.gridy = 3;
		mainPanel.add(txtWidth, gbc_txtWidth);
		txtWidth.setColumns(10);
		
		btnPanel = new JPanel();
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnConfirm = new JButton("Confirm");
		btnPanel.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		btnPanel.add(btnCancel);
	}
	public Square getSquare() {
		return square;
	}
	
	public void setEdgeColor(Color edgeColor) {
		this.color = edgeColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public void fillAddingField(int x, int y, Color edgeColor, Color innerColor) {
		txtXCoord.setText(String.valueOf(x));
		txtYCoord.setText(String.valueOf(y));
		this.setEdgeColor(edgeColor);
		this.setInnerColor(innerColor);
	}
}
