package brutal.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import brutal.model.core.IGame;
import brutal.view.GUI;

public class UserInputController implements ActionListener {

	private IGame game;
	private GUI gui;
	
	public UserInputController(IGame game, GUI gui) {
		this.game = game;
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] commands = this.gui.getTypedCommand().split("\n");
		for (String command : commands) {
			game.getState().inputCommand(command, game);	
		}
		this.gui.resetCommand();
	}
}