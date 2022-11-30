package brutal.students;

import brutal.Area;
import brutal.IGame;
import brutal.IStrategy;
import brutal.IStudent;

public class Student implements IStudent {

	private int id;
	private static int studentCount;
	
	private int ects;
	private int dexterity;
	private int strength;
	private int resilience;
	private int constitution;
	private int initiative;
	private IStrategy strategy;

	private static final int MAX_ATTRIBUTE = 10;
	private static final int MAX_HEALTH_ATTRIBUTE = 30;
	
	public Student() {
		this.setId(Student.studentCount);
		
		this.setEcts(30);
		this.setAttributes(0, 0, 0, 0, 0);
	}
	
	@Override
	public String getId() {
		return "ETU" + this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
		Student.studentCount++;
	}

	@Override
	public void setAttributes(int strength, int dexterity, int resilience, int constitution, int initiative) {
		this.setStrength(strength);
		this.setDexterity(dexterity);
		this.setResilience(resilience);
		this.setConstitution(constitution);
		this.setInitiative(initiative);
	}
	
	@Override
	public void addAttributes(int strength, int dexterity, int resilience, int constitution, int initiative) {
		this.setAttributes(this.getStrength() + strength, this.getDexterity() + dexterity, this.getResilience() + resilience, this.getConstitution() + constitution, this.getInitiative() + initiative);
	}
	
	@Override
	public void useStrategy(Area area, IGame game) {
		this.getStrategy().use(this, area, game);
	}
	
	@Override
	public void setEcts(int ects) {
		if (ects >= Student.MAX_HEALTH_ATTRIBUTE) {
			this.ects = Student.MAX_HEALTH_ATTRIBUTE;		
		}
	}

	@Override
	public void setStrategy(IStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void setStrength(int strength) {
		this.strength = strength;
		if (strength >= Student.MAX_ATTRIBUTE) {
			this.strength = Student.MAX_ATTRIBUTE;
		}
	}

	@Override
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
		if (dexterity >= Student.MAX_ATTRIBUTE) {
			this.dexterity = Student.MAX_ATTRIBUTE;	
		}
	}

	@Override
	public void setResilience(int resilience) {
		this.resilience = resilience;
		if (resilience >= Student.MAX_ATTRIBUTE) {
			this.resilience = Student.MAX_ATTRIBUTE;	
		}
	}

	@Override
	public void setConstitution(int constitution) {
		this.constitution = constitution;
		if (constitution >= Student.MAX_HEALTH_ATTRIBUTE) {
			this.constitution = Student.MAX_HEALTH_ATTRIBUTE;	
		}
	}

	@Override
	public void setInitiative(int initiative) {
		this.initiative = initiative;
		if (initiative >= Student.MAX_ATTRIBUTE) {
			this.initiative = Student.MAX_ATTRIBUTE;	
		}
	}

	@Override
	public void heal(int ects) {
		this.setEcts(this.getEcts() + ects);
	}

	@Override
	public void damage(int ects) {
		this.setEcts(this.getEcts() - ects);
	}

	@Override
	public int getEcts() {
		return this.ects;
	}

	@Override
	public int getStrength() {
		return this.strength;
	}

	@Override
	public int getDexterity() {
		return this.dexterity;
	}

	@Override
	public int getResilience() {
		return this.resilience;
	}

	@Override
	public int getConstitution() {
		return this.constitution;
	}

	@Override
	public int getInitiative() {
		return this.initiative;
	}

	@Override
	public IStrategy getStrategy() {
		return this.strategy;
	}

	@Override
	public int getTotalAttributes() {
		return this.strength + this.dexterity + this.resilience + this.constitution + this.initiative;
	}

	@Override
	public IStudent setElite() {
		IStudent student = new EliteStudent();
		student.setId(this.id);
		student.setAttributes(this.strength, this.dexterity, this.resilience, this.constitution, this.initiative);
		return student;
	}
	
	@Override
	public IStudent setMaster() {
		IStudent student = new MasterStudent();
		student.setId(this.id);
		student.setAttributes(this.strength, this.dexterity, this.resilience, this.constitution, this.initiative);
		return student;
	}
}