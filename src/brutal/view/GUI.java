package brutal.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
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

import brutal.controller.UserInputController;
import brutal.model.core.Game;
import brutal.model.core.IGame;
import brutal.model.core.IStudent;

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

public class GUI extends JFrame {
	private IGame game;
	
	private JLabel lblNewLabel;
	private JTextArea userInputCommand;
	private JButton userInputButton;
	
	public GUI(IGame game) {
		this.game = game;
		
		setBounds(100, 100, 600, 550);
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("Brutal");
		getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 564, 14);
		getContentPane().add(layeredPane);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("state");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		layeredPane.add(lblNewLabel, BorderLayout.NORTH);
		
		this.userInputButton = new JButton("Enter");
		userInputButton.setBounds(502, 385, 72, 115);
		userInputButton.addActionListener(new UserInputController(this.game, this));
		getContentPane().add(userInputButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 385, 482, 115);
		getContentPane().add(scrollPane);
		
		userInputCommand = new JTextArea();
		scrollPane.setViewportView(userInputCommand);
		
		setVisible(true);
	}
	
	public void update() {
		this.updateState();
	}
	
	private void updateState() {
		lblNewLabel.setText(this.game.getState().toString());
	}
	
	public String getTypedCommand() {
		return this.userInputCommand.getText();
	}

	public void resetCommand() {
		this.userInputCommand.setText("");
	}
}