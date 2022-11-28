package brutal;

import java.util.List;

public class Game implements IGame {

	
	
	public Game() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setState(IState state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPlayer(Program program) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createStudent(Player player, int strength, int dexterity, int resilience, int constitution,
			int initiative) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStudentAsReservist(IStudent student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEliteStudent(IStudent student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMasterStudent(IStudent student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeStudentOnArea(IStudent student, Area area) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeReservist(IStudent reservist, Area area) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerTurn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void switchPlayerTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStudentBelongingToPlayer(IStudent student, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Area> getAreas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Area getAreaByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getAreaOwner(Area area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

}
