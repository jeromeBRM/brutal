package brutal.model.states;

import brutal.model.core.IGame;
import brutal.model.core.IState;
import brutal.model.core.IStudent;
import brutal.model.core.Player;

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
	public void executeCommandLine(String[] tokens, IGame game) {
		switch (tokens[0]) {
			case "points":
				this.allocatePoints(game, tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
				break;
			case "reservist":
				this.addStudentAsReservist(game, tokens[1]);
				break;
			case "elite":
				this.setEliteStudent(game, tokens[1]);
				break;
			case "master":
				this.setMasterStudent(game, tokens[1]);
				break;
			case "strategy":
				this.setStrategyToStudent(game, tokens[1], tokens[2]);
				break;
		}
	}
	
	private void allocatePoints(IGame game, String id, int strength, int dexterity, int resilience, int constitution, int initiative) {
		if (game.getPlayerTurn().getTotalSpentAttributePoints()
				+ strength + dexterity + resilience + constitution + initiative <= SetupState.MAX_POINTS_TO_ALLOCATE &&
				strength > 0 && dexterity > 0 && resilience > 0 && constitution > 0 && initiative > 0) {
			game.getPlayerTurn().addAttributesToStudent(id, strength, dexterity, resilience, constitution, initiative);	
		}
	}
	
	private void addStudentAsReservist(IGame game, String id) {
		if (game.getPlayerTurn().getReservists().size() < SetupState.MAX_SELECTED_RESERVISTS) {
			game.getPlayerTurn().addStudentAsReservist(id);
		}
	}
	
	private void setEliteStudent(IGame game, String id) {
		if (game.getPlayerTurn().getEliteStudents().size() < SetupState.MAX_SELECTED_ELITE_STUDENTS) {
			game.getPlayerTurn().setEliteStudent(id);
		}
	}
	
	private void setMasterStudent(IGame game, String id) {
		if (game.getPlayerTurn().getMasterStudents().size() < SetupState.MAX_SELECTED_MASTER_STUDENTS) {
			game.getPlayerTurn().setMasterStudent(id);
		}
	}
	
	private void setStrategyToStudent(IGame game, String id, String strat) {
		if (!this.allStudentsHaveStrategies(game.getPlayerTurn())) {
			game.getPlayerTurn().setStrategyToStudent(id, strat);
		}
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
			player.getMasterStudents().size() == MAX_SELECTED_MASTER_STUDENTS &&
			this.allStudentsHaveStrategies(player)
		);
	}
	
	private boolean allStudentsHaveStrategies(Player player) {
		for (IStudent student : player.getAllStudents()) {
			if (student.getStrategy() == null) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "setup state";
	}
}