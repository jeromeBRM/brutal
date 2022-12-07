package brutal.strategies;

import java.util.Iterator;
import java.util.Random;

import brutal.Area;
import brutal.IGame;
import brutal.IStudent;

public class DefensiveStrategy extends Strategy {

	@Override
	public void use(IStudent origin, Area area, IGame game) {
		IStudent target = this.target(origin, area, game);
		if (target == null) {
			return;
		}
		int random = Math.abs(new Random().nextInt() % 100);
		if (random <= (20 + 6 * origin.getDexterity())) {
			if (target != null) {
				int ects = this.ects(origin, target);
				target.heal(ects);
			}
		}
	}
	
	@Override
	protected IStudent target(IStudent origin, Area area, IGame game) {
		IStudent target = null;
		for (Iterator<IStudent> iterator = area.getStudentsByEcts().descendingIterator(); iterator.hasNext();) {
			IStudent student = (IStudent) iterator.next();
			if (student.getEcts() > 0 && student.getOwner(game) == origin.getOwner(game) && student != origin) {
				target = student;
			}
		}
		return target;
	}
	
	private int ects(IStudent origin, IStudent target) {
		float random = Math.abs(new Random().nextInt() % 60) / 100.0f;
		return (int) (random * (10 + target.getConstitution()));
	}
}