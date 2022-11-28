package brutal;

public class Application {

	private static IGame game;
	
	public Application() {
		Application.game = new Game();
	}

	public static void main(String[] args) {
		Application application = new Application();
		
		System.out.println(Application.getGame());
	}

	public static IGame getGame() {
		return Application.game;
	}
}