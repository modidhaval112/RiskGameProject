package com.concordia.riskGame.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.concordia.riskGame.View.CardView;
import com.concordia.riskGame.model.Card.Card;
import com.concordia.riskGame.model.Card.Deck;
import com.concordia.riskGame.model.Continent.Continent;
import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.dice.Dice;

public class AggresivePlayer implements PlayerStrategy{

	@Override
	public Player reinforcePhase(Player player)  {
		try {
		player.setDomination();
		player.setCurrentPhase(Player.reinforcePhase);
		int armiesContControl = 0;
		int assignedArmies = 0;
		int armiesToBeGiven = 0;
		player.setPhase("#### Aggressive Player Reinforcement Phase");
		List<Continent> currcontControlList = new ArrayList();
		 currcontControlList = player.contienentControlList(player);
		if(currcontControlList!=null)
		{
			for(Continent cont : currcontControlList)
			{
				player.setPhase(" ##### continent name is ###### "+cont.getContinentName()  +" and     control value is "+cont.getContinentControlValue() );
				armiesContControl = armiesContControl + cont.getContinentControlValue();
			}
		}
		player.setPhase("#### The armies to be assigned from control value of continent is ###### : "+armiesContControl);
		assignedArmies = assignedArmies + armiesContControl;
		CardView cardView= new CardView();
		armiesToBeGiven=cardView.exchangeCardsForComputerPlayers(player);
		assignedArmies += armiesToBeGiven;
		player.setPhase("#### The total number of armies to be reinforced are  #### :" + assignedArmies);
		Country strongestCountry = getStrongestCountry(player);
		strongestCountry.setArmies(strongestCountry.getArmies()+assignedArmies);
		player.setPhase("######### Player army count after reinforcment  ####### ");
		player.setPhase("######## Player Name ########### : " + player.getName());
		for (Country country : player.getAssignedCountries()) {
			player.setPhase("					##### The Country Name  ####### : " + country.getCountryName());
			player.setPhase("					##### The Army Count      ####### : " + country.getArmies());
		}
		}
		catch (Exception e) {
			System.out.println("Exception Message : " + e.getMessage()); 
			reinforcePhase(player);
		}
		player.setCanAttack(true);
		return player;
	}
	
	/**
	 * This method is to get the strongest country of a player
	 * @param player
	 * @return country with most number of armies
	 */
	private Country getStrongestCountry(Player player) {
		List<Country> playerOwnedCountries = player.getAssignedCountries();
		Country strongestCountry = null;
		int armyCount = 0;
		for(Country country : playerOwnedCountries) {
			if(country.getArmies()>armyCount) {
				armyCount = country.getArmies();
				strongestCountry = country;
			}
		}
		return strongestCountry;
	}

