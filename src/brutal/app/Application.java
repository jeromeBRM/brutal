package brutal.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import brutal.model.core.*;
import brutal.view.*;

public class Application {
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException, IOException {

		Scanner scanner = new Scanner(System.in);
		IGame game = Game.getInstance();
        
        Runnable console = new Runnable() {
			public void run() {
				try {
			        while (true) {
			            String line = scanner.nextLine();
			            game.getState().inputCommand(line, game);
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		Runnable gui = new Runnable() {
			public void run() {
				try {
					GUI gui = new GUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		Thread consoleThread = new Thread(console);
		Thread guiThread = new Thread(gui);
		
		consoleThread.start();
		guiThread.start();
	}
}