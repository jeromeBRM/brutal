package brutal.strategies;

import java.util.*;

import brutal.Area;
import brutal.IGame;
import brutal.IStudent;

public class RandomStrategy extends Strategy  {

	@Override
	public void use(IStudent origin, Area area, IGame game) {
		int random = new Random().nextInt();
		if (random % 2 == 0) {
			new OffensiveStrategy().use(origin, area, game);
		}
		else {
			new DefensiveStrategy().use(origin, area, game);
		}
	}
	
	@Override
	protected IStudent target(IStudent origin, Area area, IGame game) {
		return origin;
	}
}