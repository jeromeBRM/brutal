package brutal.model.states;

import brutal.model.core.IGame;
import brutal.model.core.IState;
import brutal.model.core.IStudent;

public class TruceState extends State {

	private boolean confirmed;
	
	public TruceState(IGame game) {
		super(game);
		this.confirmed = false;
	}

	@Override
	public void executeCommandLine(String[] tokens, IGame game) {
		switch (tokens[0]) {
		case "deploy":
			this.placeReservist(game, tokens[1], tokens[2]);
			break;
		case "redeploy":
			this.redeployStudent(game, tokens[1], tokens[2], tokens[3]);
			break;
		case "change":
			game.switchPlayerTurn();
			break;
		case "confirm":
			this.confirmed = true;
			break;
		}
	}
	
	private void placeReservist(IGame game, String studentId, String areaId){
		IStudent reservist = game.getPlayerTurn().getStudentById(studentId, game.getPlayerTurn().getReservists());
		if (reservist == null) {
			return;
		}
		if (!game.getAreaById(areaId).getOccupyingStudents().contains(reservist)) {
			if(game.getAreaByStudent(reservist) != null) {
				return;
			}
			game.getAreaById(areaId).addStudent(reservist);
		}
		System.out.println(game);
	}
	
	private void redeployStudent(IGame game, String studentId, String areaId, String newStrategy){
		IStudent student = game.getPlayerTurn().getStudentById(studentId, game.getPlayerTurn().getAllStudents());
		if (student == null
				|| student.getEcts() <= 0
				|| !game.getAreaByStudent(student).isControlled(game)
				|| game.getAreaByStudent(student).numberOfActiveStudentsOnArea(game.getPlayerTurn()) <= 1) {
			return;
		}
		if (!game.getAreaById(areaId).getOccupyingStudents().contains(student)) {
			if(game.getAreaByStudent(student) != null) {
				game.getAreaByStudent(student).removeStudent(student);
			}
			game.getAreaById(areaId).addStudent(student);
		}
		if (newStrategy != null) {
			student.getOwner(game).setStrategyToStudent(student.getId(), newStrategy);
		}
		System.out.println(game);
	}

	@Override
	protected IState getNextState(IGame game) {
		if (this.confirmed) {
			return new BattleState(game);
		}
		else {
			return game.getState();	
		}
	}
	
	@Override
	public String toString() {
		return "truce state";
	}
}