package brutal.states;

import brutal.IGame;
import brutal.IState;
import brutal.Player;

public class SetupState extends State {

	private static final int MAX_POINTS_TO_ALLOCATE = 400;
	private static final int MAX_SELECTED_RESERVISTS = 5;
	private static final int MAX_SELECTED_ELITE_STUDENTS = 4;
	private static final int MAX_SELECTED_MASTER_STUDENTS = 1;
	
	public SetupState(IGame game) {
		super(game);
		game.setPlayerTurn(game.getPlayers().iterator().next());
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
	public void updateGameState(IGame game) {
		super.updateGameState(game);
		
		if (this.playerIsSetup(game.getPlayerTurn())) {
			game.switchPlayerTurn();
		}
	}

	@Override
	protected IState getNextState(IGame game) {
		
		for (Player player : game.getPlayers()) {
			if (!this.playerIsSetup(player)) {
				return game.getState();
			}
		}	
		return new AllocationState(game);
	}
	
	private boolean playerIsSetup(Player player) {
		return (
			player.getTotalSpentAttributePoints() == MAX_POINTS_TO_ALLOCATE &&
			player.getReservists().size() == MAX_SELECTED_RESERVISTS &&
			player.getEliteStudents().size() == MAX_SELECTED_ELITE_STUDENTS &&
			player.getMasterStudents().size() == MAX_SELECTED_MASTER_STUDENTS
		);
	}
	
	@Override
	public String toString() {
		return "setup state";
	}
}
