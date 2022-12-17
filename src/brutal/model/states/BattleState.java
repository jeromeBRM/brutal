package brutal.model.states;

import java.util.Iterator;

import brutal.model.core.*;

public class BattleState extends State {

	private static final int AREAS_TO_CONTROL = 3;
	
	public BattleState(IGame game) {
		super(game);
	}
	
	@Override
	public void start(IGame game) {
		this.battle(game);
	}

	@Override
	public void executeCommandLine(String[] tokens, IGame game) {
	}

	@Override
	protected IState getNextState(IGame game) {
		if (this.playerControlsAllAreas(game)) {
			return new EndState(game);
		}
		else {
			return new TruceState(game);
		}
	}

	private void battle(IGame game) {
		System.out.println("students are battling...");
		for (Iterator<Area> iterator = game.getAreas().iterator(); iterator.hasNext();) {	
			Area area = (Area) iterator.next();
			for (Iterator<IStudent> iterator2 = area.getStudentsByInitiative().descendingIterator(); iterator2.hasNext();) {	
				IStudent student = (IStudent) iterator2.next();
				if (student.getEcts() > 0) {
					if (!area.isControlled(game)) {
						boolean strategyUsed = student.useStrategy(area, game);
						if (area.isControlled(game)) {
							System.out.println(game);
							System.out.println(area.getId() + " is controlled by " + area.playerInControl(game));
							super.updateGameState(game);
							return;
						}
					}
				}
			}
		}
		System.out.println(game);
		this.battle(game);
	}
	
	private boolean playerControlsAllAreas(IGame game) {
		for (Iterator<Player> iterator = game.getPlayers().iterator(); iterator.hasNext();) {	
			Player player = (Player) iterator.next();
			int controlledAreas = 0;
			for (Iterator<Area> iterator2 = game.getAreas().iterator(); iterator2.hasNext();) {	
				Area area = (Area) iterator2.next();
				if (area.isControlledByPlayer(player, game)) {
					controlledAreas++;
				}
			}
			if (controlledAreas >= BattleState.AREAS_TO_CONTROL) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "battle state";
	}
}