	@Override
	public Player attackPhase(Player player) {
		player.setCurrentPhase(Player.attackPhase);
		player.setDomination();
		int attackerDice = 0;
		int maximumAttackerDice = 0;
		int maximumDefenderDice = 0;
		int defenderDice = 0;
		Dice dice = new Dice();
		Deck deck = Deck.getInstance();
		List<Integer> attackerDiceResults;
		List<Integer> defenderDiceResults;
		Country sourceCountryObject;
		Country destinationCountryObject;
		player.setPhase("#### Aggressive Player Attack Phase");
		int attackableCount = player.getAssignedCountries().size();
		while(attackableCount!=0) {
		sourceCountryObject = getStrongestCountry(player);
		int numberOfNeighbours = sourceCountryObject.getNeighbouringCountries().size();
		while(sourceCountryObject.getArmies()>1 && numberOfNeighbours>0) {
			destinationCountryObject = player.getSourceCountryFromString(sourceCountryObject.getNeighbouringCountries().get(numberOfNeighbours-1).getCountryName());
			if(destinationCountryObject.getBelongsToPlayer().equals(player)) {
				numberOfNeighbours--;
				continue;
			}
				while (sourceCountryObject.getArmies() > 1 && destinationCountryObject.getArmies() != 0) {
					maximumAttackerDice = player.getMaxAttackerDiceCount (sourceCountryObject.getArmies());
					maximumDefenderDice = player.getMaxDefenderDiceCount(destinationCountryObject.getArmies());
					System.out.println();
					player.setPhase("###### The maximum number of dice attacker can roll is  #### : "+maximumAttackerDice);
					player.setPhase("###### The maximum number of dice defender can roll is  #### : "+maximumDefenderDice);
					attackerDiceResults = dice.rollDice(maximumAttackerDice);
					defenderDiceResults = dice.rollDice(maximumDefenderDice);
					player.setPhase(
							"Attacker Dice Roll results : " + attackerDiceResults.size() + " dice has been rolled");
					for (Integer result : attackerDiceResults) {
						player.setPhase(result + " ");
					}
					player.setPhase(
							"Defender Dice Roll results" + defenderDiceResults.size() + " dice has been rolled");
					for (Integer result : defenderDiceResults) {
						player.setPhase(result + " ");
					}
					Collections.sort(attackerDiceResults);
					Collections.reverse(attackerDiceResults);
					Collections.sort(defenderDiceResults);
					Collections.reverse(defenderDiceResults);
					int minimumDiceValue = maximumAttackerDice < maximumDefenderDice ? maximumAttackerDice: maximumDefenderDice;
					for (int i = 0; i < minimumDiceValue; i++) {
						if (attackerDiceResults.get(i) != null && defenderDiceResults.get(i) != null) {
							player.setPhase("Result number " + (i + 1));
							player.setPhase("Attacker Dice value " + attackerDiceResults.get(i));
							player.setPhase("Defender Dice value " + defenderDiceResults.get(i));
							if (attackerDiceResults.get(i) > defenderDiceResults.get(i)) {
								if (!player.isCardGiven()) {
									if (player.getCardList() != null && !deck.deckOfCards.isEmpty()) {
										Card card = deck.draw();
										player.getCardList().add(card);
										player.setCardGiven(true);
									}
								}
								player.setPhase("Attacker wins this battle");
								destinationCountryObject.setArmies(destinationCountryObject.getArmies() - 1);
							} else {
								player.setPhase("Defender wins this battle");
								sourceCountryObject.setArmies(sourceCountryObject.getArmies() - 1);
							}
						} else {
							break;
						}
					}
					player.setPhase("Number of armies in " + sourceCountryObject.getCountryName() + " is "
							+ sourceCountryObject.getArmies());
					player.setPhase("Number of armies in " + destinationCountryObject.getCountryName() + " is "
							+ destinationCountryObject.getArmies());
				}
				if (destinationCountryObject.getArmies() < 1) {
					//this.movableArmies = 1;
					playerLosesTheCountry(sourceCountryObject, destinationCountryObject);
					player.printAllCountriesOfaPlayer(sourceCountryObject.getBelongsToPlayer());
				}
				if (player.hasPlayerWon(player)) {
					player.setPhase("Player " + player.getName() + "has won the game");
					System.exit(0);
				}
				player.checkPlayerTurnCanContinue(player);
				numberOfNeighbours--;
			}
		attackableCount--;
		}
		checkPlayerTurnCanContinue(player);
		player.setCardGiven(false);
		return player;
		}
		
	/**
	 * This method transfer country from one player to another player when player losses the country 
	 * @param sourceCountryObject Source Country Object
	 * @param destinationCountryObject destination country object
	 */
	public void playerLosesTheCountry(Country sourceCountryObject, Country destinationCountryObject) {
		destinationCountryObject.getBelongsToPlayer().getAssignedCountries().remove(destinationCountryObject);
		sourceCountryObject.getBelongsToPlayer().getAssignedCountries().add(destinationCountryObject);
		if (destinationCountryObject.getBelongsToPlayer().getAssignedCountries().size() == 0) {
			playerHasLost(sourceCountryObject, destinationCountryObject);
		}
		destinationCountryObject.setBelongsToPlayer(sourceCountryObject.getBelongsToPlayer());
		int movableArmies = 1;
		/*while (movableArmies <= 0 || movableArmies >= sourceCountryObject.getArmies()) {
			System.out.println(
					"Enter the armies to be left behind (Has to be at least 1 and cannot be equal or gretaer than the armies of attacking country)");
			Scanner scanner = new Scanner(System.in);
			movableArmies = scanner.nextInt();
		}*/
		if (movableArmies > 0) {
			sourceCountryObject.setArmies(sourceCountryObject.getArmies() - movableArmies);
			destinationCountryObject.setArmies(destinationCountryObject.getArmies() + movableArmies);
		}
	}
	
	
	/**
	 * This method do needed operations after player has lost
	 * @param sourceCountryObject source country object
	 * @param destinationCountryObject destination country object
	 */
	public void playerHasLost(Country sourceCountryObject, Country destinationCountryObject) {
		destinationCountryObject.getBelongsToPlayer().setHasLost(true);
		destinationCountryObject.getBelongsToPlayer().setEndGameForThisPlayer(true);
		List<Card> listOfDefenderCards = destinationCountryObject.getBelongsToPlayer().getCardList();
		for (Card card : listOfDefenderCards)
			sourceCountryObject.getBelongsToPlayer().getCardList().add(card);
		destinationCountryObject.getBelongsToPlayer().setCardList(new ArrayList<Card>());
	}

