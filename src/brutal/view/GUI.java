package brutal.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Canvas;
import javax.swing.JLabel;
import java.awt.Label;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import brutal.controller.*;
import brutal.model.core.*;
import brutal.model.states.*;

import javax.swing.border.CompoundBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.TextField;
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.List;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {
	private IGame game;
	
	private JLabel lblNewLabel;
	private JTextArea userInputCommand;
	private JButton submitButton;
	private JPanel main;
	private JButton switchButton;
	private JButton confirmButton;
	
	public GUI(IGame game) {
		setResizable(false);
		this.game = game;
		
		setBounds(100, 100, 600, 550);
		setTitle("it's only brutal");
		getContentPane().setLayout(null);
		
		JLayeredPane title = new JLayeredPane();
		title.setBounds(10, 11, 564, 14);
		getContentPane().add(title);
		title.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("state");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		title.add(lblNewLabel, BorderLayout.NORTH);
		
		this.submitButton = new JButton("Enter");
		submitButton.setBounds(502, 385, 72, 115);
		submitButton.addActionListener(new UserInputController(this.game, this));
		getContentPane().add(submitButton);
		
		JScrollPane commands = new JScrollPane();
		commands.setBounds(10, 385, 482, 115);
		getContentPane().add(commands);
		
		userInputCommand = new JTextArea();
		commands.setViewportView(userInputCommand);
		
		main = new JPanel();
		main.setBounds(10, 36, 564, 338);
		getContentPane().add(main);
		main.setLayout(null);

		setVisible(true);
	}
	
	public void update() {
		this.updateState();
		this.main.updateUI();
	}
	
	private void updateState() {
		lblNewLabel.setText(this.game.getState().toString());
		
		JPanel panel = null;
		
		switch (this.game.getState().toString()) {
			case "start state":
				panel = this.startStatePanel();
				break;
			case "setup state":
				panel = this.setupStatePanel();
				break;
			case "allocation state":
				panel = this.allocationStatePanel();
				break;
			case "battle state":
				panel = this.battleStatePanel();
				break;
			case "truce state":
				panel = this.truceStatePanel();
				break;
			case "end state":
				panel = this.endStatePanel();
				break;
		}
		
		this.main.removeAll();
		this.main.add(panel);
	}

	public String getTypedCommand() {
		return this.userInputCommand.getText();
	}

	public void resetCommand() {
		this.userInputCommand.setText("");
	}
	
	private JPanel startStatePanel() {
		JPanel panel = new JPanel();
		panel = new JPanel();
		panel.setBounds(0, 0, 564, 338);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1;
		lblNewLabel_1 = new JLabel("Choose a program between ISI, RT, A2I, GI, GM, MTE, MM");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 564, 338);
		panel.add(lblNewLabel_1);
		
		return panel;
	}
	
	private JPanel setupStatePanel() {
		JPanel panel;
		JTable table;
		JScrollPane scrollPane;
		
		panel = new JPanel();
		panel.setBounds(0, 0, 564, 338);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 31, 564, 252);
		panel.add(scrollPane);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);
		table.setBounds(0, 31, 564, 252);
		
		String[][] content = new String[this.game.getPlayerTurn().getAllStudents().size()][7];
		
		int i = 0;
		
		for (IStudent student : this.game.getPlayerTurn().getAllStudents()) {
			if (student.getStrategy() == null) {
				content[i] = new String[] {
					student.getId(),
					student.getStrength() + "",
					student.getDexterity() + "",
					student.getResilience() + "",
					student.getConstitution() + "",
					student.getInitiative() + "",
					""
				};
			} else {
				content[i] = new String[] {
					student.getId(),
					student.getStrength() + "",
					student.getDexterity() + "",
					student.getResilience() + "",
					student.getConstitution() + "",
					student.getInitiative() + "",
					student.getStrategy().toString()
				};
			}
			i++;
		}
		
		table.setModel(new DefaultTableModel(
			content,
			new String[] {
				"Student ID", "Strength", "Dexterity", "Resilience", "Constitution", "Initiative", "Strategy"
			}
		));
		
		JLabel selectedReservistsLabel = new JLabel("Number of selected reservists : " + this.game.getPlayerTurn().getReservists().size());
		selectedReservistsLabel.setBounds(0, 294, 197, 14);
		panel.add(selectedReservistsLabel);
		
		JLabel selectedElitesLabel = new JLabel("Number of selected elites : " + this.game.getPlayerTurn().getEliteStudents().size());
		selectedElitesLabel.setBounds(217, 294, 197, 14);
		panel.add(selectedElitesLabel);
		
		JLabel selectedMastersLabel = new JLabel("Number of selected masters : " + this.game.getPlayerTurn().getMasterStudents().size());
		selectedMastersLabel.setBounds(0, 324, 197, 14);
		panel.add(selectedMastersLabel);
		
		if (this.game != null) {
			JLabel selectedStudents = new JLabel("Allocate points to " + this.game.getPlayerTurn().getProgram().toString() + " students");
			
			selectedStudents.setFont(new Font("Tahoma", Font.BOLD, 16));
			selectedStudents.setHorizontalAlignment(SwingConstants.CENTER);
			selectedStudents.setBounds(0, 0, 564, 24);
			panel.add(selectedStudents);
		}
		return panel;
	}
	
	private JPanel allocationStatePanel() {
		JPanel panel;
		JTable table;
		JScrollPane scrollPane;
		
		panel = new JPanel();
		panel.setBounds(0, 0, 564, 338);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 31, 564, 309);
		panel.add(scrollPane);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);
		table.setBounds(0, 31, 564, 309);
		
		int rows = 0;
		
		for (Iterator<Area> iterator = this.game.getAreas().iterator(); iterator.hasNext();) {	
			Area area = (Area) iterator.next();
			if (area.getOccupyingStudents().size() > rows) {
				rows = area.getOccupyingStudents().size();
			}
		}
		
		String[][] content = new String[rows][this.game.getAreas().size()];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < this.game.getAreas().size(); j++) {
				String st = "-";
				if (this.game.getAreas().get(j).getOccupyingStudents().size() > i && this.game.getAreas().get(j).getOccupyingStudents().get(i).getEcts() > 0) {
					st = this.game.getAreas().get(j).getOccupyingStudents().get(i).getId() + " (" +
						this.game.getAreas().get(j).getOccupyingStudents().get(i).getEcts() + ")";
					content[i][j] = st;
				}
				content[i][j] = st;
			}
		}
		
		String[] areasLabel = new String[this.game.getAreas().size()];
		
		int i = 0;
		
		for (Area area: this.game.getAreas()) {
			areasLabel[i] = area.getName();
			i++;
		}
		
		table.setModel(new DefaultTableModel(
			content,
			areasLabel
		));
		
		if (this.game != null) {
			JLabel selectedStudents = new JLabel("Place " + this.game.getPlayerTurn().getProgram().toString() + " students");
			
			selectedStudents.setFont(new Font("Tahoma", Font.BOLD, 16));
			selectedStudents.setHorizontalAlignment(SwingConstants.CENTER);
			selectedStudents.setBounds(0, 0, 564, 24);
			panel.add(selectedStudents);
		}
		return panel;
	}
	
	private JPanel battleStatePanel() {
		JPanel panel;
		
		panel = new JPanel();
		
		return panel;
	}
	
	private JPanel truceStatePanel() {
		JPanel panel;
		JTable table;
		JScrollPane scrollPane;
		
		panel = new JPanel();
		panel.setBounds(0, 0, 564, 338);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 31, 564, 252);
		panel.add(scrollPane);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);
		table.setBounds(0, 31, 564, 252);
		
		int rows = 0;
		
		for (Iterator<Area> iterator = this.game.getAreas().iterator(); iterator.hasNext();) {	
			Area area = (Area) iterator.next();
			if (area.getOccupyingStudents().size() > rows) {
				rows = area.getOccupyingStudents().size();
			}
		}
		
		String[][] content = new String[rows][this.game.getAreas().size()];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < this.game.getAreas().size(); j++) {
				String st = "-";
				if (this.game.getAreas().get(j).getOccupyingStudents().size() > i && this.game.getAreas().get(j).getOccupyingStudents().get(i).getEcts() > 0) {
					st = this.game.getAreas().get(j).getOccupyingStudents().get(i).getId() + " (" +
						this.game.getAreas().get(j).getOccupyingStudents().get(i).getEcts() + ")";
					content[i][j] = st;
				}
				content[i][j] = st;
			}
		}
		
		String[] areasLabel = new String[this.game.getAreas().size()];
		
		int i = 0;
		
		for (Area area: this.game.getAreas()) {
			areasLabel[i] = area.getId();
			if (area.isControlled(game)) {
				areasLabel[i] += " (" + area.playerInControl(game).getProgram().toString() + ")";
			}
			i++;
		}
		
		table.setModel(new DefaultTableModel(
			content,
			areasLabel
		));
		
		if (this.game != null) {
			JLabel selectedStudents = new JLabel("It's " + this.game.getPlayerTurn().getProgram().toString() + "'s turn");
			
			selectedStudents.setFont(new Font("Tahoma", Font.BOLD, 16));
			selectedStudents.setHorizontalAlignment(SwingConstants.CENTER);
			selectedStudents.setBounds(0, 0, 564, 24);
			panel.add(selectedStudents);
		}
		
		switchButton = new JButton("Switch player");
		switchButton.setBounds(0, 315, 275, 23);
		switchButton.addActionListener(new SwitchPlayerButtonController(this.game, this));
		panel.add(switchButton);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(285, 315, 279, 23);
		confirmButton.addActionListener(new ConfirmButtonController(this.game, this));
		panel.add(confirmButton);
		
		return panel;
	}
	
	private JPanel endStatePanel() {
		JPanel panel = new JPanel();
		panel = new JPanel();
		panel.setBounds(0, 0, 564, 338);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1;
		lblNewLabel_1 = new JLabel("Player " + this.game.getWinner().getProgram().toString() + " wins the game!");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 564, 338);
		panel.add(lblNewLabel_1);
		
		JButton restartButton = new JButton("Restart game");
		restartButton.setBounds(212, 183, 140, 30);
		restartButton.addActionListener(new RestartButtonController(this.game, this));
		panel.add(restartButton);
		
		return panel;
	}
}