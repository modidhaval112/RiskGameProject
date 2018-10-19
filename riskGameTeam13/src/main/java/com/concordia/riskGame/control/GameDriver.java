package com.concordia.riskGame.control;

import java.util.Scanner;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.concordia.riskGame.View.PlayerCount;
import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Player.Player;


/**
 * This class implements the Game phases in a round robin fashion
 * 
 * @author Sande
 *
 */
public class GameDriver {

	private PlayerCount playerCountOBject;
	
	private Scanner scanner;
	private String[] nameArmiesSpilt;
	private String countryName;
	private String armiesCount;
	private List<Player> gmPlayerList;
	private HashMap<Country, List<Country>> gmcountryAndNeighbours;
	private List<Player> updatedPlayerList;
	private int assignedArmies;

	/**
	 * The following method calls each of the game phase for each player.
	 * 
	 * @param player              List of players playing the game
	 * @param countryAndConnected countries and their respective connected
	 *                            countries.
	 */

	public void gamePhase(List<Player> player, HashMap<Country, List<Country>> countryAndConnected) {

		List<Player> playerList = new ArrayList<Player>();
		gmcountryAndNeighbours = new HashMap<Country, List<Country>>();
		gmcountryAndNeighbours = countryAndConnected;
		scanner = new Scanner(System.in);
		updatedPlayerList = new ArrayList<Player>();

		for (Player p : player) {

			Player playerInstance = new Player();
			playerInstance = reinforcePhase(p);
			playerInstance = attackPhase(playerInstance);
			playerInstance = forfeitPhase(playerInstance);
			updatedPlayerList.add(playerInstance);
		}
		List<Player> gdPlayerList = new ArrayList<Player>();
		gdPlayerList = updatedPlayerList;
		System.out.println("######## Do you want to exit : yes  #########");
		String choice = scanner.nextLine();

		if (choice.equalsIgnoreCase("yes")) {
			System.exit(0);
		}
		gamePhase(gdPlayerList, gmcountryAndNeighbours);

	}

	/**
	 * The following method implements the fortify phase of the risk game.
	 * 
	 * @param playerObject Instance of current player in the forfeit phase.
	 * @return Instance of the player is returned to the next phase
	 */
	public Player forfeitPhase(Player playerObject) {

		System.out.println("###### Do you wish to enter the fortification phase: yes/no #######");
		String choice = null;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextLine();
		boolean flag = false;

		if (choice.equalsIgnoreCase("yes")) {
			try {
				Player player = new Player();
				player = playerObject;
				System.out.println(player.getName() + " is in fortification phase ");
				System.out.println("##### Fortification Phase begins ######");
				String[] utilString;
				scanner = new Scanner(System.in);

				System.out.println("#### List of countries owned by the player #####");

				for (Country countryObj : player.getAssignedCountries()) {

					System.out.print(countryObj.getCountryName() + ",");

				}
				System.out.println("\n");
				System.out.println("##### Enter source country and destination country(comma seperated) ###### :");
				utilString = scanner.nextLine().split(",");
				String fromCountry = utilString[0].trim();
				String toCountry = utilString[1].trim();

				if (fromCountry.equalsIgnoreCase(toCountry)) {
					System.out.println("xxxxxxx----From and to country cannot be the same----xxxxxx");
					throw new Exception();

				}

				System.out.println("###### Enter the number of armies to be moved #######");
				int movingArmies = scanner.nextInt();

				System.out.println("###########    Source country      	 ############### :" + fromCountry);
				System.out.println("###########  Destination Country   	 ############### :" + toCountry);
				System.out.println("###########   Armies to be moved    ###############  :" + movingArmies);

				List<Country> connectedCountries = new ArrayList<Country>();

				int destArmies = 0;
				int sourcesArmies = 0;
				if (!isNeighbour(fromCountry, toCountry)) {
					System.out.println("##### The  Countries are not neighbours ######");
					throw new Exception();
				}

				List<Country> assignedCountriesClone = new ArrayList<Country>();
				List<Country> assignedCountriesClone2 = new ArrayList<Country>();

				for (Country countryInstance : player.getAssignedCountries()) {
					if (countryInstance.getCountryName().equalsIgnoreCase(fromCountry)) {
						Country sourceCountry = new Country();
						sourceCountry = countryInstance;
						sourcesArmies = sourceCountry.getArmies();
						if (sourcesArmies == 1) {
							System.out.println("You cannot move the only army from this Country");
							throw new Exception();
						}
						if (sourcesArmies < movingArmies) {
							System.out.println(
									"The country doesnt have the mentioned number of armies, please enter a lesser number");
							throw new Exception();
						}
						if (sourcesArmies == movingArmies) {
							System.out.println(
									"You cannot move all the armies from this Country, please enter a lesser number");
							throw new Exception();
						}
						sourcesArmies = sourcesArmies - movingArmies;
						sourceCountry.setArmies(sourcesArmies);
					}
					assignedCountriesClone.add(countryInstance);
				}
				for (Country countryInstance : player.getAssignedCountries()) {
					if (countryInstance.getCountryName().equalsIgnoreCase(toCountry)) {
						Country destCountry = new Country();
						destCountry = countryInstance;
						destArmies = destCountry.getArmies();
						destArmies = destArmies + movingArmies;
						destCountry.setArmies(destArmies);
					}
					assignedCountriesClone2.add(countryInstance);
				}
				for (Country x : assignedCountriesClone2) {
					if (!assignedCountriesClone.contains(x))
						assignedCountriesClone.add(x);
				}

				System.out.println("#### Displaying country and its neighbouring countries #####");

	
				System.out.println("############### Displaying player armies count after fortify ###########");
				for (Country country : assignedCountriesClone) {
					System.out.println("######## The country name is ########   :" + country.getCountryName());
					System.out.println("######## The country armies is ######   :" + country.getArmies());
				}
				player.setAssignedCountries(assignedCountriesClone);

				System.out.println("##### Armies have been moved between countries ###### ");
				return player;
			} catch (Exception e) {
				forfeitPhase(playerObject);
			}
		} else {
			System.out.println("##### End of Fortify ###### ");
			return playerObject;
		}
		return playerObject;
	}

