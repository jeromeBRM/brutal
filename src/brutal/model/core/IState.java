package brutal.model.core;

public interface IState {
	public void updateGameState(IGame game);
	
	public void executeCommandLine(String[] tokens, IGame game);
	
	public void inputCommand(String command, IGame game);
	
	public void start(IGame game);
}