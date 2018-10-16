package com.concordia.riskGame.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * This is entity class to set and get properties of player.
 *
 * @author Darwin Anirudh G
 */
public class Player implements Serializable {

	private String name;
	private int currentPlayerReinforceArmies;
	private int playerCount;
	private int totalArmies;
	private int reinforcementArmies;
	private List<Country> assignedCountries;
	private Map<Player, List<Country>> playerAssign;

	/**
	 * default constructor
	 */
	private Player() {
	}

	/**
	 * Parameterized constructor to set player Name
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * This method is used to return Map of Player and Countries assigned to him.
	 * @return playerAssign Players and assigned countries to him
	 */
	public Map<Player, List<Country>> getPlayerAssign() {
		return playerAssign;
	}

	/**
	 * This method sets map of player and countries assigned to him.
	 * @param playerAssign
	 */
	public void setPlayerAssign(Map<Player, List<Country>> playerAssign) {
		this.playerAssign = playerAssign;
	}

	/**
	 * This method returns player name
	 * @return name Player Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets player name
	 * @param name Player Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns number of Armies player have
	 * @return currentPlayerReinforceArmies number of Armies player have
	 */
	public int getCurrentPlayerReinforceArmies() {
		return currentPlayerReinforceArmies;
	}

	/**
	 * This method sets number of Armies player have
	 * @param currentPlayerReinforceArmies  number of armies player have
	 */
	public void setCurrentPlayerReinforceArmies(int currentPlayerReinforceArmies) {
		this.currentPlayerReinforceArmies = currentPlayerReinforceArmies;
	}

	/**
	 * This method returns number of players
	 * @return playerCount no of players
	 */
	public int getPlayerCount() {
		return playerCount;
	}

	/**
	 * This method sets number of players
	 * @param playerCount number of players
	 */
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	/**
	 * This method is returns total number of armies
	 * @return totalArmies number of Armies
	 */
	public int getTotalArmies() {
		return totalArmies;
	}

	/**
	 * This method sets total number of armies
	 * @param totalArmies number of armies
	 */
	public void setTotalArmies(int totalArmies) {
		this.totalArmies = totalArmies;
	}

	/**
	 * This method returns number of reinforcement armies.
	 * @return reinforcementArmies number of reinforcement armies
	 */
	public int getReinforcementArmies() {
		return reinforcementArmies;
	}

	/**
	 * This method sets number of reinforcement armies
	 * @param reinforcementArmies number of reinforcement armies
	 */
	public void setReinforcementArmies(int reinforcementArmies) {
		this.reinforcementArmies = reinforcementArmies;
	}

	/**
	 * This method returns list of countries assigned to player
	 * @return assignedCountries list of countries
	 */
	public List<Country> getAssignedCountries() {
		return assignedCountries;
	}

	/**
	 * This method sets list of countries assigned to player
	 * @param assignedCountries list of countries
	 */
	public void setAssignedCountries(List<Country> assignedCountries) {
		this.assignedCountries = assignedCountries;
	}
}
