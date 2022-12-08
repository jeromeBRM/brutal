package brutal;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            Game.getInstance().getState().inputCommand(line, Game.getInstance());
        }
	}
}