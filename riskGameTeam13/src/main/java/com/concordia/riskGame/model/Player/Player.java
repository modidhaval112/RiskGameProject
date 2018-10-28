package com.concordia.riskGame.model.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;

import com.concordia.riskGame.View.PhaseView;
import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.dice.Dice;

/**
 * This is entity class to set and get properties of player.
 *
 * @author Darwin Anirudh G and sande
 */
public class Player extends Observable implements Serializable {

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
	private boolean hasLost;
	private boolean canAttack = false;
	private boolean canFortify = false;
	private boolean canReinforce = true;
	public String phasePrint;
	public String dominationPrint;

	/**
	 * default constructor
	 */
	public Player() {
	}

	
	public List<Player> getGamePlayerList() {
		return gamePlayerList;
	}

	public void setGamePlayerList(List<Player> gamePlayerList) {
		this.gamePlayerList = gamePlayerList;
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
		PhaseView phaseView = new PhaseView();
		this.addObserver(phaseView);
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
	 * This method sets the message for observers and knows them when it is changed.
	 * @param phaseMessage
	 */
	public void setPhase(String phaseMessage ) {
		phasePrint=phaseMessage;
		setChanged();
        notifyObservers();
 	}
	
	public void setDomination(String dominationMessage ) {
		dominationPrint=dominationMessage;
		setChanged();
        notifyObservers();
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
	 * To check if player has lost
	 * @return
	 */
	public boolean isHasLost() {
		return hasLost;
	}

	/**
	 * To set if a player has lost
	 * @param hasLost
	 */
	public void setHasLost(boolean hasLost) {
		this.hasLost = hasLost;
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
				System.out.println("Exception***************");
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
		boolean allOut = false;
		int attackChoice = 0;
		Scanner scanner = new Scanner(System.in);
		choice = scanner.nextLine();
		String sourceCountry;
		Country sourceCountryObject;
		String destinationCountry;
		Country destinationCountryObject;
		List<Country> attackableCountryList;
		int attackerDice = 0;
		int maximumAttackerDice = 0;
		int maximumDefenderDice =0;
		int defenderDice = 0;
		Dice dice = new Dice();
		List<Integer> attackerDiceResults;
		List<Integer> defenderDiceResults;
		try {
		if (choice.equalsIgnoreCase("yes")) {
			System.out.println("#### List of countries owned by the player #####");

			for (Country countryObj : player.getAssignedCountries()) {
				Country country = getSourceCountryFromString(countryObj.getCountryName());
				System.out.println(country.getCountryName() + " : "+country.getArmies());
			}
			
			System.out.println("Enter the name of the country through which you want to attack");
			sourceCountry = scanner.nextLine();
			sourceCountryObject = getSourceCountryFromString( sourceCountry);
			if(sourceCountryObject == null) {
				System.out.println("The country with the given name is not owned by the player. Please reenter the country");
				sourceCountryObject = reenterTheCountry(player); 
			}	
			System.out.println("Number of armies in "+sourceCountryObject.getCountryName()+ " : " + sourceCountryObject.getArmies());
			if( sourceCountryObject.getArmies()==1) {
				System.out.println("Attack not possible as the country has only 1 army. Please reenter the country");
				sourceCountryObject = reenterTheCountry(player); 
			}
			System.out.println("#### The neighbouring attackable countries are #####");
			attackableCountryList=printNeighboringAttackableCountriesAndArmies(sourceCountryObject,player);
			if(attackableCountryList==null || attackableCountryList.isEmpty()) {
				System.out.println("Attack not possible as there are no neighboring countries. Please reenter the country");
				sourceCountryObject = reenterTheCountry(player); 
			}
			System.out.println("Enter the name of the country on which you want to attack");
			destinationCountry = scanner.nextLine();
			destinationCountryObject = getAttackableCountryOfCountryListFromString(destinationCountry,attackableCountryList);
			if(destinationCountryObject == null) {
				System.out.println("The country with the given name is not in the list or the country does not exist");
				destinationCountryObject = reenterTheDestinationCountry(attackableCountryList); 
			}	
			if(sourceCountryObject.getArmies()>3) {
				System.out.println("Enter 1 if you want to go all out or enter 2 do a normal attack");
				attackChoice=scanner.nextInt(); // input mismatch exception to be handled properly in future										
				if(attackChoice==1) {
					allOut = true;
				}
				else {
					allOut = false;
				}
			}
			else {
				allOut = false;
			}
			
			if(allOut) {
				while(sourceCountryObject.getArmies()>1 && destinationCountryObject.getArmies()!=0) {
				if(sourceCountryObject.getArmies()>3) {
					maximumAttackerDice=3;
				}
				else if(sourceCountryObject.getArmies()==3) {
					maximumAttackerDice=2;
				}
				else if(sourceCountryObject.getArmies()==2) {
					maximumAttackerDice=1;
				}
				if(destinationCountryObject.getArmies()>=2) {
					maximumDefenderDice=2;
				}
				else {
					maximumDefenderDice=1;
				}
				attackerDiceResults = dice.rollDice(maximumAttackerDice);
				defenderDiceResults = dice.rollDice(maximumDefenderDice);
				System.out.println("Attacker Dice Roll results : "+attackerDiceResults.size()+" dice has been rolled");
				for(Integer result : attackerDiceResults) {
					System.out.print(result + " ");
				}
				System.out.println();
				System.out.println("Defender Dice Roll results"+defenderDiceResults.size()+" dice has been rolled");
				for(Integer result : defenderDiceResults) {
					System.out.print(result + " ");
				}
				System.out.println();
				Collections.sort(attackerDiceResults);
				Collections.reverse(attackerDiceResults);
				Collections.sort(defenderDiceResults);
				Collections.reverse(defenderDiceResults);
				int minimumDiceValue = maximumAttackerDice < maximumDefenderDice ? maximumAttackerDice : maximumDefenderDice;
				for(int i=0;i<minimumDiceValue;i++) {
					if(attackerDiceResults.get(i)!=null && defenderDiceResults.get(i)!=null) {
						System.out.println("Result number "+(i+1));
						System.out.println("Attacker Dice value "+attackerDiceResults.get(i));
						System.out.println("Defender Dice value "+defenderDiceResults.get(i));
						if(attackerDiceResults.get(i)>defenderDiceResults.get(i)) {
							System.out.println("Attacker wins this battle");
							destinationCountryObject.setArmies(destinationCountryObject.getArmies()-1);
						}
						else {
							System.out.println("Defender wins this battle");
							sourceCountryObject.setArmies(sourceCountryObject.getArmies()-1);
						}
					}else {
						break;
					}
				}
				System.out.println("Number of armies in "+sourceCountryObject.getCountryName()+" is "+sourceCountryObject.getArmies());
				System.out.println("Number of armies in "+destinationCountryObject.getCountryName()+" is "+destinationCountryObject.getArmies());
				}}
			else {
				if(sourceCountryObject.getArmies()>3) {
					maximumAttackerDice=3;
				}
				else if(sourceCountryObject.getArmies()==3) {
					maximumAttackerDice=2;
				}
				else if(sourceCountryObject.getArmies()==2) {
					maximumAttackerDice=1;
				}
				System.out.println("Enter the number of dice to be roled. Maximum is "+maximumAttackerDice);
				attackerDice = scanner.nextInt();
				if(attackerDice>3 && maximumAttackerDice==3) {
					attackerDice=3;
				}
				else if(attackerDice > maximumAttackerDice ) {
					attackerDice=maximumAttackerDice;
				}
				System.out.println("Number of dice rolled by attacker : "+attackerDice);
				
				if(destinationCountryObject.getArmies()>=2) {
					maximumDefenderDice=2;
				}
				else {
					maximumDefenderDice=1;
				}
				
				System.out.println("Enter the number of dice to be roled. Maximum is "+maximumDefenderDice);
				defenderDice=scanner.nextInt();
				if(defenderDice>2 && maximumDefenderDice==2) {
					defenderDice=2;
				}
				else if(defenderDice > maximumDefenderDice ) {
					defenderDice=maximumDefenderDice;
				}
				System.out.println("Number of dice rolled by defender : "+defenderDice);
			}
			attackerDiceResults = dice.rollDice(attackerDice);
			defenderDiceResults = dice.rollDice(defenderDice);
			
			System.out.println("Attacker Dice Roll results");
			for(Integer result : attackerDiceResults) {
				System.out.print(result + " ");
			}
			System.out.println();
			System.out.println("Defender Dice Roll results");
			for(Integer result : defenderDiceResults) {
				System.out.print(result + " ");
			}
			System.out.println();
			Collections.sort(attackerDiceResults);
			Collections.reverse(attackerDiceResults);
			Collections.sort(defenderDiceResults);
			Collections.reverse(defenderDiceResults);
			int minimumDiceValue = attackerDice < defenderDice ? attackerDice : defenderDice;
			/*if(attackerDiceResults.size()<3) {
				while(attackerDiceResults.size()!=3) {
					attackerDiceResults.add(null);
					}
				}
			if(defenderDiceResults.size()<3) {
				while(defenderDiceResults.size()!=3) {
					defenderDiceResults.add(null);
					}
				}*/
			for(int i=0;i<minimumDiceValue;i++) {
				if(attackerDiceResults.get(i)!=null && defenderDiceResults.get(i)!=null) {
					System.out.println("Result number "+(i+1));
					System.out.println("Attacker Dice value "+attackerDiceResults.get(i));
					System.out.println("Defender Dice value "+defenderDiceResults.get(i));
					if(attackerDiceResults.get(i)>defenderDiceResults.get(i)) {
						System.out.println("Attacker wins this battle");
						destinationCountryObject.setArmies(destinationCountryObject.getArmies()-1);
					}
					else {
						System.out.println("Defender wins this battle");
						sourceCountryObject.setArmies(sourceCountryObject.getArmies()-1);
					}
				}else {
					break;
				}
			}
			System.out.println("Number of armies in "+sourceCountryObject.getCountryName()+" is "+sourceCountryObject.getArmies());
			System.out.println("Number of armies in "+destinationCountryObject.getCountryName()+" is "+destinationCountryObject.getArmies());
			if(destinationCountryObject.getArmies()<1) {
				playerLosesTheCountry(sourceCountryObject,destinationCountryObject);
				printAllCountriesOfaPlayer(sourceCountryObject.getBelongsToPlayer());
			}
			if(player.getAssignedCountries().size()==MapContents.getInstance().getCountryAndNeighbors().keySet().size()) {
				System.out.println("Player "+player.getName()+"has won the game");
				System.exit(0);
			}
			checkPlayerTurnCanContinue(player);

			if(player.getCanAttack())
			attackPhase(player);
		} 
		else if (choice.equalsIgnoreCase("no")) {
			System.out.println("Player enter into fortify phase");
			player.setCanFortify(true);
			}
		else {
			System.out.println("Invalid Option");
			System.out.println("#### Moving to the next phase ####");
			player.setCanFortify(true);
			}
		}
		catch (Exception e) {
			System.out.println("Exception***************");
			attackPhase(player);
		}

		return pObject;
	}
	
	
	private void printAllCountriesOfaPlayer(Player player) {
		System.out.println("Countries assigned to this player are");
		for (Country countryObj : player.getAssignedCountries()) {
			System.out.println(countryObj.getCountryName() + " : "+countryObj.getArmies());
		}
	}


	private void checkPlayerTurnCanContinue(Player player) {
        for (Country c : player.getAssignedCountries()) {
            setCanAttack(false);
            setCanFortify(false);
            if (c.getArmies() > 1) {
                setCanAttack(true);
                setCanFortify(true);
                break;
            }
        }
        /*if(!canAttack && !canFortify){
            nextPlayerTurn(model);
        }*/
    }

	private void playerLosesTheCountry(Country sourceCountryObject, Country destinationCountryObject) {
		destinationCountryObject.getBelongsToPlayer().getAssignedCountries().remove(destinationCountryObject);
		sourceCountryObject.getBelongsToPlayer().getAssignedCountries().add(destinationCountryObject);
		 if (destinationCountryObject.getBelongsToPlayer().getAssignedCountries().size() == 0) {
	            playerHasLost(sourceCountryObject, destinationCountryObject);
	        }
		destinationCountryObject.setBelongsToPlayer(sourceCountryObject.getBelongsToPlayer());
		System.out.println("Enter the armies to be left behind");
		Scanner scanner = new Scanner(System.in);
		int movableArmies=scanner.nextInt();  // To be refactored 
		if (movableArmies > 0) {
			sourceCountryObject.setArmies(sourceCountryObject.getArmies()-movableArmies);
			destinationCountryObject.setArmies(destinationCountryObject.getArmies()+movableArmies);
        }
	}

	private void playerHasLost(Country sourceCountryObject, Country destinationCountryObject) {
		destinationCountryObject.getBelongsToPlayer().setHasLost(true);
		MapContents.getInstance().getPlayerList().remove(destinationCountryObject.getBelongsToPlayer());
		System.out.println("To be Implemented");
		
	}

	public Country getAttackableCountryOfCountryListFromString(String destinationCountry,List<Country> attackableCountryList) {
		MapContents contents = MapContents.getInstance();
		HashMap<Country, List<Country>> countriesAndItsNeighbours = contents.getCountryAndNeighbors();
		for(Country country : countriesAndItsNeighbours.keySet()) {
			if(destinationCountry!=null && !destinationCountry.isEmpty() && attackableCountryList.contains(country) && destinationCountry.equals(country.getCountryName())) {
				return country;
			}
		}
		return null;
	}

	public Country reenterTheDestinationCountry(List<Country> attackableCountryList) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the name of the country on which you want to attack");
		String destinationCountry = scanner.nextLine();
		Country destinationCountryObject = getAttackableCountryOfCountryListFromString(destinationCountry,attackableCountryList);
		if(destinationCountryObject == null) {
			reenterTheDestinationCountry(attackableCountryList);
		}	
		return destinationCountryObject;
	}
	
	public Country reenterTheCountry(Player player) {
		Scanner scanner = new Scanner(System.in);
		String Country;
		Country CountryObject;
		System.out.println("Enter the name of the country through which you want to attack");
		Country = scanner.nextLine();
		CountryObject = getSourceCountryFromString(Country);
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
		player.setCanAttack(true);
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
		for (Map.Entry<Country, List<Country>> entry : MapContents.getInstance().getCountryAndNeighbors().entrySet()) {
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
	public Country getSourceCountryFromString( String sourceCountry) {
		MapContents contents = MapContents.getInstance();
		HashMap<Country, List<Country>> countriesAndItsNeighbours = contents.getCountryAndNeighbors();
		for(Country country : countriesAndItsNeighbours.keySet()) {
			if(sourceCountry!=null && !sourceCountry.isEmpty() &&  sourceCountry.equals(country.getCountryName())) {
				return country;
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
	
	/**
	 * Prints the neighboring attackable countries
	 * @param country
	 * @param player
	 * @return
	 */
	public List<Country> printNeighboringAttackableCountriesAndArmies(Country country,Player player) {
		System.out.println("######Neighbouring Countries on which you can attack and its armies are :#####");
		List<Country> neighbouringAttackableCountries = new ArrayList<>();
		for(Country countryObject :country.getNeighbouringCountries()) {
			Country neighboringCountry = getSourceCountryFromString(countryObject.getCountryName());
			if(!neighboringCountry.getBelongsToPlayer().equals(player)) {
			System.out.println(neighboringCountry.getCountryName() +"  :  " + neighboringCountry.getArmies());
			neighbouringAttackableCountries.add(neighboringCountry);
			}
		}
		return neighbouringAttackableCountries;
	}

	/**
	 * @return the canAttack
	 */
	public boolean getCanAttack() {
		return canAttack;
	}

	/**
	 * @param canAttack the canAttack to set
	 */
	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}

	/**
	 * @return the canFortify
	 */
	public boolean getCanFortify() {
		return canFortify;
	}

	/**
	 * @param canFortify the canFortify to set
	 */
	public void setCanFortify(boolean canFortify) {
		this.canFortify = canFortify;
	}

	/**
	 * @return the canReinforce
	 */
	public boolean getCanReinforce() {
		return canReinforce;
	}

	/**
	 * @param canReinforce the canReinforce to set
	 */
	public void setCanReinforce(boolean canReinforce) {
		this.canReinforce = canReinforce;
	}
}
