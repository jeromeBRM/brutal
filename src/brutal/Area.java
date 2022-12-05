package brutal;

import java.util.*;

import brutal.comparators.*;

public class Area {
	private String id;
	private String name;
	private LinkedList<IStudent> occupyingStudents;
	
	public Area(String id, String name) {
		this.id = id;
		this.name = name;
		this.occupyingStudents = new LinkedList<IStudent>();
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public LinkedList<IStudent> getOccupyingStudents() {
		return this.occupyingStudents;
	}
	
	public LinkedList<IStudent> getStudentsByInitiative() {
		LinkedList<IStudent> students = this.getOccupyingStudents();
		Collections.sort(students, new SortStudentsByInitiative());
		return students;
	}
	
	public LinkedList<IStudent> getStudentsByEcts() {
		LinkedList<IStudent> students = this.getOccupyingStudents();
		Collections.sort(students, new SortStudentsByEcts());
		return students;
	}
	
	public boolean isClaimedByPlayer(Player player) {
		for (Iterator<IStudent> iterator = this.getOccupyingStudents().iterator(); iterator.hasNext();) {	
			IStudent student = (IStudent) iterator.next();
			if (player.getStudents().contains(student)) {
				return true;
			}
		}
		return false;
	}
	
	public int numberOfStudentsOnArea(Player player) {
		int numberOfStudents = 0;
		for (Iterator<IStudent> iterator = this.getOccupyingStudents().iterator(); iterator.hasNext();) {	
			IStudent student = (IStudent) iterator.next();
			if (player.getStudents().contains(student)) {
				numberOfStudents++;
			}
		}
		return numberOfStudents;
	}
	
	public int getTotalEcts() {
		int total = 0;
		
		for (IStudent student : this.getOccupyingStudents()) {
			total += student.getEcts();
		}
		
		return total;
	}
	
	public void addStudent(IStudent student) {
		this.getOccupyingStudents().add(student);
	}
	
	public void removeStudent(IStudent student) {
		this.getOccupyingStudents().remove(student);
	}
}