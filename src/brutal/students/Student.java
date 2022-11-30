package brutal.students;

import brutal.Area;
import brutal.IGame;
import brutal.IStrategy;
import brutal.IStudent;

public class Student implements IStudent {

	private int ects;
	private int dexterity;
	private int strength;
	private int resilience;
	private int constitution;
	private int initiative;
	private IStrategy strategy;
	
	public Student() {
		this.setEcts(60);
		this.setStrength(0);
	}
	
	@Override
	public void useStrategy(Area area, IGame game) {
		this.getStrategy().use(this, area, game);
	}
	
	@Override
	public void setEcts(int ects) {
		this.ects = ects;
	}

	@Override
	public void setStrategy(IStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void setStrength(int strength) {
		this.strength = strength;
	}

	@Override
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	@Override
	public void setResilience(int resilience) {
		this.resilience = resilience;
	}

	@Override
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	@Override
	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	@Override
	public void heal(int ects) {
		this.setEcts(this.getEcts() + ects);
	}

	@Override
	public void damage(int ects) {
		// TODO Auto-generated method stub
		
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
}
