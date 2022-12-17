package brutal.model.states;

import java.util.*;

import brutal.model.core.*;

public class AllocationState extends State {
	
	public AllocationState(IGame game) {
		super(game);
	}

	@Override
	public void executeCommandLine(String[] tokens, IGame game) {
		switch (tokens[0]) {
		case "place":
			this.placeStudent(game, tokens[1], tokens[2]);
			break;
		}
	}

	private void placeStudent(IGame game, String studentId, String areaId){
		IStudent student = game.getPlayerTurn().getStudentById(studentId, game.getPlayerTurn().getStudents());
		if (student == null) {
			return;
		}
		if (!game.getAreaById(areaId).getOccupyingStudents().contains(student)) {
			if(game.getAreaByStudent(student) != null) {
				game.getAreaByStudent(student).removeStudent(student);
			}
			game.getAreaById(areaId).addStudent(student);
		}
	}
	
	@Override
	public void updateGameState(IGame game) {
		super.updateGameState(game);
		
		if (this.isReady(game, game.getPlayerTurn())) {
			game.switchPlayerTurn();
		}
	}

	@Override
	protected IState getNextState(IGame game) {
		int readyPlayers = 0;
		for (Iterator<Player> iterator = game.getPlayers().iterator(); iterator.hasNext();) {	
			Player player = (Player) iterator.next();
			if (this.isReady(game, player)) {
				readyPlayers++;
			}
		}
		if (readyPlayers == game.getPlayers().size()) {
			return new BattleState(game);
		}
		else {
			return game.getState();
		}
	}
	
	private boolean isReady(IGame game, Player player) {
		int claimedAreas = 0;
		int numberOfPlacedStudents = 0;
		for (Iterator<Area> iterator = game.getAreas().iterator(); iterator.hasNext();) {	
			Area area = (Area) iterator.next();
			if (area.isClaimedByPlayer(player)) {
				claimedAreas++;
				numberOfPlacedStudents += area.numberOfStudentsOnArea(player);
			}
		}
		return claimedAreas == game.getAreas().size() && numberOfPlacedStudents == player.getStudents().size();
	}
	
	@Override
	public String toString() {
		return "allocation state";
	}
}