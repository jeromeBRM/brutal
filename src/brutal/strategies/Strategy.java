package brutal.strategies;

import brutal.Area;
import brutal.IGame;
import brutal.IStrategy;
import brutal.IStudent;

public abstract class Strategy implements IStrategy {

	protected abstract IStudent target(IStudent origin, Area area, IGame game);
}