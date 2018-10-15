package com.concordia.riskGame.entity;




import javax.swing.*;
import javax.swing.plaf.basic.BasicIconFactory;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * This is entity class to set and get properties of player.
 *
 * @author Darwin Anirudh G
 */
public class Player  implements Serializable {
	
    public String name;
    public int currentPlayerReinforceArmies;
    public int playerCount;
    public int totalArmies;
    public int reinforcementArmies;
    public List<Country> assignedCountries;
    public Map<Player, List<Country>> playerAssign;
    public Player() {
    	
	}
	public Player(String name) {
		this.name = name;
	}
	public Map<Player, List<Country>> getPlayerAssign() {
		return playerAssign;
	}
	public void setPlayerAssign(Map<Player, List<Country>> playerAssign) {
		this.playerAssign = playerAssign;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurrentPlayerReinforceArmies() {
		return currentPlayerReinforceArmies;
	}
	public void setCurrentPlayerReinforceArmies(int currentPlayerReinforceArmies) {
		this.currentPlayerReinforceArmies = currentPlayerReinforceArmies;
	}
	public int getPlayerCount() {
		return playerCount;
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	public int getTotalArmies() {
		return totalArmies;
	}
	public void setTotalArmies(int totalArmies) {
		this.totalArmies = totalArmies;
	}
	public int getReinforcementArmies() {
		return reinforcementArmies;
	}
	public void setReinforcementArmies(int reinforcementArmies) {
		this.reinforcementArmies = reinforcementArmies;
	}
	public List<Country> getAssignedCountries() {
		return assignedCountries;
	}
	public void setAssignedCountries(List<Country> assignedCountries) {
		this.assignedCountries = assignedCountries;
	}

    
 }

