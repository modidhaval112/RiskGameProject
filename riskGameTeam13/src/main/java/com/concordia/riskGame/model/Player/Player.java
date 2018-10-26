package com.concordia.riskGame.model.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;

/**
 * This is entity class to set and get properties of player.
 *
 * @author Darwin Anirudh G and sande
 */
public class Player implements Serializable {

	private String name;
	private int currentPlayerReinforceArmies;
	private int playerCount;
	private int totalArmies;
	private int reinforcementArmies;
	private List<Country> assignedCountries;
	private Map<Player, List<Country>> playerAssign;
	private List<Player> gamePlayerList;
	private HashMap<Country, List<Country>> gamecountryAndNeighbours;
	private int assignedArmies;
	private String[] nameArmiesSpilt;
	private String countryName;
	private String armiesCount;
	
	private boolean hasWon;
	private boolean canContinue;
	
	/**
	 * default constructor
	 */
	public Player() {
	}

	/**
	 * Parameterized constructor to set player Name
	 * 
	 * @param name Name of the player.
	 */
	public Player(String name) {
		this.name = name;
		setHasWon(false);
		setCanContinue(true);
	}

	/**
	 * This method is used to return Map of Player and Countries assigned to him.
	 * 
	 * @return playerAssign Players and assigned countries to him
	 */
	public Map<Player, List<Country>> getPlayerAssign() {
		return playerAssign;
	}

	/**
	 * This method sets map of player and countries assigned to him.
	 *  
	 * @param playerAssign Map of player and its list of assigned armies.
	 */
	public void setPlayerAssign(Map<Player, List<Country>> playerAssign) {
		this.playerAssign = playerAssign;
	}

	/**
	 * This method returns player name
	 * 
	 * @return name Player Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets player name
	 * 
	 * @param name Player Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns number of Armies player have
	 * 
	 * @return currentPlayerReinforceArmies number of Armies player have
	 */
	public int getCurrentPlayerReinforceArmies() {
		return currentPlayerReinforceArmies;
	}

	/**
	 * This method sets number of Armies player have
	 * 
	 * @param currentPlayerReinforceArmies number of armies player have
	 */
	public void setCurrentPlayerReinforceArmies(int currentPlayerReinforceArmies) {
		this.currentPlayerReinforceArmies = currentPlayerReinforceArmies;
	}

	/**
	 * This method returns number of players
	 * 
	 * @return playerCount no of players
	 */
	public int getPlayerCount() {
		return playerCount;
	}

	/**
	 * This method sets number of players
	 * 
	 * @param playerCount number of players
	 */
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	/**
	 * This method is returns total number of armies
	 * 
	 * @return totalArmies number of Armies
	 */
	public int getTotalArmies() {
		return totalArmies;
	}

	/**
	 * This method sets total number of armies
	 * 
	 * @param totalArmies number of armies
	 */
	public void setTotalArmies(int totalArmies) {
		this.totalArmies = totalArmies;
	}

	/**
	 * This method returns number of reinforcement armies.
	 * 
	 * @return reinforcementArmies number of reinforcement armies
	 */
	public int getReinforcementArmies() {
		return reinforcementArmies;
	}

	/**
	 * This method sets number of reinforcement armies
	 * 
	 * @param reinforcementArmies number of reinforcement armies
	 */
	public void setReinforcementArmies(int reinforcementArmies) {
		this.reinforcementArmies = reinforcementArmies;
	}

	/**
	 * This method returns list of countries assigned to player
	 * 
	 * @return assignedCountries list of countries
	 */
	public List<Country> getAssignedCountries() {
		return assignedCountries;
	}

