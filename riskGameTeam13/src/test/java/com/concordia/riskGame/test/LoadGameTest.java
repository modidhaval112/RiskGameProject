package com.concordia.riskGame.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.concordia.riskGame.View.LoadGame;
import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.Player.CheaterPlayer;
import com.concordia.riskGame.model.Player.Player;
import com.concordia.riskGame.model.save.RiskSaveGame;

public class LoadGameTest {
	
	private MapContents mapContents;
	private RiskSaveGame riskSaveGame;
	private String expectedFileName;
	private String expectedFilePath;
	private Player p1, p2;
	private Country c1,c2,c3,c4;
	private List<Country> listCountry, listCountry1;
	private List<Player> listPlayer;
	
    @Rule
    public final TextFromStandardInputStream systemInMock
      = TextFromStandardInputStream.emptyStandardInputStream();
	
	/**
	 * before method for initializing objects
	 */
	@Before
	public void before () {
		
		p1 = new Player("p1");
		p2 = new Player("p2");
		c1 = new Country("c1");
		c2 = new Country("c2");
		c3 = new Country("c3");
		c4 = new Country("c4");
		listCountry = new ArrayList<>();
		listCountry1 = new ArrayList<>();
		mapContents = MapContents.getInstance();
		listPlayer = new ArrayList<>();
		
		p1.setTotalArmies(40);
		p2.setTotalArmies(40);
		c1.setArmies(10);
		c2.setArmies(10);
		c3.setArmies(10);
		c4.setArmies(10);
		
		listCountry.add(c2);
		listCountry.add(c3);
		c1.setNeighbouringCountries(listCountry);
		mapContents.getCountryAndNeighbors().put(c1, listCountry);
		
		listCountry = new ArrayList<>();
		
		listCountry.add(c3);
		listCountry.add(c4);
		c2.setNeighbouringCountries(listCountry);
		mapContents.getCountryAndNeighbors().put(c2, listCountry);
		
		listCountry = new ArrayList<>();
		
		listCountry.add(c4);
		listCountry.add(c1);
		c3.setNeighbouringCountries(listCountry);
		mapContents.getCountryAndNeighbors().put(c3, listCountry);
		
		listCountry = new ArrayList<>();
		
		listCountry.add(c1);
		listCountry.add(c2);
		c4.setNeighbouringCountries(listCountry);
		mapContents.getCountryAndNeighbors().put(c4, listCountry);
		
		listCountry = new ArrayList<>();
		
		listCountry.add(c1);
		listCountry.add(c2);
		p1.setAssignedCountries(listCountry);
						
		listCountry1.add(c3);
		listCountry1.add(c4);
		p2.setAssignedCountries(listCountry1);
		
	}
	
	/**
	 * Test method to check if saved game file has been created or not 
	 * @throws IOException 
	 */
	@Test
	public void testreadSavedMapContent() throws IOException {
		boolean flag = false;
		try {
			
			CheaterPlayer cp = new CheaterPlayer();
			Player p11 = cp.reinforcePhase(p1);
			Player p22 = cp.reinforcePhase(p2);
			listPlayer.add(p11);
			listPlayer.add(p22);
			mapContents.setPlayerList(listPlayer);
			
			riskSaveGame = new RiskSaveGame();
			riskSaveGame.saveGame(mapContents);
			DateFormat sdf = new SimpleDateFormat("yyyyMMddHH:mm:ss");
			Date date = new Date();
			expectedFileName = sdf.format(date);
			expectedFileName = expectedFileName.replaceAll(":", "");
			expectedFilePath = "C:\\SaveGame\\"+expectedFileName;
			
			LoadGame lg = new LoadGame();
			MapContents mapContents = lg.readSavedMapContent(expectedFilePath);
			
			List<Player> listPlayer1 = mapContents.getPlayerList();
			
			if(listPlayer1.get(0).getAssignedCountries().get(0).getArmies() == 20 && listPlayer1.get(0).getAssignedCountries().get(1).getArmies() == 20
					&& listPlayer1.get(1).getAssignedCountries().get(0).getArmies() == 20 && listPlayer1.get(1).getAssignedCountries().get(1).getArmies() == 20) {
				flag = true;
			}
			
			assertTrue(flag);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
