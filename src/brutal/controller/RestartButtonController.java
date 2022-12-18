package brutal.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import brutal.model.core.IGame;
import brutal.view.GUI;

public class RestartButtonController implements ActionListener {

	private IGame game;
	private GUI gui;
	
	public RestartButtonController(IGame game, GUI gui) {
		this.game = game;
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.getState().inputCommand("restart", game);
	}
}