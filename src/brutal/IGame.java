package brutal;

import java.util.*;

public interface IGame {
	public void initialize();
	
	public void setState(IState state);
	
	public void createPlayer(Program program);
	
	public void createStudent(Player player, int strength, int dexterity, int resilience, int constitution, int initiative);
	
	public void setStudentAsReservist(IStudent student);
	
	public void setEliteStudent(IStudent student);
	
	public void setMasterStudent(IStudent student);
	
	public void placeStudentOnArea(IStudent student, Area area);
	
	public void placeReservist(IStudent reservist, Area area);
	
	public IState getState();
	
	public List<Player> getPlayers();
	
	public Player getPlayerTurn();
	
	public void switchPlayerTurn();
	
	public boolean isStudentBelongingToPlayer(IStudent student, Player player);
	
	public Set<Area> getAreas();
	
	public Area getAreaByName(String name);
	
	public Player getAreaOwner(Area area);
	
	public Player getWinner();

	public void setPlayerTurn(Player player);
}