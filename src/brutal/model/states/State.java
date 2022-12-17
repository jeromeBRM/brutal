package brutal.model.states;

import brutal.model.core.IGame;
import brutal.model.core.IState;

public abstract class State implements IState {

	public State(IGame game) {
		System.out.println("game state updated to " + this.toString());
	}
	
	@Override
	public void updateGameState(IGame game) {
		game.setState(this.getNextState(game));
		game.notifyView();
	}
	
	@Override
	public void inputCommand(String command, IGame game) {
		try {
			String[] tokens = command.split(" ");
			this.executeCommandLine(tokens, game);
			this.updateGameState(game);
		} catch (Exception e) {
			System.out.println("Incorrect input!");
			e.printStackTrace();
		}
	}

	protected abstract IState getNextState(IGame game);
	
	public void start(IGame game) {
		return;
	}
}