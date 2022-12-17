package brutal.model.core;
import brutal.model.states.*;
import brutal.model.students.Student;

import java.util.*;

public class Game implements IGame {

	private static IGame game;
	
	private IState state;
	private List<Player> players;
	private Player playerTurn;
	private List<Area> areas;
	
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
	public String toString() {
		String g = "\n";
		for (Iterator<Area> iterator = this.getAreas().iterator(); iterator.hasNext();) {	
			Area area = (Area) iterator.next();
			g += "| " + area.getName() + " " + "-".repeat(26 - area.getName().length()) + " ";
		}
		g += "|\n";
		for (int i = 0; i < this.getMaxStudentsOnArea(); i++) {
			for (int j = 0; j < this.getAreas().size(); j++) {
				if (this.getAreas().get(j).getOccupyingStudents().size() > i && this.getAreas().get(j).getOccupyingStudents().get(i).getEcts() > 0) {
					String st = this.getAreas().get(j).getOccupyingStudents().get(i).getId() + " (" +
						this.getAreas().get(j).getOccupyingStudents().get(i).getEcts() + ")";
					g += "| " + st + " ".repeat(26 - st.length()) + "  ";
				}
				else {
					String st = "-";
					g += "| " + st + " ".repeat(26 - st.length()) + "  ";
				}
			}
			g += "|\n";
		}
		g += "\n";
		for (Iterator<Area> iterator = this.getAreas().iterator(); iterator.hasNext();) {	
			Area area = (Area) iterator.next();
			if (area.playerInControl(this) != null)
				g += "  Controlled by " + area.playerInControl(this) + " " + " ".repeat(26 - ("Controlled by " + area.playerInControl(this).getProgram().toString()).length()) + " ";
			else
				g += "   " + " ".repeat(26 - " ".length()) + "  ";
		}
		g += " \n";
		return g;
	}
	
	private int getMaxStudentsOnArea() {
		int max = 0;
		for (Iterator<Area> iterator = this.getAreas().iterator(); iterator.hasNext();) {	
			Area area = (Area) iterator.next();
			if (area.getOccupyingStudents().size() > max) {
				max = area.getOccupyingStudents().size();
			}
		}
		return max;
	}
}