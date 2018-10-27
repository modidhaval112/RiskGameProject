package com.concordia.riskGame.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.Player.Player;

/**
 * This class implements the Game phases in a round robin fashion
 * 
 * @author Sande
 *
 */
public class GameDriver {

	private Scanner scanner;
	private String[] nameArmiesSpilt;
	private String countryName;
	private String armiesCount;
	private List<Player> gmPlayerList;
	private HashMap<Country, List<Country>> gmcountryAndNeighbours;
	private List<Player> updatedPlayerList;
	private int assignedArmies;
	private boolean endTheGame;
	/**
	 * The following method calls each of the game phase for each player.
	 * 
	 * @param player              List of players playing the game
	 * @param countryAndConnected countries and their respective connected
	 *                            countries.
	 * @throws Exception 
	 */
	public void gamePhase() throws Exception {
		MapContents mapContents =MapContents.getInstance();
		gmcountryAndNeighbours = new HashMap<Country, List<Country>>();
		gmcountryAndNeighbours = mapContents.getCountryAndNeighbors();
		scanner = new Scanner(System.in);
		updatedPlayerList = new ArrayList<Player>();
		endTheGame=false;
		Iterator<Player> iterator = mapContents.getPlayerList().iterator();
		while(iterator.hasNext()) {
			Player playerInstance = new Player();
			Player p = iterator.next();
			
			playerInstance = playerInstance.reinforcePhase(p);
			if(playerInstance.getCanAttack()) {
			playerInstance = playerInstance.attackPhase(playerInstance);
			}
			/*else {
				System.out.println(playerInstance.getName()+"has won the game");
				System.out.println("###### Game has been ended ######");
				endTheGame = true;
				break;
			}*/
			if(playerInstance.getCanFortify()) {
			playerInstance = playerInstance.forfeitPhase(playerInstance);
			}
			//updatedPlayerList.add(playerInstance);
			
		}
		
		if(endTheGame) {
			System.exit(0);
		}
		/*List<Player> gdPlayerList = new ArrayList<Player>();
		gdPlayerList = updatedPlayerList;*/
		System.out.println("######## Do you want to exit : yes  #########");
		String choice = scanner.nextLine();

		if (choice.equalsIgnoreCase("yes")) {
			System.exit(0);
		}
		gamePhase();

	}



	/**
	 * The following checks if the string is number or not
	 * 
	 * @param str String value is passed which is used to check as number or not.
	 * @return Returns true if the string is a number else it returns false.
	 */
	public boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	/**
	 * The following method checks the countries passed in the forfeit phase are
	 * neighbour or not.
	 * 
	 * @param sourceCountry The country from which armies have to moved.
	 * @param destCountry   The country which receives armies.
	 * @return returns true if both the countries are countries else it returns
	 *         false.
	 */
	public boolean isNeighbour(String sourceCountry, String destCountry) {
		System.out.println("##### Checking the country, if its a neighbour of the country or not #####");
		System.out.println("##### Source country is        : ##### :" + sourceCountry);
		System.out.println("##### Destination country is : ##### :" + destCountry);
		List<Country> connectedCountries = new ArrayList<Country>();
		boolean returnValue = false;
		for (Map.Entry<Country, List<Country>> entry : gmcountryAndNeighbours.entrySet()) {
			if (entry.getKey().getCountryName().equalsIgnoreCase(sourceCountry)) {
				connectedCountries = entry.getValue();
				for (Country countryInstance : connectedCountries) {
					if (countryInstance.getCountryName().equalsIgnoreCase(destCountry)) {
						System.out.println("#### Country is a neighbour ######");
						returnValue = true;
					}
				}
			}
		}
		return returnValue;
	}

	/**
	 * Method to Calculate Number of armies in reinforcement Phase
	 *  
	 * @param numberOfCountriesOwned Number of countries owned.
	 * @return number of reinforced armies
	 */
	public int calculateReiforcementArmies(int numberOfCountriesOwned) {
		int armiesToAssign;

		armiesToAssign = numberOfCountriesOwned / 3;
		if (armiesToAssign < 3) {
			armiesToAssign = 3;
		}

		return armiesToAssign;
	}

	/**
	 * The following method displays the countries assigned to a player.
	 * 
	 * @param player The instance of the player is taken as the parameter.
	 */
	public void printCountriesOwnedByPlayer(Player player) {
		System.out.println("### Countries Owned by the player are ##### :");
		for (Country country : player.getAssignedCountries()) {
			System.out.print(country.getCountryName() + " ,");
		}

	}

}
