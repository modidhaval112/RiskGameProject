package com.concordia.riskGame.model.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Scanner;

import com.concordia.riskGame.View.CardView;
import com.concordia.riskGame.View.PhaseView;
import com.concordia.riskGame.View.WorldDominationView;
import com.concordia.riskGame.control.GameDriver;
import com.concordia.riskGame.model.Card.Card;
import com.concordia.riskGame.model.Card.Deck;
import com.concordia.riskGame.model.Continent.Continent;
import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.dice.Dice;

/**
 * This is entity class to set and get properties of player.
 *
 * @author Darwin Anirudh G and sande
 */
public class Player extends Observable implements Serializable {

	public static String reinforcePhase = "Reinforcement Phase";
	public static String attackPhase = "Attack Phase";
	public static String fortificationPhase = "Fortification Phase";
	public String name;
	private int currentPlayerReinforceArmies;
	private int playerCount;
	private int totalArmies;
	private int reinforcementArmies;
	private List<Country> assignedCountries;
	private Map<Player, List<Country>> playerAssign;
	private List<Player> gamePlayerList;
	private List<Card> cardList = new ArrayList<>();
	private boolean exchanged = false;
	private Deck deck = Deck.getInstance();
	private int cardExchangeTypeCount = 0;
	public int getCardExchangeTypeCount() {
		return cardExchangeTypeCount;
	}

	public void setCardExchangeTypeCount(int cardExchangeTypeCount) {
		this.cardExchangeTypeCount = cardExchangeTypeCount;
	}

	private String cardExchangeAppearingMoreThanThrice = "";
	public String getCardExchangeAppearingMoreThanThrice() {
		return cardExchangeAppearingMoreThanThrice;
	}

	public void setCardExchangeAppearingMoreThanThrice(String cardExchangeAppearingMoreThanThrice) {
		this.cardExchangeAppearingMoreThanThrice = cardExchangeAppearingMoreThanThrice;
	}

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
	private boolean cardGiven = false;
	private boolean endGameForThisPlayer = false;
	public String phasePrint;
	public String phaseMsg;
	public String dominationPrint = "";
	public int cardExchangeCount = 0;
	public String currentPhase;
	private String errorMesage;

