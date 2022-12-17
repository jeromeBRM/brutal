package brutal.model.students;

public class MasterStudent extends Student {
	@Override
	public int getStrength() {
		return super.getStrength() + 2;
	}

	@Override
	public int getDexterity() {
		return super.getDexterity() + 2;
	}

	@Override
	public int getResilience() {
		return super.getResilience() + 2;
	}

	@Override
	public int getConstitution() {
		return super.getConstitution() + 10;
	}

	@Override
	public int getInitiative() {
		return super.getInitiative() + 2;
	}
}