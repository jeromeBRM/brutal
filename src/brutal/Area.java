package brutal;

import java.util.*;

import brutal.comparators.*;

public class Area {
	private String name;
	private LinkedList<IStudent> occupyingStudents;
	
	public Area(String name) {
		this.name = name;
		this.occupyingStudents = new LinkedList<IStudent>();
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