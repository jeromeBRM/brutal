package brutal.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;

import brutal.model.core.*;
import brutal.model.states.*;

class Tests {
	
	@Ignore
	@Test
	void gameTest() {
		
		IGame game = Game.getInstance();
		
		/*
		 * start state tests
		 */
		
		// tests if game is correctly initialized to default state
		assertTrue(game.getState() instanceof StartState);
		
		// tests if areas are correctly set
		assertTrue(game.getAreas().size() == 5);
		assertTrue(game.getAreaByName("Bibliothèque") != null);

		// creates players
		game.getState().inputCommand("ISI", game);
		game.getState().inputCommand("ISI", game);
		game.getState().inputCommand("RT", game);
	
		// tests if player "ISI" is not created twice
		assertEquals(game.getPlayers().size(), 2);
		
		/*
		 * setup state tests
		 */
		
		// tests if game state updated to setup state
		assertTrue(game.getState() instanceof SetupState);
		
		// tests if player's turn is first player
		assertEquals(game.getPlayerTurn().getProgram(), Program.ISI);
		
		// tests if students are correctly created
		for (Player player : game.getPlayers()) {
			assertEquals(player.getAllStudents().size(), 20);	
		}

		// tests if student ETU0 is created and belongs to current player
		assertEquals(game.getPlayerTurn().getStudentById("ETU0", game.getPlayerTurn().getAllStudents()).getId(), "ETU0");
		
		// allocate points to students 
		for (IStudent student : game.getPlayerTurn().getAllStudents()) {
			game.getState().inputCommand("points " + student.getId() + " 4 4 4 4 4", game);
			game.getState().inputCommand("strategy " + student.getId() + " random", game);
		}
		
		// tests if total spent points are correctly registered
		assertEquals(game.getPlayerTurn().getTotalSpentAttributePoints(), 400);
		
		// tests if cannot allocate more than 400 points
		game.getState().inputCommand("points ETU0 1 1 1 1 1", game);
		assertEquals(game.getPlayerTurn().getTotalSpentAttributePoints(), 400);
		
		// tests if cannot add same student as reservist twice
		assertEquals(game.getPlayerTurn().getReservists().size(), 0);
		game.getState().inputCommand("reservist ETU0", game);
		game.getState().inputCommand("reservist ETU0", game);
		assertEquals(game.getPlayerTurn().getStudents().size(), 19);
		assertEquals(game.getPlayerTurn().getReservists().size(), 1);
		
		// tests if cannot add more than 5 reservists
		game.getState().inputCommand("reservist ETU1", game);
		game.getState().inputCommand("reservist ETU2", game);
		game.getState().inputCommand("reservist ETU3", game);
		game.getState().inputCommand("reservist ETU4", game);
		game.getState().inputCommand("reservist ETU5", game);
		assertEquals(game.getPlayerTurn().getReservists().size(), 5);
		assertEquals(game.getPlayerTurn().getStudents().size(), 15);

		// tests if cannot set same student as elite twice
		assertEquals(game.getPlayerTurn().getEliteStudents().size(), 0);
		game.getState().inputCommand("elite ETU1", game);
		game.getState().inputCommand("elite ETU1", game);
		assertEquals(game.getPlayerTurn().getEliteStudents().size(), 1);
		
		// tests if cannot add more than 4 elite students
		game.getState().inputCommand("elite ETU2", game);
		game.getState().inputCommand("elite ETU6", game);
		
		// tests elite student attributes boost
		IStudent elite = game.getPlayerTurn().getStudentById("ETU2", game.getPlayerTurn().getAllStudents());
		assertEquals(elite.getEcts(), 39);
		assertEquals(elite.getDexterity(), 5);
		assertEquals(elite.getDexterity(), 5);
		assertEquals(elite.getResilience(), 5);
		assertEquals(elite.getConstitution(), 9);
		assertEquals(elite.getInitiative(), 5);
		
		Player player1 = game.getPlayerTurn();
		
		// tests if cannot set same student as master twice
		assertEquals(game.getPlayerTurn().getMasterStudents().size(), 0);
		game.getState().inputCommand("master ETU8", game);
		game.getState().inputCommand("master ETU8", game);
		assertEquals(game.getPlayerTurn().getMasterStudents().size(), 1);
		
		// tests master student attributes boost
		IStudent master = game.getPlayerTurn().getStudentById("ETU8", game.getPlayerTurn().getAllStudents());
		assertEquals(master.getEcts(), 44);
		assertEquals(master.getDexterity(), 6);
		assertEquals(master.getDexterity(), 6);
		assertEquals(master.getResilience(), 6);
		assertEquals(master.getConstitution(), 14);
		assertEquals(master.getInitiative(), 6);
		
		game.getState().inputCommand("elite ETU7", game);
		game.getState().inputCommand("elite ETU8", game);
		
		// tests if player turn switched
		assertNotEquals(game.getPlayerTurn(), player1);
		
		Player player2 = game.getPlayerTurn();
		
		for (IStudent student : game.getPlayerTurn().getStudents()) {
			game.getState().inputCommand("points " + student.getId() + " 4 4 4 4 4", game);
			game.getState().inputCommand("strategy " + student.getId() + " random", game);
		}
		
		game.getState().inputCommand("reservist ETU20", game);
		game.getState().inputCommand("reservist ETU21", game);
		game.getState().inputCommand("reservist ETU22", game);
		game.getState().inputCommand("reservist ETU23", game);
		game.getState().inputCommand("reservist ETU24", game);
		game.getState().inputCommand("elite ETU20", game);
		game.getState().inputCommand("elite ETU21", game);
		game.getState().inputCommand("elite ETU22", game);
		game.getState().inputCommand("elite ETU23", game);
		game.getState().inputCommand("master ETU24", game);
		
		/*
		 * allocation state tests
		 */
		
		// tests if game state updated to allocation state
		assertTrue(game.getState() instanceof AllocationState);

		game.getState().inputCommand("place ETU0 BBL", game);
		game.getState().inputCommand("place ETU6 BBL", game);
		
		// tests if cannot place reservist on area
		assertEquals(game.getAreaById("BBL").getOccupyingStudents().get(0), game.getPlayerTurn().getStudentById("ETU6", game.getPlayerTurn().getAllStudents()));

		// tests if game state did not update to battle state
		assertNotEquals(game.getPlayerTurn(), player2);
		
		for (IStudent student : game.getPlayerTurn().getStudents()) {
			game.getState().inputCommand("place " + student.getId() + " BBL", game);
		}
		
		// tests if all students are on "BBL" area
		assertEquals(game.getAreaById("BBL").getOccupyingStudents().size(), 15);

		for (int i = 0; i < 5; i++) {
			game.getState().inputCommand("place " + game.getPlayerTurn().getStudents().get(i).getId() + " " + game.getAreas().get(i).getId(), game);
		}
		
		// tests if students were moved from "BBL" area
		assertEquals(game.getAreaById("BBL").getOccupyingStudents().size(), 11);

		for (IStudent student : game.getPlayerTurn().getStudents()) {
			game.getState().inputCommand("place " + student.getId() + " BBL", game);
		}
		
		// tests if all students are on "BBL" area
		assertEquals(game.getAreaById("BBL").getOccupyingStudents().size(), 26);
		
		for (int i = 0; i < 5; i++) {
			game.getState().inputCommand("place " + game.getPlayerTurn().getStudents().get(i).getId() + " " + game.getAreas().get(i).getId(), game);
		}
		
		// tests if students were moved from "BBL" area
		assertEquals(game.getAreaById("BBL").getOccupyingStudents().size(), 22);
		
		/*
		 * battle state tests
		 */
		
		// tests if total ects on area "BBL" is lower than 660 and greater than 0
		assertTrue(game.getAreaById("BBL").getTotalEcts() > 0);
		
		/*
		 * truce state tests
		 */
		
		// tests if game state updated to truce state
		//assertTrue(game.getState() instanceof TruceState);
		
		int countOnBBL = game.getAreaById("BBL").getOccupyingStudents().size();
		
		game.getState().inputCommand("deploy ETU0 BBL", game);
		game.getState().inputCommand("deploy ETU0 BBL", game);
		game.getState().inputCommand("deploy ETU1 BBL", game);
		game.getState().inputCommand("deploy ETU9 BBL", game);
		game.getState().inputCommand("deploy ETU20 BBL", game);
		
		game.getState().inputCommand("change", game);

		game.getState().inputCommand("deploy ETU2 BBL", game);
		game.getState().inputCommand("deploy ETU20 BBL", game);
		game.getState().inputCommand("deploy ETU21 BBL", game);
		game.getState().inputCommand("deploy ETU22 BBL", game);
		game.getState().inputCommand("deploy ETU23 BBL", game);
		game.getState().inputCommand("deploy ETU24 BBL", game);
		
		// tests if students ETU0 and ETU1 have been deployed on BBL
		assertEquals(game.getAreaById("BBL").getOccupyingStudents().size(), countOnBBL + 7);
		
		game.getState().inputCommand("confirm", game);
		
		/*
		 * end state tests

		
		// tests if game state updated to end state
		assertTrue(game.getState() instanceof EndState);
		
		game.getState().inputCommand("restart", game);		 */
	}
}