	/**
	 * This method sets list of countries assigned to player
	 * 
	 * @param assignedCountries list of countries
	 */
	public void setAssignedCountries(List<Country> assignedCountries) {
		this.assignedCountries = assignedCountries;
	}
	
	
	/**
	 * The following method implements the fortify phase of the risk game.
	 * 
	 * @param playerObject Instance of current player in the forfeit phase.
	 * @return Instance of the player is returned to the next phase
	 */
	public Player forfeitPhase(Player playerObject) {
		Scanner scanner;
		System.out.println("###### Do you wish to enter the fortification phase: yes/no #######");
		String choice = null;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextLine();

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
	 * @param player Instance of current player in the forfeit phase.
	 * @return Instance of the player is returned to the next phase
	 * @throws Exception 
	 */
	public Player attackPhase(Player player) throws Exception {

		System.out.println("###### Do you wish to attack : yes/no #######");
		Player pObject = new Player();
		pObject = player;
		String choice = null;
		Scanner scanner = new Scanner(System.in);
		choice = scanner.nextLine();
		String sourceCountry;
		Country sourceCountryObject;
		String destinationCountry;
		Country destinationCountryObject;
		if (choice.equalsIgnoreCase("yes")) {
			System.out.println("#### List of countries owned by the player #####");

			for (Country countryObj : player.getAssignedCountries()) {
				System.out.print(countryObj.getCountryName() + ",");
			}
			System.out.println("Enter the name of the country through which you want to attack");
			sourceCountry = scanner.nextLine();
			sourceCountryObject = getCountryOfPlayerFromString(player, sourceCountry);
			if(sourceCountryObject == null) {
				System.out.println("The country with the given name is not owned by the player");
				sourceCountryObject = reenterTheCountry(player);  // declare custom exceptions in the future
			}	
			
			System.out.println("#### The neighbouring attackable countries are #####");
			printNeighbouringAttackableCountriesAndArmies(sourceCountryObject,player);
			System.out.println("Enter the name of the country through which you want to attack");
			destinationCountry = scanner.nextLine();
			destinationCountryObject = getCountryOfCountryListFromString(sourceCountry);
			System.out.println("Player attacks");
			
			attackPhase(player);
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("Player enter into fortify phase");

		} else {
			System.out.println("Invalid Option");
			System.out.println("#### Moving to the next phase ####");
		}

		return pObject;
	}
	
	
	public Country getCountryOfCountryListFromString(String sourceCountry) {
		MapContents contents = MapContents.getInstance();
		HashMap<Country, List<Country>> countriesAndItsNeighbours = contents.getCountryAndNeighbors();
		for(Country country : countriesAndItsNeighbours.keySet()) {
			if(sourceCountry!=null && !sourceCountry.isEmpty() && sourceCountry.equals(country.getCountryName())) {
				return country;
			}
		}
		return null;
	}

	public Country reenterTheCountry(Player player) {
		Scanner scanner = new Scanner(System.in);
		String Country;
		Country CountryObject;
		System.out.println("Enter the name of the country through which you want to attack");
		Country = scanner.nextLine();
		CountryObject = getCountryOfPlayerFromString(player, Country);
		if(CountryObject==null) {
			reenterTheCountry(player);
		}
		return CountryObject;
	}

	/**
	 * The following method implements the reinforcement phase of the risk game.
	 * 
	 * @param player Instance of current player in the forfeit phase.
	 * @return Instance of the player is returned to the next phase
	 */
	public Player reinforcePhase(Player player) {
		Scanner scanner;
		scanner = new Scanner(System.in);
		 gamePlayerList = new ArrayList<Player>();
		System.out.println("########" + player.getName() + "  reinforcement phase begins ########");
		assignedArmies = calculateReiforcementArmies(player.getAssignedCountries().size());
		System.out.println("#### The total number of armies to be reinforced are  #### :" + assignedArmies);
		player.setTotalArmies(assignedArmies);
		int counter = player.getTotalArmies();
		int armiesCounter;

		while (counter > 0) {
			printCountriesOwnedByPlayer(player);
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
		gamePlayerList.add(player);

		for (Player play : gamePlayerList) {
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
		for (Map.Entry<Country, List<Country>> entry : gamecountryAndNeighbours.entrySet()) {
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
	 * @return checks if the player has Won
	 */
	public boolean getHasWon() {
		return hasWon;
	}

	/**
	 * @param hasWon the hasWon to set
	 */
	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

	/**
	 * @return checks if the player can Continue
	 */
	public boolean getCanContinue() {
		return canContinue;
	}

	/**
	 * @param canContinue the canContinue to set
	 */
	public void setCanContinue(boolean canContinue) {
		this.canContinue = canContinue;
	}
	
	/**
	 * To get the country object from the string value of the country
	 * @param player the player object to which the country belongs
	 * @param country string value of the country
	 * @return the country object
	 */
	public Country getCountryOfPlayerFromString(Player player, String country) {
		for(Country playerAssignedCountry : player.getAssignedCountries()) {
			if(country!=null && !country.isEmpty() && country.equals(playerAssignedCountry.getCountryName())) {
				return playerAssignedCountry;
			}
		}
		return null;
	}
	
	/**
	 * This method prints all countries owned by a player
	 * @param player the Player object
	 */
	public void printAllCountriesOwnedByPlayer(Player player) {
		for (Country countryObj : player.getAssignedCountries()) {
			System.out.print(countryObj.getCountryName() + ",");
		}
	}
	
	public void printNeighbouringAttackableCountriesAndArmies(Country country,Player player) {
		System.out.println("######Neighbouring Countries on which you can attack and its armies are :#####");
		for(Country countryObject : country.getNeighbouringCountries()) {
			if(!player.getAssignedCountries().contains(countryObject)) {
			System.out.println(countryObject.getCountryName() +"  :  " + countryObject.getArmies());
			}
		}
	}
}