	@Override
	public Player forfeitPhase(Player player) {
		player.setCurrentPhase(Player.fortificationPhase);
		player.setDomination();
		player.setPhase("#### Aggressive Player Forfeit Phase");
		player.setPhase("#### Before Fortification ####");
		player.printAllCountriesOfaPlayer(player);
		boolean fortificationDone = false;
		List<Country> sortedCountryList = getSortedCountryListBasedOnArmy(player);
		Country sourceCountry = null;
		Country destinationCountry = null;
		int countriesSize = sortedCountryList.size();
		while(!fortificationDone && countriesSize>0 ) {
			destinationCountry = sortedCountryList.get(countriesSize-1);
			for(Country country2 : destinationCountry.getNeighbouringCountries()) {
				sourceCountry = player.getSourceCountryFromPlayerUsingString(country2.getCountryName(), player);
				if(sourceCountry!=null && sourceCountry.getArmies()>1) {
					fortificationDone = true;
					break;
				}
			}
			countriesSize--;
		}
		player.setPhase("					###########    Source country      	     ###############       : "
				+ sourceCountry);
		player.setPhase(
				"					###########  Destination Country   	 ###############       : " + destinationCountry);
		player.setPhase("					############   Armies to be moved    ###############      : "
				+ (sourceCountry.getArmies()-1));
		destinationCountry.setArmies(destinationCountry.getArmies() + sourceCountry.getArmies()-1);
		sourceCountry.setArmies(1);
		player.setPhase("#### After Fortification ####");
		player.printAllCountriesOfaPlayer(player);
		return player;
	}
	
	/**
	 * This method is to sort the country list of a player based on the army count of the countries in ascending order
	 * @param player the player object is passed
	 * @return a sorted list of countries
	 */
	public List<Country> getSortedCountryListBasedOnArmy(Player player) {
		List<Integer> armiesList = new ArrayList<>();
		for(Country country : player.getAssignedCountries()) {
			Country playerCountry = player.getSourceCountryFromString(country.getCountryName());
			armiesList.add(playerCountry.getArmies());
		}
		List<Country> sortedCountryList = new ArrayList<>();
		Collections.sort(armiesList);
		for(Integer army : armiesList) {
			for(Country country : player.getAssignedCountries()) {
			Country playerCountry = player.getSourceCountryFromString(country.getCountryName());
				if( playerCountry.getArmies() == army  && !sortedCountryList.contains(playerCountry) ) {
				sortedCountryList.add(playerCountry);
				break;
				}
			}
		}
		return sortedCountryList;
	}
	
	
	/**
	 * This method checks if player's turn can continue or not
	 * @param player player object
	 */
	void checkPlayerTurnCanContinue(Player player) {
		for (Country c : player.getAssignedCountries()) {
			player.setCanAttack(false);
			player.setCanFortify(false);
			if (c.getArmies() > 1 && player.checkNeighboringAttackableCountriesAndArmies(c, player)!=null && !player.checkNeighboringAttackableCountriesAndArmies(c, player).isEmpty()) {
				player.setCanAttack(true);
				player.setCanFortify(true);
				break;
			}
		}
	}
	
}
