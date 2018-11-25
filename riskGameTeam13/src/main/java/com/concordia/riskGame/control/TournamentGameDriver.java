package com.concordia.riskGame.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import com.concordia.riskGame.model.Card.Deck;
import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.Map.MapParseProcessor;
import com.concordia.riskGame.model.Player.Player;
import com.concordia.riskGame.model.Tournament.Tournament;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @author saich
 *
 */
public class TournamentGameDriver {

	private static Logger LOGGER = LogManager.getLogger();

	private HashMap<Country, List<Country>> gmcountryAndNeighbours;
	private boolean endTheGame = false;
	public int turns=0;
	private List<String> results = new ArrayList<>();
	MapParseProcessor mapParseObject;
	/**
	 * The following method calls each of the game phase for each player.
	 * 
	 * @throws Exception throws exception
	 */
	public void gamePhase(HashMap<String, String> playerNamesAndTypes,List<String> gameMapFiles,int noOfGames,int noOfTurns) throws Exception {



		Tournament tournament = new Tournament(playerNamesAndTypes, gameMapFiles, noOfGames, noOfTurns);
		LOGGER.debug("No of Turns is "+noOfTurns);
		for(String fileName : gameMapFiles)
		{
			LOGGER.debug("Current Map is" +gameMapFiles.indexOf(fileName) +"Filename is"+fileName);
			for(int j=0; j<noOfGames;j++)
			{
				mapParseObject = new MapParseProcessor();
				mapParseObject.mapParser(fileName,String.valueOf(playerNamesAndTypes.size()), playerNamesAndTypes,"tournament");
				MapContents mapContents =MapContents.getInstance();
				gmcountryAndNeighbours = new HashMap<Country, List<Country>>();
				gmcountryAndNeighbours = mapContents.getCountryAndNeighbors();
				Scanner scanner = new Scanner(System.in);
				endTheGame=false;
				turns=0;
				Deck deck = Deck.getInstance();
				deck.setDeckOfCards(mapContents.getCountryList());
				LOGGER.debug("Map "+gameMapFiles.indexOf(fileName)+"In game "+j);

				while(!endTheGame && turns < noOfTurns) {
					List<Player> removablePlayers = new ArrayList<>();
					for(Player player : mapContents.getPlayerList()) {
						if(player.isHasLost()) {
							removablePlayers.add(player);
						}
					}
					mapContents.getPlayerList().removeAll(removablePlayers);
					Iterator<Player> iterator = mapContents.getPlayerList().iterator();
					LOGGER.debug("After Iterator");		
					while(iterator.hasNext() &&!endTheGame &&  turns < noOfTurns) {
						turns++;
						LOGGER.debug("Turns Count is"+turns);
						Player playerInstance = new Player();
						Player p = iterator.next();
						if(!p.isHasLost()) {
							playerInstance = p.strategy.reinforcePhase(p);
							if(playerInstance.getCanAttack()) {
								playerInstance = playerInstance.strategy.attackPhase(playerInstance);
							}
							if(playerInstance.getHasWon()) {
								LOGGER.debug("Save the result and Exit the loop");
								endTheGame=true;
								results.add(playerInstance.getName());
								LOGGER.debug(results.size());
								LOGGER.debug(playerInstance.getName());



							}
							if(playerInstance.getCanFortify()) {
								playerInstance = playerInstance.strategy.forfeitPhase(playerInstance);
							}
						}

						if(turns == noOfTurns) {
							LOGGER.debug("Game result is draw");

							results.add("Draw");
							LOGGER.debug(results.size());
						}
					}

				}


			}

		}
		LOGGER.debug(Arrays.toString(results.toArray()));
		LOGGER.debug("========================================================");

		int listcount=0;
		for(String fileName : gameMapFiles)
		{
			for(int j=0; j<noOfGames;j++)
			{
				LOGGER.debug( "Map "+( gameMapFiles.indexOf(fileName)+1) +" Game "+(j+1)+" result is " +results.get(listcount));
				listcount++;
			}

		}
		LOGGER.info("========================================================");

		if(endTheGame) {
			System.exit(0);
		}



	}

}
