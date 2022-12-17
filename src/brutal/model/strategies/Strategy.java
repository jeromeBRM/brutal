package brutal.model.strategies;

import brutal.model.core.Area;
import brutal.model.core.IGame;
import brutal.model.core.IStrategy;
import brutal.model.core.IStudent;

public abstract class Strategy implements IStrategy {

	protected abstract IStudent target(IStudent origin, Area area, IGame game);
}