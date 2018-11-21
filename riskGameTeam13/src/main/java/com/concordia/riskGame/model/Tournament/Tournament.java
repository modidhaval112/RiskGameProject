package com.concordia.riskGame.model.Tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tournament {
	
	public int numberOfTurns;
	public int numberOfGames;
	public List<String> gameMapFiles = new ArrayList<>();
	HashMap<String, String> playerNameAndTypes = new HashMap<>();
	
	
	/**
	 * This method assigning the map files,count of games and turns
	 * @param playerNameAndTypes MAp of the player and the types of players
	 * @param gameMapFiles list of game map file names
	 * @param numberOfGames count of the games played
	 * @param numberOfTurns count of the turns
	 */
	public Tournament(HashMap<String, String> playerNameAndTypes ,List<String> gameMapFiles, int numberOfGames, int numberOfTurns) {
		super();
		this.playerNameAndTypes = playerNameAndTypes;
		this.gameMapFiles = gameMapFiles;
		this.numberOfGames = numberOfGames;
		this.numberOfTurns = numberOfTurns;
	}
	
}
