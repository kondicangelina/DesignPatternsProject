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

import adapter.HexagonAdapter;
import hexagon.Hexagon;

public class DialogUpdateHexagon extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel btnPanel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JTextField txtXCoord;
	private JTextField txtYCoord;
	private JLabel lblXCoord;
	private JLabel lblYCoord;
	private JLabel lblRadius;
	private JTextField txtRadius;
	private Color color;
	private Color innerColor;
	private Hexagon hexagon;
	private JLabel lblFill;
	private JLabel lblOutline;
	private JButton btnColor;
	private JButton btnInnerColor;

	public DialogUpdateHexagon() {
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
	public Hexagon getHexagon() {
		return hexagon;
	}
	
	public void fillFields(HexagonAdapter hexagon) {
		txtXCoord.setText(String.valueOf(hexagon.getX()));
		txtYCoord.setText(String.valueOf(hexagon.getY()));
		txtRadius.setText(String.valueOf(hexagon.getR()));
		btnColor.setBackground(hexagon.getColor());
		btnInnerColor.setBackground(hexagon.getInnerColor());
	}
	
	public void checkTextField() {
		if (txtXCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter X coordinate", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if (txtYCoord.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter Y", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else if (txtRadius.getText().isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "Please enter radius", "WARNING!", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				int xCoordinate = Integer.parseInt(txtXCoord.getText());
				int yCoordinate = Integer.parseInt(txtYCoord.getText());
				int radius = Integer.parseInt(txtRadius.getText());
				if (xCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (yCoordinate <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (radius <= 0) {
					JOptionPane.showMessageDialog(getContentPane(), "The value must be positive number", "WARNING!", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					hexagon = new Hexagon(xCoordinate, yCoordinate, radius);
					hexagon.setBorderColor(btnColor.getBackground());
					hexagon.setAreaColor(btnInnerColor.getBackground());
					dispose();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getContentPane(), "The values must be number", "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public void generateUserInterface() {
		setModal(true);
		setTitle("Update hexagon");
		setResizable(false);
		setBounds(50, 50, 600, 400);
		setModal(true);
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(339, 9));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{30, 0, 81, 38, 116, 172, 0};
		gbl_mainPanel.rowHeights = new int[]{30, 16, 23, 22, 0, 29, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		lblXCoord = new JLabel("X Coordinate:");
		GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
		gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblXCoordinate.anchor = GridBagConstraints.WEST;
		gbc_lblXCoordinate.gridx = 2;
		gbc_lblXCoordinate.gridy = 1;
		mainPanel.add(lblXCoord, gbc_lblXCoordinate);
		
		txtXCoord = new JTextField();
		GridBagConstraints gbc_txtWidth = new GridBagConstraints();
		gbc_txtWidth.insets = new Insets(0, 0, 5, 5);
		gbc_txtWidth.anchor = GridBagConstraints.WEST;
		gbc_txtWidth.gridx = 4;
		gbc_txtWidth.gridy = 1;
		mainPanel.add(txtXCoord, gbc_txtWidth);
		txtXCoord.setColumns(10);
		
		lblYCoord = new JLabel("Y coordinate:");
		GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
		gbc_lblYCoordinate.anchor = GridBagConstraints.WEST;
		gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_lblYCoordinate.gridx = 2;
		gbc_lblYCoordinate.gridy = 2;
		mainPanel.add(lblYCoord, gbc_lblYCoordinate);
		
		txtYCoord = new JTextField();
		GridBagConstraints gbc_txtYCoordinate = new GridBagConstraints();
		gbc_txtYCoordinate.insets = new Insets(0, 0, 5, 5);
		gbc_txtYCoordinate.anchor = GridBagConstraints.WEST;
		gbc_txtYCoordinate.gridx = 4;
		gbc_txtYCoordinate.gridy = 2;
		mainPanel.add(txtYCoord, gbc_txtYCoordinate);
		txtYCoord.setColumns(10);
		
		lblRadius = new JLabel("Radius:");
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.anchor = GridBagConstraints.WEST;
		gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
		gbc_lblRadius.gridx = 2;
		gbc_lblRadius.gridy = 4;
		mainPanel.add(lblRadius, gbc_lblRadius);
		
		txtRadius = new JTextField();
		GridBagConstraints gbc_txtRadius = new GridBagConstraints();
		gbc_txtRadius.anchor = GridBagConstraints.WEST;
		gbc_txtRadius.insets = new Insets(0, 0, 5, 5);
		gbc_txtRadius.gridx = 4;
		gbc_txtRadius.gridy = 4;
		mainPanel.add(txtRadius, gbc_txtRadius);
		txtRadius.setColumns(10);
		
		lblOutline = new JLabel("Outline:");
		GridBagConstraints gbc_lblOutline = new GridBagConstraints();
		gbc_lblOutline.anchor = GridBagConstraints.WEST;
		gbc_lblOutline.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutline.gridx = 2;
		gbc_lblOutline.gridy = 5;
		mainPanel.add(lblOutline, gbc_lblOutline);
		
		btnColor = new JButton("");
		GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
		gbc_btnEdgeColor.fill = GridBagConstraints.BOTH;
		gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdgeColor.gridx = 4;
		gbc_btnEdgeColor.gridy = 5;
		mainPanel.add(btnColor, gbc_btnEdgeColor);
		
		lblFill = new JLabel("Fill:");
		GridBagConstraints gbc_lblFill = new GridBagConstraints();
		gbc_lblFill.anchor = GridBagConstraints.WEST;
		gbc_lblFill.insets = new Insets(0, 0, 5, 5);
		gbc_lblFill.gridx = 2;
		gbc_lblFill.gridy = 6;
		mainPanel.add(lblFill, gbc_lblFill);
		
		btnInnerColor = new JButton(" ");
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.fill = GridBagConstraints.BOTH;
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 4;
		gbc_btnInnerColor.gridy = 6;
		mainPanel.add(btnInnerColor, gbc_btnInnerColor);
		
		btnPanel = new JPanel();
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnConfirm = new JButton("Confirm");
		btnPanel.add(btnConfirm);
		btnCancel = new JButton("Cancel");
		btnPanel.add(btnCancel);
	}
	
	public void setHexagon(Hexagon ha) {
		this.hexagon = ha;
		this.color = ha.getBorderColor();
		this.innerColor = ha.getAreaColor();
		this.btnColor.setBackground(color);
		this.btnInnerColor.setBackground(innerColor);
		this.txtXCoord.setText(String.valueOf(this.hexagon.getX()));
		this.txtYCoord.setText(String.valueOf(this.hexagon.getY()));
		this.txtRadius.setText(String.valueOf(this.hexagon.getR()));
	}
	
	public void setColor(Color edgeColor) {
		this.color = edgeColor;
		
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
		
	}
	
	
}
