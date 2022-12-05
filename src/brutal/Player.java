package brutal;

import java.util.*;

import brutal.strategies.DefensiveStrategy;
import brutal.strategies.OffensiveStrategy;
import brutal.strategies.RandomStrategy;
import brutal.students.*;

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
	
	public IStudent getStudentById(String id, LinkedList<IStudent> students) {
		IStudent student = null;
		
		for (IStudent s : students) {
			if (s.getId().equals(id)) {
				student = s;
			}
		}
		return student;
	}

	public LinkedList<IStudent> getStudents() {
		return this.students;
	}
	
	public LinkedList<IStudent> getReservists() {
		return this.reservists;
	}
	
	public LinkedList<IStudent> getAllStudents() {
		LinkedList<IStudent> all = (LinkedList<IStudent>)this.getStudents().clone();
		all.addAll((LinkedList<IStudent>)this.getReservists().clone());
		return all;
	}
	
	public void addStudentAsReservist(String id) {
		if (this.getStudentById(id, this.getStudents()) == null) {
			return;
		}
		if (this.getReservists().contains(this.getStudentById(id, this.getReservists()))) {
			return;
		}
		this.getReservists().add(this.getStudentById(id, this.getStudents()));
		this.getStudents().remove(this.getStudentById(id, this.getStudents()));
	}
	
	public void setEliteStudent(String id) {
		if (this.getStudentById(id, this.getAllStudents()) == null) {
			return;
		}
		IStudent target = this.getStudentById(id, this.getAllStudents());
		IStudent student = target.setElite();
		
		if (this.getStudents().contains(target)) {
			this.getStudents().remove(target);	
			this.getStudents().add(student);
		}
		else {
			this.getReservists().remove(this.getStudentById(id, this.getReservists()));
			this.getReservists().add(student);
		}
	}
	
	public void setMasterStudent(String id) {
		if (this.getStudentById(id, this.getAllStudents()) == null) {
			return;
		}
		IStudent target = this.getStudentById(id, this.getAllStudents());
		IStudent student = target.setMaster();
		
		if (this.getStudents().contains(target)) {
			this.getStudents().remove(target);
			this.getStudents().add(student);
		}
		else {
			this.getReservists().remove(this.getStudentById(id, this.getReservists()));
			this.getReservists().add(student);
		}
	}
	
	public Set<IStudent> getEliteStudents() {
		Set<IStudent> eliteStudents = new HashSet<IStudent>();
		for (Iterator<IStudent> iterator = this.getAllStudents().iterator(); iterator.hasNext();) {
			IStudent student = (IStudent) iterator.next();
			if (student instanceof EliteStudent) {
				eliteStudents.add(student);	
			}
		}
		return eliteStudents;
	}
	
	public Set<IStudent> getMasterStudents() {
		Set<IStudent> masterStudents = new HashSet<IStudent>();
		for (Iterator<IStudent> iterator = this.getAllStudents().iterator(); iterator.hasNext();) {
			IStudent student = (IStudent) iterator.next();
			if (student instanceof MasterStudent) {
				masterStudents.add(student);	
			}
		}
		return masterStudents;
	}
	
	public void addAttributesToStudent(String id, int strength, int dexterity, int resilience, int constitution, int initiative) {
		this.getStudentById(id, this.getAllStudents()).addAttributes(strength, dexterity, resilience, constitution, initiative);
	}
	
	public int getTotalSpentAttributePoints() {
		int total = 0;
		
		for (Iterator<IStudent> iterator = this.getAllStudents().iterator(); iterator.hasNext();) {
			IStudent student = (IStudent) iterator.next();
			total += student.getTotalAttributes();
		}
		return total;
	}

	public void setStrategyToStudent(String id, String strat) {
		IStrategy strategy;
		
		switch (strat) {
			case "offensive":
				strategy = new OffensiveStrategy();
				break;
			case "defensive":
				strategy = new DefensiveStrategy();
				break;
			case "random":
				strategy = new RandomStrategy();
				break;
			default :
				strategy = new RandomStrategy();
		}
		this.getStudentById(id, this.getAllStudents()).setStrategy(strategy);
	}
}