	/**
	 * The following method implements the attack phase of the risk game.
	 * 
	 * @param playerObject Instance of current player in the forfeit phase.
	 * @return Instance of the player is returned to the next phase
	 */

	public Player attackPhase(Player player) {

		System.out.println("###### Do you wish to attack : yes/no #######");
		Player pObject = new Player();
		pObject = player;

		String choice = null;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextLine();

		if (choice.equalsIgnoreCase("yes")) {
			System.out.println("Player attacks");
			attackPhase(player);
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("Player enter into forfeits phase");

		} else {
			System.out.println("Invalid Option");
			System.out.println("#### Moving to the next phase ####");
		}

		return pObject;
	}

	/**
	 * The following method implements the reinforcement phase of the risk game.
	 * 
	 * @param playerObject Instance of current player in the forfeit phase.
	 * @return Instance of the player is returned to the next phase
	 */

	public Player reinforcePhase(Player player) {

		scanner = new Scanner(System.in);
		gmPlayerList = new ArrayList<Player>();
		int additionalArmies;
		System.out.println("########" + player.getName() + "  reinforcement phase begins ########");

		assignedArmies = calculateReiforcementArmies(player.getAssignedCountries().size());

		System.out.println("#### The total number of armies to be reinforced are  #### :" + assignedArmies);
		player.setTotalArmies(assignedArmies);

		int counter = player.getTotalArmies();
		int armiesCounter;

		while (counter > 0) {

			countriesOwnedByPlayer(player);

			System.out.println(
					"##### Select the country name,armies (comma , seperated) in which you want to assign armies ######");
			System.out.println("#### The number of armies to be reinforced are  #### :" + counter);

			nameArmiesSpilt = scanner.nextLine().split(",");
			countryName = nameArmiesSpilt[0];
			armiesCount = nameArmiesSpilt[1];

			if (!isNumeric(armiesCount)) {
				System.out.println("#### It is not valid Integer . Please enter a integer #####");
				armiesCount = scanner.nextLine();
			}
			System.out.println("##### The selected country name is  #### :" + countryName);
			System.out.println("##### The army count  name is       #### :" + armiesCount);

			armiesCounter = Integer.parseInt(armiesCount);
			int armyCount = 0;
			if (armiesCounter <= counter) {

				for (Country country : player.getAssignedCountries()) {
					if (countryName.equalsIgnoreCase(country.getCountryName())) {

						Country c = new Country();
						c = country;
						armyCount = country.getArmies() + armiesCounter;
						c.setArmies(armyCount);
						Collections.replaceAll(player.getAssignedCountries(), country, c);
						player.setTotalArmies(armyCount);
					}
				}

				counter = counter - armiesCounter;

			} else {
				System.out.println(
						"##### The entered army count is greater than the remaining armies. Please enter a value below the ramining armies ######");
				System.out.println("##### The reamining armies are ##### :" + counter);
			}

		}
		gmPlayerList.add(player);

		for (Player play : gmPlayerList) {
			System.out.println("##### The player name is         ######             :" + play.getName());
			for (Country countryObject : play.getAssignedCountries()) {
				System.out.println("			###### The assigned country name is ###### 		:"
						+ countryObject.getCountryName());
				System.out.println(
						"			###### The assigned army count  is 	 ######	    :" + countryObject.getArmies());
			}
		}

		System.out.println("########" + player.getName() + "  reinforcement phase ended ########");
		return player;

	}

	public boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

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
	 * Method to Calculate Number of armiesin reinforcement Phase
	 * 
	 * @param numberOfCountriesOwned
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

	public boolean isSourceDestCountrySame(String sourceCountry, String destinationCountry) {
		boolean returnValue = true;

		if (sourceCountry.equalsIgnoreCase(destinationCountry))
			returnValue = false;

		return returnValue;
	}

	public void countriesOwnedByPlayer(Player player) {
		System.out.println("### Countries Owned by the player are ##### :");
		for (Country country : player.getAssignedCountries()) {
			System.out.print(country.getCountryName() + " ,");
		}

	}

}
