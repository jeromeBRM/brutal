package brutal;

public interface IState {
	public void updateGameState(IGame game);
	
	public void executeCommandLine(String command, IGame game);
	
	public boolean commandIsValid(String command);
}