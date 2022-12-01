package brutal.students;

public class EliteStudent extends Student {
	@Override
	public int getStrength() {
		return super.getStrength() + 1;
	}

	@Override
	public int getDexterity() {
		return super.getDexterity() + 1;
	}

	@Override
	public int getResilience() {
		return super.getResilience() + 1;
	}

	@Override
	public int getConstitution() {
		return super.getConstitution() + 5;
	}

	@Override
	public int getInitiative() {
		return super.getInitiative() + 1;
	}
}