package com.concordia.riskGame.model.Tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tournament {
	
	public int noOfTurns;
	public int noOfGames;
	public List<String> mapFiles = new ArrayList<>();
	HashMap<String, String> playerNamesAndTypes = new HashMap<>();
	
	
	/**
	 * This method assigning the map files,count of games and turns
	 * @param playerNameAndTypes MAp of the player and the types of players
	 * @param mapFiles list of game map file names
	 * @param numberOfGames count of the games played
	 * @param numberOfTurns count of the turns
	 */
	public Tournament(HashMap<String, String> playerNamesAndTypes ,List<String> mapFiles, int noOfGames, int noOfTurns) {
		super();
		this.playerNamesAndTypes = playerNamesAndTypes;
		this.mapFiles = mapFiles;
		this.noOfGames = noOfGames;
		this.noOfTurns = noOfTurns;
	}



}
