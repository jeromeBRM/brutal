package brutal;

public interface IState {
	public void updateGameState(IGame game);
	
	public void executeCommandLine(String[] tokens, IGame game);
	
	public void inputCommand(String command, IGame game);
}