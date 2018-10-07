package com.concordia.riskGame.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.concordia.riskGame.entity.Country;
import com.concordia.riskGame.entity.GameMap;

/**
 * This class contains all the methods to validate the map file.
 * @author d_modi
 */

public class MapValidator {
	
/*	public boolean checkNeighbourCountryCount(GameMap gameMap) {
		
		Map<Country, List<Country>> mapCountry = gameMap.getMapCountry();
		
		for(Map.Entry<Country, List<Country>> entry : mapCountry.entrySet()) {
			if(entry.getValue() == null || entry.getValue().size()==0)
			{
				return false;
			}
		}
		
		return true;
	}
*/
	
	/**
	 * method to check if all the labels are described properly
	 * @param gameMap : GameMap object
	 * @return : true if all labels are described properly, otherwise false
	 */
	public boolean checkMapLabel(GameMap gameMap) {
		
		if(gameMap.getLabelCount() == 3)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * method to check if Continent count is greater than zero or not
	 * @param gameMap : GameMap object
	 * @return : true if winCount is greater than zero, otherwise false
	 */
	public boolean checkWinCount(GameMap gameMap) {
		
		for (Entry<String, Integer> entry : gameMap.getMapWinCount().entrySet()) {
			if(entry.getValue() <= 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * method to check if Country Continent if from Continent list only
	 * @param gameMap : GameMap object
	 * @return : true if Country Continent is from Continent List, otherwise false
	 */
	public boolean checkContinent(GameMap gameMap) {
		
		for (String s : gameMap.getSetContinent()) {
			if(!gameMap.getMapWinCount().keySet().contains(s)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * method to check if all continent have at least one country
	 * @param gameMap : GameMap object
	 * @return : true if all continent has at least one country, otherwise false
	 */
	public boolean checkContinentCountry(GameMap gameMap) {
		Set<String> setContinent = gameMap.getMapWinCount().keySet();
		for (String s : gameMap.getSetContinent()) {
			if(setContinent.contains(s)) {
				setContinent.remove(s);
			}
		}
		
		if(setContinent.isEmpty())
			return true;
		else
			return false;
	}
	
	/**
	 * method to check if country is assigned to only one country
	 * @param gameMap : GameMap object
	 * @return : true if all countries are assigned to only one country, otherwise false
	 */
	public boolean checkUniqueContinentCountry(GameMap gameMap) {
		
		if(gameMap.getUniqueCountryCount())
		{
			return false;
		}
		return true;
	}
	
/*	public void isAllContinentConnected(GameMap gameMap, String parent, Map<String, Integer> visitedContinentMap, Map<String, String> continentCountry, int continentListSize) {
		
		int a = 1;
		for(Entry<String, Integer> entry : visitedContinentMap.entrySet()) {
						
			if(entry.getKey().equalsIgnoreCase(parent)) 
			{
				a = 0;
			}
		}
		
		if(a==1) {
			visitedContinentMap.put(parent, 1);
		}
		
		if(visitedContinentMap.size() == continentListSize)
		{
			gameMap.setVisitedContinentMapFlag(true);
			return;
		}
		List<Country> listCountry = gameMap.getMapContinentCountry().get(parent);
		
		for (int i = 0; i < listCountry.size(); i++) {
			List<Country> listAdjCountry = gameMap.getMapCountry().get(listCountry.get(i));
			for (int j = 0; j < listAdjCountry.size(); j++) {
				String countryName = listAdjCountry.get(j).getCountryName();
				String continentName = continentCountry.get(countryName);
				if(!continentName.equalsIgnoreCase(parent) && !visitedContinentMap.keySet().contains(continentName)) {
					isAllContinentConnected(gameMap, continentName, visitedContinentMap, continentCountry, continentListSize);
				}
			}
		}
		
	}*/
	
	/**
	 * method to check if map is connected graph or not
	 * @param parent : starting node of the graph
	 * @param mapCountry : map of Country and neighboring Countries
	 * @param visitedMap : map of Country and visited value, 1 for visited and 0 if not visited
	 * @return : true if graph is connected, otherwise false
	 */
	public Map<String, Integer> checkConnectedGraph(Country parent, Map<Country, List<Country>> mapCountry, Map<String, Integer> visitedMap) {
		List<Country> nbCountries = new ArrayList<>();
		visitedMap.put(parent.getCountryName().trim(), 1);

		for (Map.Entry<Country, List<Country>> entry : mapCountry.entrySet()) {
			if (parent.getCountryName().equalsIgnoreCase(entry.getKey().getCountryName())) {
				nbCountries = entry.getValue();
			}
		}

		if (nbCountries != null) {
			for (int i = 0; i < nbCountries.size(); i++) {
				if (!visitedMap.containsKey(nbCountries.get(i).getCountryName().trim())) {
					checkConnectedGraph(nbCountries.get(i), mapCountry, visitedMap);
				}
			}
		}

		return visitedMap;
	}
	
	/**
	 * method to check all the output returned by above described methods
	 * @param file : File Object
	 */
	public void init(File file) {
		MapValidator mapValidator = new MapValidator();
		GameMap gameMap;
		Map<String, Integer> visitedMap1 = new HashMap<>();
		Boolean validMapFlag;
		String statusMessage;
		
		
		System.out.println("In Init method");
		//String filePath = new File("resources/003_I72_Fairchild T-31.map").getAbsolutePath();
		//File file = new File(filePath);
		
		ReadMap readMap = new ReadMap();
		gameMap = readMap.readMapFile(file);
		
		Map<Country, List<Country>> mapCountry = gameMap.getMapCountry();

		for (int i = 0; i < gameMap.getListCountry().size(); i++) {
			if (visitedMap1.size() != gameMap.getListCountry().size()) {
				Map<String, Integer> visitedMap = new HashMap<>();
				visitedMap1 = mapValidator.checkConnectedGraph(gameMap.getListCountry().get(i), mapCountry, visitedMap);
			} else {
				break;
			}
		}
		
		int connectedCountries = 0;
		for (Map.Entry<String, Integer> entry : visitedMap1.entrySet()) {
			if (entry.getValue() == 1) {
				connectedCountries++;
			}
		}
		
		System.out.println("Connected Countries : " + connectedCountries);
		System.out.println("mapCountry : " + gameMap.getCountryCount());
		
		if(mapValidator.checkMapLabel(gameMap)) {
			validMapFlag = true;
		}
		else {
			validMapFlag = false;
			statusMessage = "Map is invalid as labels are not proper described";
			System.out.println("Message : " + statusMessage);
			return;
		}
		
		if(gameMap.getMapWinCount().isEmpty()) {
			validMapFlag = false;
			statusMessage = "Map should have atleast one Continent";
			System.out.println("Message : " + statusMessage);
			return;
		}
		else {
			validMapFlag = true;
			statusMessage = "Map is valid";
		}
		
		if(gameMap.getCountryCount() == 0 ) {
			validMapFlag = false;
			statusMessage = "Map should have atleast one Country";
			System.out.println("Message : " + statusMessage);
			return;
		}
		else {
			validMapFlag = true;
			statusMessage = "Map is valid";
		}
		
		if(mapValidator.checkWinCount(gameMap)) {
			validMapFlag = true;
		}
		else {
			validMapFlag = false;
			statusMessage = "Map is invalid as Continent win count should be greater than zero";
			System.out.println("Message : " + statusMessage);
			return;
		}
		
		if(mapValidator.checkContinent(gameMap)) {
			validMapFlag = true;
		}
		else {
			validMapFlag = false;
			statusMessage = "Map is invalid as Country Continent should be from only Continent List";
			System.out.println("Message : " + statusMessage);
			return;
		}
		
		if(mapValidator.checkContinentCountry(gameMap)) {
			validMapFlag = true;
		}
		else {
			validMapFlag = false;
			statusMessage = "Map is invalid as Every Continent should have atleast one country";
			System.out.println("Message : " + statusMessage);
			return;
		}
		
		if(mapValidator.checkUniqueContinentCountry(gameMap)) {
			validMapFlag = true;
		}
		else {
			validMapFlag = false;
			statusMessage = "Map is invalid as Country can assign to only one Continent";
			System.out.println("Message : " + statusMessage);
			return;
		}
		
		if(connectedCountries == gameMap.getCountryCount()) {
			validMapFlag = true;
			statusMessage = "Map is valid";
		}
		else {
			validMapFlag = false;
			statusMessage = "Map is invalid as it is not a connected graph";
			System.out.println("Message : " + statusMessage);
			return;
		}
		
/*		Map<String, Integer> visitedContinentMap = new HashMap<>();		
 		if(mapValidator.checkNeighbourCountryCount(gameMap)) {
			validMapFlag = true;
		}
		else {
			validMapFlag = false;
			statusMessage = "Map is invalid as a country should have atleast one neighbour country";
			System.out.println("Message : " + statusMessage);
			return;
		}*/
		
/*		mapValidator.isAllContinentConnected(gameMap, gameMap.getMapContinentCountry().keySet().iterator().next(), visitedContinentMap, gameMap.getContinentCountry(), setContinentValues.size());
		if(gameMap.getVisitedContinentMapFlag()) {
			validMapFlag = true;
		}
		else {
			validMapFlag = false;
			statusMessage = "Map is invalid as Continents are not connected";
			System.out.println("Message : " + statusMessage);
			return;
		}*/
		
		
		
		
		Set<String> setContinentValues = new HashSet<>();
		for (Entry<String, String> entry : gameMap.getContinentCountry().entrySet()) {
			setContinentValues.add(entry.getValue());
		}
		
		
		System.out.println("Message : " + statusMessage);
		System.out.println("validMapFlag : " + validMapFlag);
		
	}
	
}
