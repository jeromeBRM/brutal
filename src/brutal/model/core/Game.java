package brutal.model.core;
import brutal.model.states.*;
import brutal.model.students.Student;
import brutal.view.GUI;

import java.util.*;

public class Game implements IGame {

	private static IGame game;
	
	private IState state;
	private List<Player> players;
	private Player playerTurn;
	private List<Area> areas;
	
	private GUI gui;
	
	public Game() {
		this.setState(new StartState(this));
	}

	@Override
	public void initialize() {
		Student.resetCounter();
		this.players = new LinkedList<Player>();
		
		this.areas = new LinkedList<Area>();
		this.areas.add(new Area("BBL", "Bibliothèque"));
		this.areas.add(new Area("BDE", "Bureau des étudiants"));
		this.areas.add(new Area("QAD", "Quartier administratif"));
		this.areas.add(new Area("HID", "Halles industrielles"));
		this.areas.add(new Area("HSP", "Halle sportive"));
	}

	@Override
	public void setState(IState state) {
		this.state = state;
		state.start(this);
	}

	@Override
	public void createPlayer(Program program) {
		for(Player player : this.getPlayers()) {
			if (player.getProgram() == program)
				return;
		}
		this.players.add(new Player(program));
	}

	@Override
	public void createStudent(Player player, int strength, int dexterity, int resilience, int constitution, int initiative) {
		
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
	public IState getState() {
		return this.state;
	}

	@Override
	public List<Player> getPlayers() {
		return this.players;
	}

	@Override
	public Player getPlayerTurn() {
		return Game.getInstance().getPlayers().get(0);
	}

	@Override
	public void switchPlayerTurn() {
		Collections.rotate(Game.getInstance().getPlayers(), 1);
		System.out.println("switched player turn to " + this.getPlayerTurn().getProgram());
	}

	@Override
	public boolean isStudentBelongingToPlayer(IStudent student, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Area> getAreas() {
		return this.areas;
	}
	
	@Override
	public Area getAreaById(String id) {
		Area area = null;

		for (Area a : this.getAreas()) {
			if (a.getId().equals(id)) {
				area = a;
			}
		}
		
		return area;
	}

	@Override
	public Area getAreaByName(String name) {
		Area area = null;

		for (Area a : this.getAreas()) {
			if (a.getName().equals(name)) {
				area = a;
			}
		}
		
		return area;
	}
	
	@Override
	public Area getAreaByStudent(IStudent student) {
		Area area = null;

		for (Area a : this.getAreas()) {
			if (a.getOccupyingStudents().contains(student)) {
				area = a;
			}
		}
		
		return area;
	}

	public static synchronized IGame getInstance() {
		if (Game.game == null) {
			Game.game = new Game();
		}
		return Game.game;
	}

	@Override
	public void setPlayerTurn(Player player) {
		this.playerTurn = player;
	}
	
	@Override
	public Player getWinner() {
		Player winner = null;
		for (Iterator<Player> iterator = game.getPlayers().iterator(); iterator.hasNext();) {	
			Player player = (Player) iterator.next();
			int controlledAreas = 0;
			for (Iterator<Area> iterator2 = game.getAreas().iterator(); iterator2.hasNext();) {	
				Area area = (Area) iterator2.next();
				if (area.isControlledByPlayer(player, game)) {
					controlledAreas++;
				}
			}
			if (controlledAreas >= EndState.AREAS_TO_CONTROL) {
				winner = player;
			}
		}
		return winner;
	}
	
	@Override
	public String toString() {
		String g = "game is in " + this.game.getState().toString();
		return g;
	}
	
	public int getMaxStudentsOnArea() {
		int max = 0;
		for (Iterator<Area> iterator = this.getAreas().iterator(); iterator.hasNext();) {	
			Area area = (Area) iterator.next();
			if (area.getOccupyingStudents().size() > max) {
				max = area.getOccupyingStudents().size();
			}
		}
		return max;
	}

	@Override
	public void notifyView() {
		this.gui.update();
	}

	@Override
	public void addView(GUI gui) {
		this.gui = gui;
		this.notifyView();
	}
}