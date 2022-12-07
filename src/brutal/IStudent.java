package brutal;

public interface IStudent {
	public String getId();
	
	public void setId(int id);
	
	public void setAttributes(int strength, int dexterity, int resilience, int constitution, int initiative);
	
	public void addAttributes(int strength, int dexterity, int resilience, int constitution, int initiative);
	
	public void useStrategy(Area area, IGame game);
	
	public void setStrategy(IStrategy strategy);
	
	public void setEcts(int ects);
	
	public void setStrength(int strength);
	
	public void setDexterity(int dexterity);
	
	public void setResilience(int resilience);
	
	public void setConstitution(int constitution);
	
	public void setInitiative(int initiative);
	
	public void heal(int ects);
	
	public void damage(int ects);
	
	public int getEcts();
	
	public int getStrength();
	
	public int getDexterity();
	
	public int getResilience();
	
	public int getConstitution();
	
	public int getInitiative();
	
	public IStrategy getStrategy();
	
	public int getTotalAttributes();
	
	public IStudent setElite();
	
	public IStudent setMaster();

	public Player getOwner(IGame game);
}