package com.concordia.riskGame.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.concordia.riskGame.model.Card.Deck;
import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.Player.Player;
import com.concordia.riskGame.model.Tournament.Tournament;

public class TournamentGameDriver {
	
	
	private HashMap<Country, List<Country>> gmcountryAndNeighbours;
	private boolean endTheGame = false;
	public int turns=0;
	/**
	 * The following method calls each of the game phase for each player.
	 * 
	 * @throws Exception throws exception
	 */
	public void gamePhase(HashMap<String, String> playerNameAndTypes,List<String> gameMapFiles,int numberOfGames,int numberOfTurns) throws Exception {
		MapContents mapContents =MapContents.getInstance();
		gmcountryAndNeighbours = new HashMap<Country, List<Country>>();
		gmcountryAndNeighbours = mapContents.getCountryAndNeighbors();
		Scanner scanner = new Scanner(System.in);
		endTheGame=false;
		for(String fileName : gameMapFiles) {
			
		
		turns=0;
		Deck deck = Deck.getInstance();
		deck.setDeckOfCards(mapContents.getCountryList());
		Tournament tournament = new Tournament(playerNameAndTypes, gameMapFiles, numberOfGames, numberOfTurns);
		while(!endTheGame && turns < numberOfTurns) {
			List<Player> removablePlayers = new ArrayList<>();
			for(Player player : mapContents.getPlayerList()) {
				if(player.isHasLost()) {
					removablePlayers.add(player);
				}
			}
			mapContents.getPlayerList().removeAll(removablePlayers);
		Iterator<Player> iterator = mapContents.getPlayerList().iterator();
		while(iterator.hasNext()) {
			turns++;
			Player playerInstance = new Player();
			Player p = iterator.next();
			if(!p.isHasLost()) {
			playerInstance = p.strategy.reinforcePhase(p);
			if(playerInstance.getCanAttack()) {
			playerInstance = playerInstance.strategy.attackPhase(playerInstance);
			}
			if(playerInstance.getHasWon()) {
				System.out.println("Save the result and Exit the loop");
			}
			if(playerInstance.getCanFortify()) {
			playerInstance = playerInstance.strategy.forfeitPhase(playerInstance);
			}
			}
		}
		if(turns == numberOfTurns) {
			System.out.println("Update the result as draw");
		}
		}
		
		}
		if(endTheGame) {
			System.exit(0);
		}
		
	

	}

}
