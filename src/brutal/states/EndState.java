package brutal.states;

import brutal.IGame;
import brutal.IState;

public class EndState extends State {

	public EndState(IGame game) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
