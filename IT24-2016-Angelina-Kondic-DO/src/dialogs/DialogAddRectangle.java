package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
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
import shapes.Rectangle;

public class DialogAddRectangle extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtXCoord;
	private JTextField txtYCoord;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private JPanel mainPanel;
	private JPanel btnPanel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private Rectangle rectangle;
	private Color color;
	private Color innerColor;
	
	public DialogAddRectangle() {
		generateUserInterface();
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				checkFields();
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				dispose();
			}
		});
	}
	public void checkFields() {
		if(txtXCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter X coordinate", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if(txtYCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter Y coordinate", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if(txtHeight.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter height", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if(txtWidth.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter width", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				int xCoordinate = Integer.parseInt(txtXCoord.getText());
				int yCoordinate = Integer.parseInt(txtYCoord.getText());
				int height = Integer.parseInt(txtHeight.getText());
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
				} else if (height <= 0 ) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					rectangle = new Rectangle(new Point(xCoordinate, yCoordinate), width, height, color, innerColor);
					dispose();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getContentPane(), "The values must be number", "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	public void generateUserInterface() {
		setModal(true);
		setTitle("Rectangle");
		setResizable(false);
		setBounds(50, 50, 600, 400);
		setModal(true);
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 131, 145, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel lblXCoordinate = new JLabel("X Coordinate:");
		GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
		gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblXCoordinate.gridx = 2;
		gbc_lblXCoordinate.gridy = 1;
		mainPanel.add(lblXCoordinate, gbc_lblXCoordinate);
		
		txtXCoord = new JTextField();
		txtXCoord.setEditable(false);
		GridBagConstraints gbc_txtXCoordinate = new GridBagConstraints();
		gbc_txtXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtXCoordinate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtXCoordinate.gridx = 4;
		gbc_txtXCoordinate.gridy = 1;
		mainPanel.add(txtXCoord, gbc_txtXCoordinate);
		txtXCoord.setColumns(10);
		
		JLabel lblYCoordinate = new JLabel("Y Coordinate:");
		GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
		gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblYCoordinate.gridx = 2;
		gbc_lblYCoordinate.gridy = 2;
		mainPanel.add(lblYCoordinate, gbc_lblYCoordinate);
		
		txtYCoord = new JTextField();
		txtYCoord.setEditable(false);
		GridBagConstraints gbc_txtYCoordinate = new GridBagConstraints();
		gbc_txtYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtYCoordinate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYCoordinate.gridx = 4;
		gbc_txtYCoordinate.gridy = 2;
		mainPanel.add(txtYCoord, gbc_txtYCoordinate);
		txtYCoord.setColumns(10);
		
		
		
		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.insets = new Insets(0, 0, 0, 5);
		gbc_lblHeight.gridx = 2;
		gbc_lblHeight.gridy = 4;
		mainPanel.add(lblHeight, gbc_lblHeight);
		
		txtHeight = new JTextField();
		GridBagConstraints gbc_txtHeight = new GridBagConstraints();
		gbc_txtHeight.insets = new Insets(0, 0, 0, 5);
		gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtHeight.gridx = 4;
		gbc_txtHeight.gridy = 4;
		mainPanel.add(txtHeight, gbc_txtHeight);
		txtHeight.setColumns(10);
		
		
		JLabel lblWidth = new JLabel("Width:");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.gridx = 2;
		gbc_lblWidth.gridy = 3;
		mainPanel.add(lblWidth, gbc_lblWidth);
		
		txtWidth = new JTextField();
		GridBagConstraints gbc_txtWidth = new GridBagConstraints();
		gbc_txtWidth.insets = new Insets(0, 0, 5, 5);
		gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWidth.gridx = 4;
		gbc_txtWidth.gridy = 3;
		mainPanel.add(txtWidth, gbc_txtWidth);
		txtWidth.setColumns(10);
		
		btnPanel = new JPanel();
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		btnConfirm = new JButton("Confirm");
		btnPanel.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
	}
	public Rectangle getRectangle() {
		return this.rectangle;
	}
	public void setColor(Color color) {
		this.color=color;
	}
	public void setInnerColor(Color innerColor) {
		this.innerColor=innerColor;
	}
	public void fillAddingFields(int x, int y, Color color, Color innerColor) {
		txtXCoord.setText(String.valueOf(x));
		txtYCoord.setText(String.valueOf(y));
		this.setColor(color);
		this.setInnerColor(innerColor);
	}
}
