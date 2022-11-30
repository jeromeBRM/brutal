package brutal.states;

import brutal.IGame;
import brutal.IState;

public class AllocationState extends State {

	public AllocationState(IGame game) {
		super(game);
	}

	@Override
	public void executeCommandLine(String[] tokens, IGame game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IState getNextState(IGame game) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return "allocation state";
	}
}