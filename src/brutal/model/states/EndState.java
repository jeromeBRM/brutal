package brutal.model.states;

import brutal.model.core.*;

public class EndState extends State {

	public static final int AREAS_TO_CONTROL = 3;
	
	private boolean confirmed;
	
	public EndState(IGame game) {
		super(game);
		this.confirmed = false;
	}
	
	@Override
	public void start(IGame game) {
		System.out.println("player " + game.getWinner().getProgram().toString() + " wins the game!");
	}

	@Override
	public void executeCommandLine(String[] tokens, IGame game) {
		switch (tokens[0]) {
		case "restart":
			this.confirmed = true;
			break;
		}
	}

	@Override
	protected IState getNextState(IGame game) {
		if (this.confirmed) {
			return new StartState(game);
		}
		else {
			return game.getState();
		}
	}

	@Override
	public String toString() {
		return "end state";
	}
}