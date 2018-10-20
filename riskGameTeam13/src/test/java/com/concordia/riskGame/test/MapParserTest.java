package com.concordia.riskGame.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import com.concordia.riskGame.model.Continent.Continent;
import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.Map.MapParseProcessor;


/**
 * Test to check if reading the map is functioning properly
 * 
 */
public class MapParserTest {

	private String filePath;
	private MapParseProcessor mapParserObject;
	private MapContents mapContents;
	String playerCount;
	
	/**
	 * Before method to set initialize objects
	 * @throws Exception
	 */
	@Before
	public void before() throws Exception {
	    filePath="src/main/resources/Africa.map";
		mapParserObject = new MapParseProcessor();
		playerCount = "5";
	}
	
	/**
	 * Test method for mapParser method
	 */
	@Test
	public void mapParsertest() {
		mapContents = mapParserObject.mapParser(filePath, playerCount);
		Set<Continent> setOfContinents= mapContents.getContinentAndItsCountries().keySet();
		Set<Country> setOfCountries= mapContents.getCountryAndNeighbors().keySet();
		List<Country> listOfNeighborCountries= new ArrayList<>();
		Continent continent = new Continent("Northern Africa");
		List<Country> listOfCountry = new ArrayList<>();
		
		for(Continent c : mapContents.getContinentAndItsCountries().keySet()) {
			if(c.getContinentName().equalsIgnoreCase("Northern Africa")) {
				listOfCountry = mapContents.getContinentAndItsCountries().get(c);
				System.out.println("continent : " + continent.getContinentName());
			}
		}
		
		for(Country c : mapContents.getCountryAndNeighbors().keySet()) {
			if(c.getCountryName().equalsIgnoreCase("Mauritania")) {
				listOfNeighborCountries = mapContents.getCountryAndNeighbors().get(c);
				System.out.println("c.getCountryName() : " + c.getCountryName());
			}
		}
	
		assertEquals(7,setOfContinents.size());
		assertEquals(41,setOfCountries.size());
		assertEquals(5, listOfCountry.size());
		assertEquals(4, listOfNeighborCountries.size());
	}
}
