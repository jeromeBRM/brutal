package brutal;

import java.util.LinkedList;
import java.util.Set;

import brutal.students.Student;

public class Player {

	private Program program;
	private LinkedList<IStudent> students;
	private LinkedList<IStudent> reservists;
	
	public Player(Program program) {
		this.program = program;
		this.students = new LinkedList<IStudent>();
		this.reservists = new LinkedList<IStudent>();
		
		for (int i = 0 ; i < 20 ; i++) {
			this.students.add(new Student());
		}
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
	
	public Set<Player> getEliteStudents() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Set<Player> getMasterStudents() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getTotalSpentAttributePoints() {
		int total = 0;
		return total;
	}
}