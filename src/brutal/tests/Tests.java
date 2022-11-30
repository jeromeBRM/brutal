package brutal.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import brutal.*;
import brutal.states.*;

class Tests {
	
	@Test
	void gameTest() {
		
		/*
		 * start state tests
		 */
		
		// tests if game is correctly initialized to default state
		assertTrue(Game.getInstance().getState() instanceof StartState);
		
		// tests if areas are correctly set
		assertTrue(Game.getInstance().getAreas().size() == 5);
		assertTrue(Game.getInstance().getAreaByName("Bibliothèque") != null);

		// creates players
		Game.getInstance().createPlayer(Program.ISI);
		Game.getInstance().createPlayer(Program.RT);
	
		// tests if program cannot be created twice
		Game.getInstance().createPlayer(Program.ISI);
		assertFalse(Game.getInstance().getPlayers().size() == 3);
		
		/*
		 * setup state tests
		 */
		
		// tests if game state updated to setup state
		assertTrue(Game.getInstance().getState() instanceof SetupState);
		
		// tests if player's turn is first player
		assertEquals(Game.getInstance().getPlayerTurn().getProgram(), Program.ISI);
		
		// tests if students are correctly created
		for (Player player : Game.getInstance().getPlayers()) {
			assertEquals(player.getStudents().size(), 20);	
		}
	}
}