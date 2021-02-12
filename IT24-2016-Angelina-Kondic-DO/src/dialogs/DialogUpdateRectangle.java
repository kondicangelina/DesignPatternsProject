package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
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

import shapes.Point;
import shapes.Rectangle;

public class DialogUpdateRectangle extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel btnPanel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel lblUpLeftPoint;
	private JLabel lblXCoord;
	private JTextField txtXCoord;
	private JLabel lblYCoord;
	private JTextField txtYCoord;
	private JLabel lblWidth;
	private JTextField txtWidth;
	private JLabel lblHeight;
	private JTextField txtHeight;
	private JLabel lblOutline;
	private JButton btnColor;
	private JLabel lblFill;
	private JButton btnInnerColor;
	private Rectangle rectangle;
	
	public DialogUpdateRectangle() {
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
				Color choosenColor = JColorChooser.showDialog(null, "Choose edge color", btnColor.getBackground());
				if (choosenColor != null) {
					btnColor.setBackground(choosenColor);
				}
			}
		});
		
		btnInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color choosenColor = JColorChooser.showDialog(null, "Choose edge color", btnInnerColor.getBackground());
				if (choosenColor != null) {
					btnInnerColor.setBackground(choosenColor);
				}
			}
		});
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public void fillFields(Rectangle rectangle) {
		txtXCoord.setText(String.valueOf(rectangle.getUpperLeftPoint().getX()));
		txtYCoord.setText(String.valueOf(rectangle.getUpperLeftPoint().getY()));
		txtWidth.setText(String.valueOf(rectangle.getWidth()));
		txtHeight.setText(String.valueOf(rectangle.getHeight()));
		btnColor.setBackground(rectangle.getColor());
		btnInnerColor.setBackground(rectangle.getInnerColor());
	}
	
	public void checkTextField() {
		if (txtXCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter X coordinate of up left point", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if (txtYCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter Y coordinate of up left point", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if (txtWidth.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter width", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if (txtHeight.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter height", "WARNING!", JOptionPane.WARNING_MESSAGE);		
		} else {
			try {
				int xCoordinate = Integer.parseInt(txtXCoord.getText());
				int yCoordinate = Integer.parseInt(txtYCoord.getText());
				int width = Integer.parseInt(txtWidth.getText());
				int height = Integer.parseInt(txtHeight.getText());
				if (xCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (yCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (width <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (height <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					rectangle = new Rectangle(new Point(xCoordinate, yCoordinate), width, height, btnColor.getBackground(), btnInnerColor.getBackground());
					dispose();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getContentPane(), "The values must be number", "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public void generateUserInterface() {
		setModal(true);
		setTitle("Update rectangle");
		setResizable(false);
		setBounds(50, 50, 600, 400);
		setModal(true);
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.NORTH);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{30, 0, 0, 81, 38, 116, 172, 0};
		gbl_mainPanel.rowHeights = new int[]{30, 16, 23, 0, 22, 29, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		lblUpLeftPoint = new JLabel("Up left point");
		lblUpLeftPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblUpLeftPoint = new GridBagConstraints();
		gbc_lblUpLeftPoint.anchor = GridBagConstraints.WEST;
		gbc_lblUpLeftPoint.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpLeftPoint.gridx = 2;
		gbc_lblUpLeftPoint.gridy = 1;
		mainPanel.add(lblUpLeftPoint, gbc_lblUpLeftPoint);
		
		lblXCoord = new JLabel("X coordinate:");
		GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
		gbc_lblXCoordinate.anchor = GridBagConstraints.WEST;
		gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblXCoordinate.gridx = 2;
		gbc_lblXCoordinate.gridy = 2;
		mainPanel.add(lblXCoord, gbc_lblXCoordinate);
		
		txtXCoord = new JTextField();
		GridBagConstraints gbc_txtXCoordinate = new GridBagConstraints();
		gbc_txtXCoordinate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtXCoordinate.gridx = 3;
		gbc_txtXCoordinate.gridy = 2;
		mainPanel.add(txtXCoord, gbc_txtXCoordinate);
		txtXCoord.setColumns(10);
		
		lblYCoord = new JLabel("Y coordinate:");
		GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
		gbc_lblYCoordinate.anchor = GridBagConstraints.WEST;
		gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblYCoordinate.gridx = 2;
		gbc_lblYCoordinate.gridy = 3;
		mainPanel.add(lblYCoord, gbc_lblYCoordinate);
		
		txtYCoord = new JTextField();
		GridBagConstraints gbc_txtYCoordinate = new GridBagConstraints();
		gbc_txtYCoordinate.fill = GridBagConstraints.BOTH;
		gbc_txtYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtYCoordinate.gridx = 3;
		gbc_txtYCoordinate.gridy = 3;
		mainPanel.add(txtYCoord, gbc_txtYCoordinate);
		txtYCoord.setColumns(10);
		
		lblWidth = new JLabel("Width:");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.anchor = GridBagConstraints.WEST;
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.gridx = 2;
		gbc_lblWidth.gridy = 5;
		mainPanel.add(lblWidth, gbc_lblWidth);
		
		txtWidth = new JTextField();
		GridBagConstraints gbc_txtWidth = new GridBagConstraints();
		gbc_txtWidth.insets = new Insets(0, 0, 5, 5);
		gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWidth.gridx = 3;
		gbc_txtWidth.gridy = 5;
		mainPanel.add(txtWidth, gbc_txtWidth);
		txtWidth.setColumns(10);
		
		lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.WEST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 2;
		gbc_lblHeight.gridy = 6;
		mainPanel.add(lblHeight, gbc_lblHeight);
		
		txtHeight = new JTextField();
		GridBagConstraints gbc_txtHeight = new GridBagConstraints();
		gbc_txtHeight.insets = new Insets(0, 0, 5, 5);
		gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtHeight.gridx = 3;
		gbc_txtHeight.gridy = 6;
		mainPanel.add(txtHeight, gbc_txtHeight);
		txtHeight.setColumns(10);
		
		lblOutline = new JLabel("Outline:");
		GridBagConstraints gbc_lblOutline = new GridBagConstraints();
		gbc_lblOutline.anchor = GridBagConstraints.WEST;
		gbc_lblOutline.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutline.gridx = 2;
		gbc_lblOutline.gridy = 8;
		mainPanel.add(lblOutline, gbc_lblOutline);
		
		btnColor = new JButton("");
		GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
		gbc_btnEdgeColor.fill = GridBagConstraints.BOTH;
		gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdgeColor.gridx = 3;
		gbc_btnEdgeColor.gridy = 8;
		mainPanel.add(btnColor, gbc_btnEdgeColor);
		
		lblFill = new JLabel("Fill:");
		GridBagConstraints gbc_lblFill = new GridBagConstraints();
		gbc_lblFill.anchor = GridBagConstraints.WEST;
		gbc_lblFill.insets = new Insets(0, 0, 5, 5);
		gbc_lblFill.gridx = 2;
		gbc_lblFill.gridy = 9;
		mainPanel.add(lblFill, gbc_lblFill);
		
		btnInnerColor = new JButton("");
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.fill = GridBagConstraints.BOTH;
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 3;
		gbc_btnInnerColor.gridy = 9;
		mainPanel.add(btnInnerColor, gbc_btnInnerColor);
			
		btnPanel = new JPanel();
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnConfirm = new JButton("Confirm");
		btnPanel.add(btnConfirm);
		btnCancel = new JButton("Cancel");
		btnPanel.add(btnCancel);
	}
}
