package brutal.states;

import java.util.Iterator;

import brutal.*;

public class EndState extends State {

	private static final int AREAS_TO_CONTROL = 3;
	
	private boolean confirmed;
	
	public EndState(IGame game) {
		super(game);
		this.confirmed = false;
	}
	
	@Override
	public void start(IGame game) {
		System.out.println("player " + this.getWinner(game).getProgram().toString() + " wins the game!");
	}

	@Override
	public void executeCommandLine(String[] tokens, IGame game) {
		switch (tokens[0]) {
		case "restart":
			this.confirmed = true;
			break;
		}
	}

	@Override
	protected IState getNextState(IGame game) {
		if (this.confirmed) {
			return new StartState(game);
		}
		else {
			return game.getState();
		}
	}
	
	private Player getWinner(IGame game) {
		Player winner = null;
		for (Iterator<Player> iterator = game.getPlayers().iterator(); iterator.hasNext();) {	
			Player player = (Player) iterator.next();
			int controlledAreas = 0;
			for (Iterator<Area> iterator2 = game.getAreas().iterator(); iterator2.hasNext();) {	
				Area area = (Area) iterator2.next();
				if (area.isControlledByPlayer(player, game)) {
					controlledAreas++;
				}
			}
			if (controlledAreas >= EndState.AREAS_TO_CONTROL) {
				winner = player;
			}
		}
		return winner;
	}

	@Override
	public String toString() {
		return "end state";
	}
}