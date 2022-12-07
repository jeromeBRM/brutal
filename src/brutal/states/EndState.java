package brutal.states;

import brutal.*;

public class EndState extends State {

	public EndState(IGame game) {
		super(game);
	}

	@Override
	public void executeCommandLine(String[] tokens, IGame game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IState getNextState(IGame game) {
		return new StartState(game);
	}
}