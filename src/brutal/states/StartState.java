package brutal.states;

import brutal.IGame;
import brutal.IState;

public class StartState extends State {

	private static final int MAX_PLAYERS = 2;
	
	public StartState(IGame game) {
		super(game);
	}

	@Override
	public void executeCommandLine(String command, IGame game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean commandIsValid(String command) {
		// TODO Auto-generated method stub
		return false;
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