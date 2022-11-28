package brutal;

import java.util.LinkedList;

public class Player {

	private Program program;
	private LinkedList<IStudent> students;
	private LinkedList<IStudent> reservists;
	
	public Player(Program program) {
		this.program = program;
		this.students = new LinkedList<IStudent>();
		this.reservists = new LinkedList<IStudent>();
	}
	
	public Program getProgram() {
		return this.program;
	}

	public LinkedList<IStudent> getStudents() {
		return this.students;
	}
	
	public LinkedList<IStudent> getReservists() {
		return this.reservists;
	}
	
	public int getTotalSpentAttributePoints() {
		int total = 0;
		return total;
	}
}