package brutal.model.core;

import java.util.*;

import brutal.view.GUI;
/** 
*Cette Classe represente l interface du jeu, les deux joueurs definissent les parametres de leur equipe lors de la phase d initialisation.
*@author BRAEMS Jerome, LOUIS Antoine
*/

public interface IGame {
	
	/**
	*Permet de commencer une partie avec des nouveaux parametres.
	*/

	public void initialize();
	
	/**
	*Permet de definir letat de la partie en fonction de ce qu il s y passe
	*@param l etat de la partie
	*/

	public void setState(IState state);
	
	/**
	*Permet d ajouter un nouveau joueur a la partie.
	*@param le programme de l eleve
	*/

	public void createPlayer(Program program);
	
	/**
	*Permet d ajouter un etudiant dans la composition du joueur
	*@param Les statistiques de l etudiant
	*/

	
	public void createStudent(Player player, int strength, int dexterity, int resilience, int constitution, int initiative);
	
	/**
	*Permet de donner le role de reserviste a un etudiant deja existant.
	*@param L etudiant qui sera transforme en reserviste
	*/

	public void setStudentAsReservist(IStudent student);
	
	 /**
	 * permet de donner le role d etudiant d elite a un etudiant deja existant.
	 * @param l etudiant qui sera designe pour devenir un etudiant d elite
	 */
	
	public void setEliteStudent(IStudent student);
	
	/**
	 * permet de donner le role de maitre du gobi a un etudiant deja existant.
	 * @param l etudiant qui sera designe pour devenir le maitre du gobi
	 */
	
	public void setMasterStudent(IStudent student);
	
	/**
	 * permet de positionner un etudiant sur une zone de combat
	 * @param l etudiant qui sera deploye
	 * @param la zone de combat ou il sera deploye
	 */
	
	public void placeStudentOnArea(IStudent student, Area area);
	
	/**
	*Permet d associer une zone de combat a un reserviste 
	*@param Le reserviste qui sera deploye dans la zone donne en parametre
	*/

	public void placeReservist(IStudent reservist, Area area);
	
	/**
	 * permet de donner l etat de la partie.
	 * @return
	 */
	public IState getState();
	
	public List<Player> getPlayers();
	
	/**
	 * permet de donner le vainqueur de la partie
	 * @return le joueur ayant contolle le plus de zone
	 */

	public Player getWinner();
	
	/**
	 * permet de savoir quel joueur doit effectuer des actions
	 * @return le joueur qui doit effectuer une acxtion
	 */
	public Player getPlayerTurn();
	
	/**
	 * Permet de donner la possibilite d effectuer des actions a un joueur apres que son adversaire ait fini les siennes 
	 */ 
	
	public void switchPlayerTurn();
	
	/**
	 * permet de savoir si un etudiant precis appartient a l un des joueurs
	 * @param l etudiant dont il est question
	 * @param le joueur
	 * @return oui ou non
	 */
	
	public boolean isStudentBelongingToPlayer(IStudent student, Player player);
	
	public List<Area> getAreas();
	
	public Area getAreaById(String id);
	
	public Area getAreaByName(String name);
	
	public Area getAreaByStudent(IStudent student);

	public void setPlayerTurn(Player player);
	
	public void addView(GUI gui);
	
	public void notifyView();
	
	public String toString();
}