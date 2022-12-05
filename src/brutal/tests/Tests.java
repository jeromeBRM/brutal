package brutal.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import brutal.*;
import brutal.states.*;

class Tests {
	
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
		game.createPlayer(Program.ISI);
		game.createPlayer(Program.RT);
	
		// tests if program cannot be created twice
		game.createPlayer(Program.ISI);
		assertFalse(game.getPlayers().size() == 3);
		
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
		assertEquals(elite.getEcts(), 30);
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
		assertEquals(master.getEcts(), 30);
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
	}
}