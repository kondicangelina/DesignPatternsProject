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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import adapter.HexagonAdapter;
import hexagon.Hexagon;

public class DialogAddHexagon extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtXCoord;
	private JTextField txtYCoord;
	private JTextField txtRadius;
	private JLabel lblXCoord;
	private JLabel lblYCoord;
	private JLabel lblRadius;
	private JPanel mainPanel;
	private JPanel btnPanel;
	private JButton btnConfirm;
	private JButton btnCancel;
	private Color color;
	private Color innerColor;
	private Hexagon hexagon;
	private HexagonAdapter hexagonAdapter;
	
	public DialogAddHexagon() {
		generateUserInterface();
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				checkTextFields();
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				dispose();
			}
		});
	}
	public void checkTextFields() {
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
					hexagon.setBorderColor(color);
					hexagon.setAreaColor(innerColor);
					hexagonAdapter = new HexagonAdapter(hexagon);
					dispose();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getContentPane(), "The values must be number", "WARNING!", JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	public void generateUserInterface() {
		setModal(true);
		setTitle("Add hexagon");
		setResizable(false);
		setBounds(50, 50, 600, 400);
		setModal(true);
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(339, 9));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		lblXCoord = new JLabel("X Coordinate:");
		GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
		gbc_lblXCoordinate.anchor = GridBagConstraints.EAST;
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
		gbc_lblYCoordinate.anchor = GridBagConstraints.EAST;
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
		
		lblRadius = new JLabel("Radius:");
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.anchor = GridBagConstraints.EAST;
		gbc_lblRadius.insets = new Insets(0, 0, 0, 5);
		gbc_lblRadius.gridx = 1;
		gbc_lblRadius.gridy = 3;
		mainPanel.add(lblRadius, gbc_lblRadius);
		
		txtRadius = new JTextField();
		GridBagConstraints gbc_txtRadius = new GridBagConstraints();
		gbc_txtRadius.insets = new Insets(0, 0, 0, 5);
		gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRadius.gridx = 3;
		gbc_txtRadius.gridy = 3;
		mainPanel.add(txtRadius, gbc_txtRadius);
		txtRadius.setColumns(10);
		
		btnPanel = new JPanel();
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnConfirm = new JButton("Confirm");
		btnPanel.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		btnPanel.add(btnCancel);
	}
	public Hexagon getHexagon() {
		return hexagon;
	}
	
	public HexagonAdapter getHexagonAdapter() {
		return hexagonAdapter;
	}

	public void setEdgeColor(Color edgeColor) {
		this.color = edgeColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	
	public void fillAddingFields(int x, int y, Color edgeColor, Color innerColor) {
		txtXCoord.setText(String.valueOf(x));
		txtYCoord.setText(String.valueOf(y));
		this.setEdgeColor(edgeColor);
		this.setInnerColor(innerColor);
	}
	
}
