package brutal.model.strategies;

import java.util.*;

import brutal.model.core.Area;
import brutal.model.core.IGame;
import brutal.model.core.IStudent;

public class RandomStrategy extends Strategy  {

	@Override
	public boolean use(IStudent origin, Area area, IGame game) {
		int random = new Random().nextInt();
		if (random % 2 == 0) {
			return new OffensiveStrategy().use(origin, area, game);
		}
		else {
			return new DefensiveStrategy().use(origin, area, game);
		}
	}
	
	@Override
	protected IStudent target(IStudent origin, Area area, IGame game) {
		return origin;
	}
	
	@Override
	public String toString() {
		return "random";
	}
}