	public int movableArmies; 
	/**
	 * default constructor
	 */
	public Player() {
		PhaseView phaseView = new PhaseView();
		this.addObserver(phaseView);
		WorldDominationView dominationView = new WorldDominationView();
		this.addObserver(dominationView);
		CardView cardView = new CardView();
		this.addObserver(cardView);
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
		PhaseView phaseView = new PhaseView();
		this.addObserver(phaseView);
		WorldDominationView dominationView = new WorldDominationView();
		this.addObserver(dominationView);
		CardView cardView = new CardView();
		this.addObserver(cardView);
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
	 * This method sets the message for observers and knows them when it is changed.
	 * 
	 * @param phaseMessage
	 */
	public void setPhase(String phaseMessage) {
		phasePrint = "phase";
		phaseMsg=phaseMessage;
		setChanged();
		notifyObservers();
	}

	/**
	 * This method sets the message for observer of domination and knows them when
	 * it is changed.
	 */
	public void setDomination() {
		dominationPrint = "domination";
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
	 * 
	 * @return
	 */
	public boolean isHasLost() {
		return hasLost;
	}

	/**
	 * To set if a player has lost
	 * 
	 * @param hasLost
	 */
	public void setHasLost(boolean hasLost) {
		this.hasLost = hasLost;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public int getCardExchangeCount() {
		return cardExchangeCount;
	}

	public void setCardExchangeCount(int cardExchangeCount) {
		this.cardExchangeCount = cardExchangeCount;
	}

	public String getCurrentPhase() {
		return currentPhase;
	}

	public void setCurrentPhase(String currentPhase) {
		this.currentPhase = currentPhase;
	}

	/**
	 * The following method implements the fortify phase of the risk game.
	 * 
	 * @param playerObject Instance of current player in the forfeit phase.
	 * @return Instance of the player is returned to the next phase
	 */
	public Player forfeitPhase(Player playerObject) {
		/*setCurrentPhase(Player.fortificationPhase);
		playerObject.setCurrentPhase(Player.fortificationPhase);
		setDomination();*/
		Scanner scanner;
		setPhase("###### Do you wish to enter the fortification phase: yes/no #######");
		String choice = null;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextLine();

		if (choice.equalsIgnoreCase("yes")) {
			try {
				Player player = new Player();
				player = playerObject;
				setPhase(player.getName() + " is in fortification phase ");
				setPhase("##### Fortification Phase begins ######");
				String[] utilString;
				scanner = new Scanner(System.in);
				setPhase("#### List of countries owned by the player #####");

				for (Country countryObj : player.getAssignedCountries()) {
					System.out.print(countryObj.getCountryName() + ",");

				}

				System.out.println(
						"																												  ");
				setPhase("#### Enter the country name , you want to move armies from ###### ");
				String srcCountry = scanner.nextLine();
				srcCountry = srcCountry.trim();
				
				
				setPhase(" ##### Printing List of neighouring countries ###### ");
				List<Country> destNeighborCountryList = new ArrayList();
				destNeighborCountryList = printNeighbouringCountry(srcCountry, player);
				
				
				if(destNeighborCountryList.size() == 0)
				{
					setPhase("##### The source country has zero neighboring countries ######");
					srcCountry = reEnterSourceCountryforListCheck(player);
					srcCountry = srcCountry.trim();
					
				}
				
				

				System.out.println(
						"																												  ");
				
				if (!checkValidSourceCountry(srcCountry, player)) {
					srcCountry = reEnterSourceCountry(player);
					srcCountry = srcCountry.trim();
				}

				destNeighborCountryList=printNeighbouringCountry(srcCountry, player);
				
				setPhase("###### Please enter destination country where you want to move armies #####");
				String destinationCountry = scanner.nextLine();
				destinationCountry = destinationCountry.trim();

							
				/*System.out.println(" ##### Printing List of neighouring countries ###### ");
				List<Country> destNeighborCountryList = new ArrayList();
				destNeighborCountryList = printNeighbouringCountry(srcCountry, player);

				System.out.println(
						"																												  ");
				System.out.println("###### Please enter destination country where you want to move armies #####");
				String destinationCountry = scanner.nextLine();
				destinationCountry = destinationCountry.trim();*/

				if (!checkValidDestinationCountry(destNeighborCountryList, destinationCountry)) {
					destinationCountry = reEnterDestinationCountry(destNeighborCountryList);
					destinationCountry = destinationCountry.trim();
				}

				/*
				 * System.out.println("\n"); System.out.
				 * println("##### Enter source country and destination country (comma seperated) ###### :"
				 * ); utilString = scanner.nextLine().split(","); String fromCountry =
				 * utilString[0].trim(); String toCountry = utilString[1].trim();
				 */

				String fromCountry = srcCountry;
				String toCountry = destinationCountry;

				if (fromCountry.equalsIgnoreCase(toCountry)) {
					setPhase("xxxxxxx----From and to country cannot be the same----xxxxxx");
					setErrorMesage("From and To country can not be the same");
					throw new Exception();

				}

				setPhase("###### Enter the number of armies to be moved #######");
				int movingArmies = scanner.nextInt();
				setPhase("					###########    Source country      	     ###############       : "
								+ fromCountry);
				setPhase(
						"					###########  Destination Country   	 ###############       : " + toCountry);
				setPhase("					############   Armies to be moved    ###############      : "
						+ movingArmies);

				int destArmies = 0;
				int sourcesArmies = 0;
				if (!isNeighbour(fromCountry, toCountry)) {
					setPhase("##### The Countries are not neighbours ######");
					setErrorMesage("The Countries are not neighbours");
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
							setPhase("You cannot move the only army from this Country");
							setErrorMesage("You cannot move the only army from this Country");
							throw new Exception();
						}
						if (sourcesArmies < movingArmies) {
							setPhase(
									"The country doesnt have the mentioned number of armies, please enter a lesser number");
							setErrorMesage("The country doesnt have the mentioned number of armies, please enter a lesser number");
							throw new Exception();
						}
						if (sourcesArmies == movingArmies) {
							setPhase(
									"You cannot move all the armies from this Country, please enter a lesser number");
							setErrorMesage("You cannot move all the armies from this Country, please enter a lesser number");
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

				setPhase("############### Displaying player armies count after fortify ###########");

				for (Country country : assignedCountriesClone) {
					setPhase("######## The country name is     #######      : " + country.getCountryName());
					setPhase("######## The country armies is   #######      : " + country.getArmies());
				}

				player.setAssignedCountries(assignedCountriesClone);
				setPhase("##### Armies have been moved between countries ######");
				setErrorMesage("Armies have been moved between countries");
				return player;
			} catch (Exception e) {
				System.out.println("Exception Message " + e.getMessage());
				forfeitPhase(playerObject);
			}
		} else {
			setPhase("##### End of Fortify ###### ");
			return playerObject;
		}
		return playerObject;
	}

	/**
	 * The following method requests the user to renter the source country in event
	 * of invalid input during the first attempt.
	 * 
	 * @param player Player Instance.
	 * @return Returns a valid source country name.
	 */

	public String reEnterSourceCountry(Player player) {
		String soruceCountry = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("#### Please enter a valid source country #####");
		soruceCountry = sc.nextLine();

		if (!checkValidSourceCountry(soruceCountry, player))
			soruceCountry = reEnterSourceCountry(player);

		return soruceCountry;
	}
	
	
	/**
	 * The following method requests the user to renter the source country in event
	 * of  zero list for destination country.
	 * 
	 * @param player Player Instance.
	 * @return Returns a valid source country name.
	 */

	public String reEnterSourceCountryforListCheck(Player player) {
		String soruceCountry = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("#### Please enter a valid source country #####");
		soruceCountry = sc.nextLine();
		List<Country> destCountryList = new ArrayList();
		
		if (checkZeroDestinationCountryList(soruceCountry,player).size() == 0 )
			soruceCountry = reEnterSourceCountryforListCheck(player);

		return soruceCountry;
	}
	
	/**
	 * The following returns the list of destination countries.
	 * @param countryName Name of the country to which you want to get destination country.
	 * @param player Player Instance.
	 * @return List of Destination Countries.
	 */
	
	public  List<Country> checkZeroDestinationCountryList(String countryName, Player player)
	{
		
		List<Country> desCountryList  = new ArrayList();
		desCountryList=printNeighbouringCountry(countryName, player);
		return desCountryList;
	}
	

	/**
	 * The following method checks if it is a valid source country.
	 * 
	 * @param countryName Name of the country.
	 * @param player      Player Instance
	 * @return Returns true if it is a valid source country.
	 */

	public boolean checkValidSourceCountry(String countryName, Player player) {
		boolean returnValue = false;

		for (Country country : player.getAssignedCountries()) {
			if (country.getCountryName().equalsIgnoreCase(countryName)) {
				returnValue = true;
				break;
			}
		}

		return returnValue;
	}

	/**
	 * The following method creates a valid list of neighouring countries owned by a
	 * player for that country.
	 * 
	 * @param countryName Name of the Country.
	 * @param player      Player Instance.
	 * @return Returns the List of adjacent countries for a country owned by the
	 *         player.
	 */
	public List<Country> printNeighbouringCountry(String countryName, Player player) {
		List<Country> countryNeighbourList = new ArrayList();

		List<Country> playerCountryList = new ArrayList();

		List<Country> commonCountryList = new ArrayList();

		playerCountryList = player.getAssignedCountries();

		for (Country countryInstance : player.getAssignedCountries()) {
			if (countryInstance.getCountryName().equalsIgnoreCase(countryName)) {
				countryNeighbourList = countryInstance.getNeighbouringCountries();
			}

		}

		for (Country countryObj : playerCountryList) {
			for (Country countryj : countryNeighbourList) {
				if (countryj.getCountryName().equalsIgnoreCase(countryObj.getCountryName())) {
					commonCountryList.add(countryj);
				}
			}
		}

		System.out.println("####### Printing the neighbour countries owned by the player #########");

		System.out.println("####### The size of common country list is ######## " + commonCountryList.size());
		for (Country countryObj : commonCountryList) {
			System.out.print("            " + countryObj.getCountryName());
		}

		return commonCountryList;
	}

	/**
	 * The following method requests the user to renter the destination country in
	 * event of invalid input during the first attempt.
	 * 
	 * @param destCountryList List of Destination Countries.
	 * @return Returns a valid destination country name.
	 */

	public String reEnterDestinationCountry(List<Country> destCountryList) {
		String destCountry = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("#### Please enter a valid destination country #####");
		setErrorMesage("Please enter a valid destination country");
		destCountry = sc.nextLine();
		destCountry = destCountry.trim();

		if (!checkValidDestinationCountry(destCountryList, destCountry))
			destCountry = reEnterDestinationCountry(destCountryList);

		return destCountry;
	}

	/**
	 * The following method checks if it is valid destination country for
	 * fortification or not.
	 * 
	 * @param destCountryList List of Destination Countries.
	 * @param countryName     Name of the country.
	 * @return Returns true if it is a valid destination country.
	 */
	public boolean checkValidDestinationCountry(List<Country> destCountryList, String countryName) {

		boolean returnValue = false;

		for (Country country : destCountryList) {
			if (country.getCountryName().equalsIgnoreCase(countryName)) {
				returnValue = true;
				break;
			}
		}

		return returnValue;

	}

	/**
	 * The following method implements the attack phase of the risk game.
	 * 
	 * @param player Instance of current player in the forfeit phase.
	 * @return Instance of the player is returned to the next phase
	 * @throws Exception
	 */
	public Player attackPhase(Player player) throws Exception {
	/*	setCurrentPhase(Player.attackPhase);
		player.setCurrentPhase(Player.attackPhase);
		setDomination();*/

		setPhase("###### Do you wish to attack : yes/no #######");
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
		int maximumDefenderDice = 0;
		int defenderDice = 0;
		Dice dice = new Dice();
		List<Integer> attackerDiceResults;
		List<Integer> defenderDiceResults;
		try {
			if (choice.equalsIgnoreCase("yes")) {
						GameDriver driver = new GameDriver();
			List<Country> sourceAndDestinationCountry = driver.getSourceAndDestinationCountry(player); 
			if(sourceAndDestinationCountry==null) {
				setPhase("#### Moving to the next phase ####");
				player.setCanFortify(true);
				player.setCardGiven(false);
				return pObject;
			}
			sourceCountryObject=sourceAndDestinationCountry.get(0);
			destinationCountryObject=sourceAndDestinationCountry.get(1);
				if (sourceCountryObject.getArmies() > 3) {
					setPhase("Enter 1 if you want to go all out or enter 2 do a normal attack");
					attackChoice = scanner.nextInt(); // input mismatch exception to be handled properly in future
					if (attackChoice == 1) {
						allOut = true;
					} else {
						allOut = false;
					}
				} else {
					allOut = false;
				}

				if (allOut) {
					while (sourceCountryObject.getArmies() > 1 && destinationCountryObject.getArmies() != 0) {
						
						
					
						
						maximumAttackerDice = getMaxAttackerDiceCount (sourceCountryObject.getArmies());
						maximumDefenderDice = getMaxDefenderDiceCount(destinationCountryObject.getArmies());
						
						setPhase("###### The maximum number of dice attacker can roll is  #### : "+maximumAttackerDice);
						setPhase("###### The maximum number of dice defender can roll is  #### : "+maximumDefenderDice);
						
						
						attackerDiceResults = dice.rollDice(maximumAttackerDice);
						defenderDiceResults = dice.rollDice(maximumDefenderDice);
						setPhase(
								"Attacker Dice Roll results : " + attackerDiceResults.size() + " dice has been rolled");
						for (Integer result : attackerDiceResults) {
							setPhase(result + " ");
						}
						System.out.println();
						System.out.println(
								"Defender Dice Roll results" + defenderDiceResults.size() + " dice has been rolled");
						for (Integer result : defenderDiceResults) {
							setPhase(result + " ");
						}
						System.out.println();
						Collections.sort(attackerDiceResults);
						Collections.reverse(attackerDiceResults);
						Collections.sort(defenderDiceResults);
						Collections.reverse(defenderDiceResults);
						int minimumDiceValue = maximumAttackerDice < maximumDefenderDice ? maximumAttackerDice
								: maximumDefenderDice;
						
						for (int i = 0; i < minimumDiceValue; i++) {
							if (attackerDiceResults.get(i) != null && defenderDiceResults.get(i) != null) {
								setPhase("Result number " + (i + 1));
								setPhase("Attacker Dice value " + attackerDiceResults.get(i));
								setPhase("Defender Dice value " + defenderDiceResults.get(i));
								if (attackerDiceResults.get(i) > defenderDiceResults.get(i)) {
									if (!player.isCardGiven()) {
										if (player.getCardList() != null && !deck.deckOfCards.isEmpty()) {
											
											Card card = deck.draw();
											//card.getCarrdInfo(card);
											player.getCardList().add(card);
											player.setCardGiven(true);
										}
									}
									setPhase("Attacker wins this battle");
									destinationCountryObject.setArmies(destinationCountryObject.getArmies() - 1);
								} else {
									setPhase("Defender wins this battle");
									sourceCountryObject.setArmies(sourceCountryObject.getArmies() - 1);
								}
							} else {
								break;
							}
						}
						
						
						setPhase("Number of armies in " + sourceCountryObject.getCountryName() + " is "
								+ sourceCountryObject.getArmies());
						setPhase("Number of armies in " + destinationCountryObject.getCountryName() + " is "
								+ destinationCountryObject.getArmies());
					}
				} else {
					
				
					
					maximumAttackerDice = getMaxAttackerDiceCount (sourceCountryObject.getArmies());
					maximumDefenderDice = getMaxDefenderDiceCount(destinationCountryObject.getArmies());
					
					setPhase("###### The max attacker dice count is  #### : "+maximumAttackerDice);
					setPhase("###### The max attacker dice count is  #### : "+maximumDefenderDice);
					
					
					setPhase("Enter the number of dice to be roled. Maximum is " + maximumAttackerDice);
					attackerDice = scanner.nextInt();
					if (attackerDice > 3 && maximumAttackerDice == 3) {
						attackerDice = 3;
					} else if (attackerDice > maximumAttackerDice) {
						attackerDice = maximumAttackerDice;
					}
					setPhase("Number of dice rolled by attacker : " + attackerDice);

				

					setPhase("Enter the number of dice to be roled. Maximum is " + maximumDefenderDice);
					defenderDice = scanner.nextInt();
					if (defenderDice > 2 && maximumDefenderDice == 2) {
						defenderDice = 2;
					} else if (defenderDice > maximumDefenderDice) {
						defenderDice = maximumDefenderDice;
					}
					setPhase("Number of dice rolled by defender : " + defenderDice);
				
				attackerDiceResults = dice.rollDice(attackerDice);
				defenderDiceResults = dice.rollDice(defenderDice);

				setPhase("Attacker Dice Roll results");
				for (Integer result : attackerDiceResults) {
					System.out.print(result + " ");
				}
				System.out.println();
				setPhase("Defender Dice Roll results");
				for (Integer result : defenderDiceResults) {
					setPhase(result + " ");
				}
				System.out.println();
				Collections.sort(attackerDiceResults);
				Collections.reverse(attackerDiceResults);
				Collections.sort(defenderDiceResults);
				Collections.reverse(defenderDiceResults);
				int minimumDiceValue = attackerDice < defenderDice ? attackerDice : defenderDice;
				/*
				 * if(attackerDiceResults.size()<3) { while(attackerDiceResults.size()!=3) {
				 * attackerDiceResults.add(null); } } if(defenderDiceResults.size()<3) {
				 * while(defenderDiceResults.size()!=3) { defenderDiceResults.add(null); } }
				 */
				for (int i = 0; i < minimumDiceValue; i++) {
					if (attackerDiceResults.get(i) != null && defenderDiceResults.get(i) != null) {
						setPhase("Result number " + (i + 1));
						setPhase("Attacker Dice value " + attackerDiceResults.get(i));
						setPhase("Defender Dice value " + defenderDiceResults.get(i));
						if (attackerDiceResults.get(i) > defenderDiceResults.get(i)) {
							if (!player.isCardGiven()) {
								if (player.getCardList() != null && !deck.deckOfCards.isEmpty()) {
									Card card = deck.draw();
								//	card.getCarrdInfo(card);
									player.getCardList().add(card);
									player.setCardGiven(true);
								}
							}
							setPhase("Attacker wins this battle");
							destinationCountryObject.setArmies(destinationCountryObject.getArmies() - 1);
						} else {
							setPhase("Defender wins this battle");
							sourceCountryObject.setArmies(sourceCountryObject.getArmies() - 1);
						}
					} else {
						break;
					}
				}
				setPhase("Number of armies in " + sourceCountryObject.getCountryName() + " is "
						+ sourceCountryObject.getArmies());
				setPhase("Number of armies in " + destinationCountryObject.getCountryName() + " is "
						+ destinationCountryObject.getArmies());
				
			}
				if (destinationCountryObject.getArmies() < 1) {
					this.movableArmies = 0;
					playerLosesTheCountry(sourceCountryObject, destinationCountryObject);
					printAllCountriesOfaPlayer(sourceCountryObject.getBelongsToPlayer());
				}
				if (hasPlayerWon(player)) {
					setPhase("Player " + player.getName() + "has won the game");
					System.exit(0);
				}
				checkPlayerTurnCanContinue(player);

				if (player.getCanAttack()) {
					attackPhase(player);
				}
				else {
					setPhase("Exiting Attack as attack is not possible anymore");
				}
			} else if (choice.equalsIgnoreCase("no")) {
				setPhase("Player enter into fortify phase");
				player.setCanFortify(true);
			} else {
				setPhase("Invalid Option");
				setPhase("#### Moving to the next phase ####");
				player.setCanFortify(true);
			}
		} catch (Exception e) {
			System.out.println("Exception***************");
			e.printStackTrace();
			attackPhase(player);
		}
		player.setCardGiven(false);
		return pObject;
	}
	
	public int getMaxAttackerDiceCount(int armies)
	{
		int maximumAttackerDice = 0;
		
		if (armies > 3) {
		maximumAttackerDice = 3;
	} else if (armies== 3) {
		maximumAttackerDice = 2;
	} else if (armies == 2) {
		maximumAttackerDice = 1;
	}
		//System.out.println("### The maximumDefenderDice value is #### : "+maximumAttackerDice);
		return maximumAttackerDice;
	}
	
	
	
	public int getMaxDefenderDiceCount(int armies)
	{
		int maximumDefenderDice = 0;
		
		if (armies >= 2) {
			maximumDefenderDice = 2;
		} else {
			maximumDefenderDice = 1;
		}
		
		//System.out.println("### The maximumDefenderDice value is #### : "+maximumDefenderDice);
		return maximumDefenderDice;
	
	}
	
	
	public boolean hasPlayerWon(Player player) {
		if(player.getAssignedCountries().size() == MapContents.getInstance().getCountryAndNeighbors().keySet().size()) {
			return true;
		}
			return false;
	}

	private void printAllCountriesOfaPlayer(Player player) {
		System.out.println("Countries assigned to this player are");
		for (Country countryObj : player.getAssignedCountries()) {
			System.out.println(countryObj.getCountryName() + " : " + countryObj.getArmies());
		}
	}

	private void checkPlayerTurnCanContinue(Player player) {
		for (Country c : player.getAssignedCountries()) {
			setCanAttack(false);
			setCanFortify(false);
			if (c.getArmies() > 1 && checkNeighboringAttackableCountriesAndArmies(c, player)!=null && !checkNeighboringAttackableCountriesAndArmies(c, player).isEmpty()) {
				setCanAttack(true);
				setCanFortify(true);
				break;
			}
		}
		/*
		 * if(!canAttack && !canFortify){ nextPlayerTurn(model); }
		 */
	}

	public void playerLosesTheCountry(Country sourceCountryObject, Country destinationCountryObject) {
		destinationCountryObject.getBelongsToPlayer().getAssignedCountries().remove(destinationCountryObject);
		sourceCountryObject.getBelongsToPlayer().getAssignedCountries().add(destinationCountryObject);
		if (destinationCountryObject.getBelongsToPlayer().getAssignedCountries().size() == 0) {
			playerHasLost(sourceCountryObject, destinationCountryObject);
		}
		destinationCountryObject.setBelongsToPlayer(sourceCountryObject.getBelongsToPlayer());
		/*int movableArmies = 0;*/
		while (movableArmies == 0 || movableArmies >= sourceCountryObject.getArmies()) {
			System.out.println(
					"Enter the armies to be left behind (Has to be at least 1 and cannot be equal or gretaer than the armies of attacking country)");
			Scanner scanner = new Scanner(System.in);
			movableArmies = scanner.nextInt(); // To be refactored
		}
		if (movableArmies > 0) {
			sourceCountryObject.setArmies(sourceCountryObject.getArmies() - movableArmies);
			destinationCountryObject.setArmies(destinationCountryObject.getArmies() + movableArmies);
		}
	}

	public void playerHasLost(Country sourceCountryObject, Country destinationCountryObject) {
		destinationCountryObject.getBelongsToPlayer().setHasLost(true);
		// MapContents.getInstance().getPlayerList().remove(destinationCountryObject.getBelongsToPlayer());
		destinationCountryObject.getBelongsToPlayer().setEndGameForThisPlayer(true);
		List<Card> listOfDefenderCards = destinationCountryObject.getBelongsToPlayer().getCardList();
		for (Card card : listOfDefenderCards)
			sourceCountryObject.getBelongsToPlayer().getCardList().add(card);
			destinationCountryObject.getBelongsToPlayer().setCardList(new ArrayList<Card>());
		// System.out.println("To be Implemented");

	}

	public Country getAttackableCountryOfCountryListFromString(String destinationCountry,
			List<Country> attackableCountryList) {
		MapContents contents = MapContents.getInstance();
		HashMap<Country, List<Country>> countriesAndItsNeighbours = contents.getCountryAndNeighbors();
		for (Country country : countriesAndItsNeighbours.keySet()) {
			if (destinationCountry != null && !destinationCountry.isEmpty() && attackableCountryList.contains(country)
					&& destinationCountry.equals(country.getCountryName())) {
				return country;
			}
		}
		return null;
	}

	public Country reenterTheDestinationCountry(List<Country> attackableCountryList) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the name of the country on which you want to attack or enter 'quit' to exit the attack");
		String destinationCountry = scanner.nextLine();
		if(destinationCountry.equals("quit")) {
			Country country = new Country("quit");
			return country;
		}
		Country destinationCountryObject = getAttackableCountryOfCountryListFromString(destinationCountry,
				attackableCountryList);
		if (destinationCountryObject == null) {
			reenterTheDestinationCountry(attackableCountryList);
		}
		return destinationCountryObject;
	}

	public Country reenterTheCountry(Player player) {
		Scanner scanner = new Scanner(System.in);
		String Country;
		Country CountryObject;
		System.out.println("Enter the name of the country through which you want to attack or enter 'quit' to exit the attack");
		Country = scanner.nextLine();
		if(Country.equals("quit")) {
			return null;
		}
		CountryObject = getSourceCountryFromString(Country);
		if (CountryObject == null) {
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

		try {
			setDomination();
			
			setCurrentPhase(Player.reinforcePhase);
			player.setCurrentPhase(Player.reinforcePhase);
			Scanner scanner;
			scanner = new Scanner(System.in);
			int armiesToBeGiven = 0;
			gamePlayerList = new ArrayList<Player>();
			System.out.println(
					"																													");
			setPhase("\n######## " + player.getName() + "  reinforcement phase begins ########");
			assignedArmies = calculateReiforcementArmies(player.getAssignedCountries().size());
			
			
			
			
			List<Continent> currcontControlList = new ArrayList();
			int armiesContControl = 0;
			currcontControlList = contienentControlList(player);
			if(currcontControlList!=null)
			{
				for(Continent cont : currcontControlList)
				{
					setPhase(" ##### continent name is ###### "+cont.getContinentName()  +"     control value is "+cont.getContinentControlValue() );
					armiesContControl = armiesContControl + cont.getContinentControlValue();
				}
			}
			
			
			assignedArmies = assignedArmies + armiesContControl;
			
					
			CardView cardView= new CardView();
			armiesToBeGiven=cardView.exchangeCards(player);
			assignedArmies += armiesToBeGiven;
			System.out.println(
					"																													");
			setPhase("#### The total number of armies to be reinforced are  #### :" + assignedArmies);
			player.setTotalArmies(assignedArmies);
			int counter = player.getTotalArmies();
			int armiesCounter;

			
			while (counter > 0) {
				printCountriesOwnedByPlayer(player);
				System.out.println(
						"																													");

				setPhase("#### The number of armies to be reinforced are  #### :" + counter);
				setPhase(
						"##### Select the country name,armies (comma , seperated) in which you want to assign armies ######");
				nameArmiesSpilt = scanner.nextLine().split(",");
				countryName = nameArmiesSpilt[0];
				armiesCount = nameArmiesSpilt[1];

				if (!isNumeric(armiesCount)) {
					armiesCount = reEnterArmyCountry();
				}

				if (!isValidSourceCountry(countryName, player)) {
					countryName = reEnterCountry(player);
				}

			
				System.out.println(
						"																													");
				setPhase("##### The selected country name is  ####     : " + countryName);
				setPhase("##### The army count  name is          ####     : " + armiesCount);
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
					setPhase(
							"##### The entered army count is greater than the remaining armies. Please enter a value below the ramining armies ######");
					setPhase("##### The reamining armies are ##### :" + counter);
				}

			}

			setPhase("######### Player army count after reinforcment  ####### ");
			setPhase("######## Player Name ########### : " + player.getName());
			for (Country country : player.getAssignedCountries()) {

				setPhase("					##### The Country Name  ####### : " + country.getCountryName());
				setPhase("					##### The Army Count      ####### : " + country.getArmies());

			}

		} catch (Exception e) {
			System.out.println("Exception Message : " + e.getMessage()); 
			e.printStackTrace();
			reinforcePhase(player);
		}
		setPhase("########" + player.getName() + "  reinforcement phase ended ########");
		player.setCanAttack(true);
		return player;

	}

	
public Player exChangeCardTerritoryExist(List<Card> exchangeCards,Player player) {
		

		Player playerObject = new Player();
		playerObject = player;
		
		List<Country> countryList = new ArrayList();
		countryList = player.getAssignedCountries();
		
		List<Country> updatedCountryList = new ArrayList();
		
		List<Card> exchangeCardsLocal = new ArrayList();
		
		
		System.out.println(" ###### Inside Exchange card Territory ####### ");
		
		for(Country c : countryList)
		{
			for (Card card : exchangeCards)
			{
				String[] utilString = card.getName().split(",");
				String countryName = utilString[0]; 
				if(countryName.equalsIgnoreCase(c.getCountryName()))
				{
					System.out.println(" ###### country name inside exchnage is  #######  :"+c.getCountryName());
					System.out.println(" ###### armies before inside exchnage is  ####### : "+c.getArmies());
					int armies = c.getArmies();
					armies = armies + 2;
					c.setArmies(armies);
					System.out.println(" ###### armies after inside exchnage is  ####### : "+c.getArmies());
				}
			}
			updatedCountryList.add(c);
		}

		playerObject.setAssignedCountries(updatedCountryList);
		return playerObject;
		
	}

	public List<Continent> contienentControlList(Player player)
	{
		
		System.out.println("####### calculateReinforcementArmiesContienentand ########");
		MapContents contents = MapContents.getInstance();
		System.out.println("########## Printing contienet and its countries #############");
		HashMap<Continent, List<Country>> continentAndItsCountries = new  HashMap<>();
		HashMap<String , Integer> continentAndControlValue = new  HashMap<>();
		
		continentAndItsCountries= contents.getContinentAndItsCountries();
		List<String> continentName = new ArrayList();
		Map<Player,List<String>> playerContinentControl = new HashMap();
		List<Continent> continentList = new ArrayList();
		int armies = 0;

		for (Map.Entry<Continent, List<Country>> entryKeyValue : continentAndItsCountries.entrySet())
		{
			String name = entryKeyValue.getKey().getContinentName();
			int controlValue = entryKeyValue.getKey().getContinentControlValue();
			System.out.println("																				");
			System.out.println("####### The  contienent name is ####### :"+name);
			System.out.println("																				");
			System.out.println("###### The continent control value is #### :"+entryKeyValue.getKey().getContinentControlValue());
			int coutryContinentCount = 0;
			int playerOwnedCountryCount = 0;
			coutryContinentCount = entryKeyValue.getValue().size();
			for (Country c : entryKeyValue.getValue())
			{
				System.out.println("     "+c.getCountryName()  + "   owned by player "+c.getBelongsToPlayer().getName() );
				if(c.getBelongsToPlayer().getName().equalsIgnoreCase(player.getName()))
				{
					playerOwnedCountryCount = playerOwnedCountryCount + 1;
				}
				
				
			}
			if(playerOwnedCountryCount == coutryContinentCount)
			{
				
				continentList.add(entryKeyValue.getKey());
				
			}
			
			System.out.println("######### No of countries in the continent          ######## : "+coutryContinentCount);
			System.out.println("######### No of countries owned by the player  ######## : "+playerOwnedCountryCount);
	
			
		}
		
		System.out.println("####### List size before returning is ####### : "+continentList.size());
		
		return continentList;
	}

	
	public boolean checkCardDifferentTypes(List<Card> exchangeCards, int cardTypes) {
		if(cardTypes<3) {
			return false;
		}
		int typesCount=0;
		List<String> types = new ArrayList<>();
		for(Card card : exchangeCards) {
			types.add(card.getType());
		}
		if(!types.get(0).equals(types.get(1)) && !types.get(1).equals(types.get(2)) && !types.get(2).equals(types.get(0))) {
			cardExchangeTypeCount = 3;
			return true;
		}
		return false;
	}

	public boolean checkCardSameType(List<Card> exchangeCards,  String cardAppearingThrice) {
		int cardAppearingCount=0;
		if(exchangeCards.size()<3) {
			return false;
		}
		else {
			for(Card card : exchangeCards) {
				if(card.getType().equals(cardAppearingThrice)) {
					cardAppearingCount++;
				}
			}
			if(cardAppearingCount==3) {
				cardExchangeAppearingMoreThanThrice = cardAppearingThrice;
				return true;
			}
		}
		return false;
	}

	/**
	 * The following method requests the user to re enter the army count in event of
	 * invalid input in the first attempt.
	 * 
	 * @return Returns the army count.
	 */

	public String reEnterArmyCountry() {
		String armycount = "0";
		Scanner sc = new Scanner(System.in);
		System.out.println("#### Please enter a valid integer for army reinforcment #####");
		armycount = sc.nextLine();

		if (!isNumeric(armycount))
			armycount = reEnterArmyCountry();

		return armycount;
	}

	/**
	 * The following asks the user to reenter the country name in event of invalid
	 * input in the first attempt.
	 * 
	 * @param player Player instance.
	 * @return Returns the country name entered by the user.
	 */
	public String reEnterCountry(Player player) {
		System.out.println("##### Please enter a valid country  to be reinforced #####");
		Scanner sc = new Scanner(System.in);
		String countryName = null;
		countryName = sc.nextLine();

		if (!isValidSourceCountry(countryName, player)) {
			countryName = reEnterCountry(player);
		}
		System.out.println("Return country name is " + countryName);
		return countryName;
	}

	/**
	 * The following method checks if it is valid source country for fortification.
	 * 
	 * @param countryName  The name of the source country.
	 * @param playerObject The player instance
	 * @return Returns true if the country is valid else returns false
	 */

	public boolean isValidSourceCountry(String countryName, Player playerObject) {
		boolean returnValue = false;

		for (Country countryInstance : playerObject.getAssignedCountries()) {
			if (countryInstance.getCountryName().equalsIgnoreCase(countryName)) {
				returnValue = true;
				break;
			}
		}

		return returnValue;
	}

	public void exchangeCards(int cardTypes, String cardAppearingMoreThanThrice, Player player, List<Integer> cardNumbers) throws Exception {

		if(cardTypes==3 || (cardAppearingMoreThanThrice!=null && !cardAppearingMoreThanThrice.isEmpty())){
			Card card1 = player.getCardList().get(cardNumbers.get(0)-1);
			Card card2 = player.getCardList().get(cardNumbers.get(1)-1);
			Card card3 = player.getCardList().get(cardNumbers.get(2)-1);
			int a=cardNumbers.get(0)-1;
			int b=cardNumbers.get(1)-1;
			int c =cardNumbers.get(2)-1;
			boolean s=  player.getCardList().remove(card1);
			player.getCardList().remove(card2);
			player.getCardList().remove(card3);
			
			deck.add(card1);
			deck.add(card2);
			deck.add(card3);
			exchanged = true;
			setCardList(player.getCardList());
			setChanged();
			notifyObservers(player);
		}
		
		else{
			System.out.println("Exchange not possible as the numbers are wrong");
			throw new Exception();
		}
			

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
	 * 
	 * @param player  the player object to which the country belongs
	 * @param country string value of the country
	 * @return the country object
	 */
	public Country getSourceCountryFromString(String sourceCountry) {
		MapContents contents = MapContents.getInstance();
		HashMap<Country, List<Country>> countriesAndItsNeighbours = contents.getCountryAndNeighbors();
		for (Country country : countriesAndItsNeighbours.keySet()) {
			if (sourceCountry != null && !sourceCountry.isEmpty() && sourceCountry.equals(country.getCountryName())) {
				return country;
			}
		}
		return null;
	}
	
	
	/**
	 * To get the country object from the string value of the country if it belongs to the player
	 * 
	 * @param player  the player object to which the country belongs
	 * @param country string value of the country
	 * @return the country object
	 */
	public Country getSourceCountryFromPlayerUsingString(String sourceCountry,Player player) {
		MapContents contents = MapContents.getInstance();
		HashMap<Country, List<Country>> countriesAndItsNeighbours = contents.getCountryAndNeighbors();
		for (Country country : countriesAndItsNeighbours.keySet()) {
			if (sourceCountry != null && !sourceCountry.isEmpty() && sourceCountry.equals(country.getCountryName())&&  player.getAssignedCountries().contains(country)) {
				return country;
			}
		}
		return null;
	}

	/**
	 * This method prints all countries owned by a player
	 * 
	 * @param player the Player object
	 */
	public void printAllCountriesOwnedByPlayer(Player player) {
		for (Country countryObj : player.getAssignedCountries()) {
			System.out.print(countryObj.getCountryName() + ",");
		}
	}

	/**
	 * Prints the neighboring attackable countries
	 * 
	 * @param country
	 * @param player
	 * @return
	 */
	public List<Country> printNeighboringAttackableCountriesAndArmies(Country country, Player player) {
		System.out.println("######Neighbouring Countries on which you can attack and its armies are :#####");
		List<Country> neighbouringAttackableCountries = new ArrayList<>();
		for (Country countryObject : country.getNeighbouringCountries()) {
			Country neighboringCountry = getSourceCountryFromString(countryObject.getCountryName());
			if (!neighboringCountry.getBelongsToPlayer().equals(player)) {
				System.out.println(neighboringCountry.getCountryName() + "  :  " + neighboringCountry.getArmies());
				neighbouringAttackableCountries.add(neighboringCountry);
			}
		}
		return neighbouringAttackableCountries;
	}

	public List<Country> checkNeighboringAttackableCountriesAndArmies(Country country, Player player) {
		List<Country> neighbouringAttackableCountries = new ArrayList<>();
		for (Country countryObject : country.getNeighbouringCountries()) {
			Country neighboringCountry = getSourceCountryFromString(countryObject.getCountryName());
			if (!neighboringCountry.getBelongsToPlayer().equals(player)) {
				//System.out.println(neighboringCountry.getCountryName() + "  :  " + neighboringCountry.getArmies());
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

	/**
	 * @return the cardGiven
	 */
	public boolean isCardGiven() {
		return cardGiven;
	}

	/**
	 * @param cardGiven the cardGiven to set
	 */
	public void setCardGiven(boolean cardGiven) {
		this.cardGiven = cardGiven;
	}

	/**
	 * @return the endGameForThisPlayer
	 */
	public boolean isEndGameForThisPlayer() {
		return endGameForThisPlayer;
	}

	/**
	 * @param endGameForThisPlayer the endGameForThisPlayer to set
	 */
	public void setEndGameForThisPlayer(boolean endGameForThisPlayer) {
		this.endGameForThisPlayer = endGameForThisPlayer;
	}
	
	/**
	 * @return errorMessage returns error message
	 */
	public String getErrorMesage() {
		return errorMesage;
	}

	/**
	 * @param errorMesage sets error message
	 */
	public void setErrorMesage(String errorMesage) {
		this.errorMesage = errorMesage;
	}

	/**
	 * @return the exchanged
	 */
	public boolean getExchanged() {
		return exchanged;
	}

	/**
	 * @param exchanged the exchanged to set
	 */
	public void setExchanged(boolean exchanged) {
		this.exchanged = exchanged;
	}
}
