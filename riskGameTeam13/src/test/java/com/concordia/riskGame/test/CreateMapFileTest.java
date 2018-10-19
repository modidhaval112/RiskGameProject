package com.concordia.riskGame.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.concordia.riskGame.View.CreateMapFile;
import com.concordia.riskGame.entity.Continent;
import com.concordia.riskGame.entity.Country;
import com.concordia.riskGame.model.MapContents;
import com.concordia.riskGame.model.MapOperations;
/**
 * This Class contains the test method to check if the creation of the map is being done in the Conquest format
 * @author Dheeraj As
 *
 */
public class CreateMapFileTest {
	
	private List<String> nameOfContinents = new ArrayList<>();
	private List<String> nameOfCountries = new ArrayList<>();
	private HashMap<Continent, List<Country>> continentsWithItsCountries= new HashMap<>();
	private HashMap<Country, List<Country>> countriesWithItsNeighbours = new HashMap<>();
	private List<Country> countries = new ArrayList<>() ;
	List<Country> neighbourCountries1 = new ArrayList<>();
	List<Country> neighbourCountries2 = new ArrayList<>();
	StringBuilder testBuilder= new StringBuilder();
	Boolean mapCreationValid;

	
	/**
	 * This method sets up the context for creating the map file
	 */
	@Before
	public void setUpContextForMapFile() {
		
		nameOfContinents.add("con1");
		nameOfContinents.add("con2");
		
		String countryName = "c1";
		nameOfCountries.add(countryName);
		Country country = new Country(countryName, 0, 0, "con1");
		countries.add(country);
		
		Continent continent = new Continent(nameOfContinents.get(0));
		continent.setNumberOfCountries(1);
		continentsWithItsCountries.put(continent, countries);
		
		String countryName2 = "c2";
		nameOfCountries.add(countryName2);
		Country country2 = new Country(countryName2, 0, 0, "con2");
		countries.add(country2);
		
		Continent continent2 = new Continent(nameOfContinents.get(1));
		continent2.setNumberOfCountries(1);
		continentsWithItsCountries.put(continent2, countries);
		
		Country neighbouringCountry= new Country("c2", 0, 0, "con1");
		neighbourCountries1.add(neighbouringCountry);
		
		Country neighbouringCountry2= new Country("c1", 0, 0, "con2");
		neighbourCountries2.add(neighbouringCountry2);
		
		countriesWithItsNeighbours.put(countries.get(0), neighbourCountries1);
		countriesWithItsNeighbours.put(countries.get(1), neighbourCountries2);
		
		
		
				
	}
	
	/**
	 * This test method checks for positive the created map format
	 * 
	 */
	@Test
	public void testWriteMapFilePositive() {
		
			//MapOperations mapOperationObj= new MapOperations();
		try {
		    MapContents mapContents = new MapContents();
		    
		    Set<Continent> continentList = continentsWithItsCountries.keySet();
			for (Continent s : continentList) {
			    s.setNumberOfCountries(1);
			}
		    
	        mapContents.setContinentAndItsCountries(continentsWithItsCountries);
	        mapContents.setCountryAndNeighbors(countriesWithItsNeighbours);
	        
	        MapOperations mapOperations = new MapOperations();
			mapOperations.writeMapFile(mapContents, null);
			
			
			
			testBuilder = mapOperations.getMapFileContents();
						
			if(testBuilder.toString().contains("[Map]") && testBuilder.toString().contains("[Continents]") && testBuilder.toString().contains("con1=1") && testBuilder.toString().contains("con2=1") && 
				testBuilder.toString().contains("[Territories]")  && testBuilder.toString().contains("c1,0,0,con1,c2") && testBuilder.toString().contains("c2,0,0,con2,c1")) {
				mapCreationValid = true;
			}
			
			assertTrue(mapCreationValid);
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * This test method checks for negative scenario for the created map format
	 * 
	 */
	@Test
	public void testWriteMapFileNegative() {
		
			//MapOperations mapOperationObj= new MapOperations();
		try {
		    MapContents mapContents = new MapContents();
	        mapContents.setContinentAndItsCountries(continentsWithItsCountries);
	        mapContents.setCountryAndNeighbors(countriesWithItsNeighbours);
	        
	        MapOperations mapOperations = new MapOperations();
			mapOperations.writeMapFile(mapContents, null);
			
			testBuilder = mapOperations.getMapFileContents();
			
			String removeString = "[MAP]";
			int i = testBuilder.indexOf(removeString);
		    if (i != -1) {
		        testBuilder.delete(i, i + removeString.length());
		    }
			
			mapCreationValid = false;		
			if(testBuilder.toString().contains("[MAP]") && testBuilder.toString().contains("[Continents]") && testBuilder.toString().contains("con1=1") && testBuilder.toString().contains("con2=1") && 
				testBuilder.toString().contains("[Territories]")  && testBuilder.toString().contains("c1,0,0,con1,c2") && testBuilder.toString().contains("c2,0,0,con2,c1")) {
				mapCreationValid = true;
			}
			
			assertFalse(mapCreationValid);
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * This test method checks for positive scenario for .map file format
	 * 
	 */
	@Test
	public void testWriteMapFileExtentionPositive() {
		
			//MapOperations mapOperationObj= new MapOperations();
		try {
		    MapContents mapContents = new MapContents();
	        mapContents.setContinentAndItsCountries(continentsWithItsCountries);
	        mapContents.setCountryAndNeighbors(countriesWithItsNeighbours);
	        
	        MapOperations mapOperations = new MapOperations();
			String fileName = mapOperations.writeMapFile(mapContents, "Africa");
			
			//System.out.println("File name  : " + fileName);
			String[] splitArray = fileName.split("\\.");

			if(splitArray[splitArray.length-1].equalsIgnoreCase("map")) {
				mapCreationValid = true;
			}
			
			assertTrue(mapCreationValid);
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * This test method checks for negative scenario for .map file format
	 * 
	 */
	@Test
	public void testWriteMapFileExtentionNegative() {
		
			//MapOperations mapOperationObj= new MapOperations();
		try {
		    MapContents mapContents = new MapContents();
	        mapContents.setContinentAndItsCountries(continentsWithItsCountries);
	        mapContents.setCountryAndNeighbors(countriesWithItsNeighbours);
	        
	        MapOperations mapOperations = new MapOperations();
			String fileName = mapOperations.writeMapFile(mapContents, "Africa");
			
			//System.out.println("File name  : " + fileName);
			fileName = fileName.concat("1");
			String[] splitArray = fileName.split("\\.");
			mapCreationValid = false;
			if(splitArray[splitArray.length-1].equalsIgnoreCase("map")) {
				mapCreationValid = true;
			}
			assertFalse(mapCreationValid);
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * This test method checks for negative scenario for file name format
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test(expected=FileNotFoundException.class)
	public void testWriteMapFileExtentionNegative1() throws FileNotFoundException {
		
			//MapOperations mapOperationObj= new MapOperations();
		    MapContents mapContents = new MapContents();
	        mapContents.setContinentAndItsCountries(continentsWithItsCountries);
	        mapContents.setCountryAndNeighbors(countriesWithItsNeighbours);
	        MapOperations mapOperations = new MapOperations();
			mapOperations.writeMapFile(mapContents, "Africa?");
	}
}