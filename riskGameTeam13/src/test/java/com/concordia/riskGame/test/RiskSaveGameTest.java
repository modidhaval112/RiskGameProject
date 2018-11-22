package com.concordia.riskGame.test;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.Assert.assertTrue;


import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.Player.Player;
import com.concordia.riskGame.model.save.RiskSaveGame;

public class RiskSaveGameTest {
	
	Player p1, p2;
	Country c1,c2,c3,c4;
	List<Country> listCountry, listCountry1;
	MapContents mapContents;
	
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
	 */
	@Test
	public void testFileCreation() {
		RiskSaveGame riskSaveGame = new RiskSaveGame();
		riskSaveGame.saveGame(mapContents);
		
		boolean flag = false;
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHH:mm:ss");
		Date date = new Date();
		String expectedFileName = sdf.format(date);
		expectedFileName = expectedFileName.replaceAll(":", "");
		
		File folder = new File("C:\\SaveGame\\");
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
			    System.out.println("File " + listOfFiles[i].getName());
			}
			if(listOfFiles[i].getName().equalsIgnoreCase(expectedFileName)) {
				flag = true;
				break;
			}
		}
	    assertTrue(flag);
	}

}
