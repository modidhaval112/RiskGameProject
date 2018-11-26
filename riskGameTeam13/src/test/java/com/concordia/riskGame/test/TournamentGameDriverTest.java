package com.concordia.riskGame.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.concordia.riskGame.control.TournamentGameDriver;
import com.concordia.riskGame.model.Player.Player;

public class TournamentGameDriverTest {

	private HashMap<String, String> playerNamesAndTypes;
	private List<String> gameMapFiles;
	private Player p1, p2;

	/**
	 * before method for initializing objects
	 */
	@Before
	public void before() {

		try {
			playerNamesAndTypes = new HashMap<>();
			gameMapFiles = new ArrayList<>();

			playerNamesAndTypes.put("Player1", "Cheater");
			playerNamesAndTypes.put("Player2", "Cheater");
			gameMapFiles.add("src/main/resources/test.map");
			gameMapFiles.add("src/main/resources/test1.map");

			TournamentGameDriver driver = new TournamentGameDriver();
			driver.gamePhase(playerNamesAndTypes, gameMapFiles, 2, 10);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test method to check if saved game file has been created or not
	 * 
	 * @throws IOException
	 */
	@Test
	public void testReadSavedMapContent() throws IOException {
	}
}
