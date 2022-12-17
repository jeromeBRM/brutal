package brutal.model.states;

import brutal.model.core.IGame;
import brutal.model.core.IState;
import brutal.model.core.Program;

public class StartState extends State {

	private static final int MAX_PLAYERS = 2;
	
	public StartState(IGame game) {
		super(game);
		game.initialize();
	}

	@Override
	public void executeCommandLine(String[] tokens, IGame game) {
		if (Program.valueOf(tokens[0]) != null) {
			game.createPlayer(Program.valueOf(tokens[0]));
		}
	}

	@Override
	protected IState getNextState(IGame game) {
		if (game.getPlayers().size() == StartState.MAX_PLAYERS)
			return new SetupState(game);
		else
			return game.getState();
	}

	@Override
	public String toString() {
		return "start state";
	}
}