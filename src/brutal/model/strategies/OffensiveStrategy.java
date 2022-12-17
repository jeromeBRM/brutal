package brutal.model.strategies;

import java.util.*;

import brutal.model.core.Area;
import brutal.model.core.IGame;
import brutal.model.core.IStudent;

public class OffensiveStrategy extends Strategy {

	@Override
	public boolean use(IStudent origin, Area area, IGame game) {
		IStudent target = this.target(origin, area, game);
		if (target == null) {
			return false;
		}
		int random = Math.abs(new Random().nextInt() % 100);
		if (random <= (40 + 3 * origin.getDexterity())) {
			int damage = this.damagePoints(origin, target);
			
			target.damage(damage);
			return true;
		}
		return false;
	}

	@Override
	protected IStudent target(IStudent origin, Area area, IGame game) {
		IStudent target = null;
		for (Iterator<IStudent> iterator = area.getStudentsByEcts().descendingIterator(); iterator.hasNext();) {
			IStudent student = (IStudent) iterator.next();
			if (student.getEcts() > 0 && student.getOwner(game) != origin.getOwner(game)) {
				target = student;
			}
		}
		return target;
	}
	
	private int damagePoints(IStudent origin, IStudent target) {
		float random = Math.abs(new Random().nextInt() % 100) / 100.0f;
		return (int) (random * (1 + this.damageMultiplier(origin, target)) * 10.0f);
	}
	
	private float damageMultiplier(IStudent origin, IStudent target) {
		float mul = Math.max(0, Math.min(100, 10 * origin.getStrength() - 5 * target.getResilience()));
		return mul * 0.1f;
	}
}