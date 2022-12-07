package brutal.states;

import brutal.*;

public class EndState extends State {

	private boolean confirmed;
	
	public EndState(IGame game) {
		super(game);
		this.confirmed = false;
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