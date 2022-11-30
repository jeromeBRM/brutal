package brutal.states;

import brutal.IGame;
import brutal.IState;

public abstract class State implements IState {

	public State(IGame game) {
		System.out.println("game state updated to " + this.toString());
	}
	
	@Override
	public void updateGameState(IGame game) {
		game.setState(this.getNextState(game));
	}

	protected abstract IState getNextState(IGame game);
}