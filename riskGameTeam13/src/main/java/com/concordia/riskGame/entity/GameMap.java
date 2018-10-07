package com.concordia.riskGame.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * This class contains setters and getters for different attributes of GameMap Object
 * @author d_modi
 */
public class GameMap {

	List<Country> listCountry = new ArrayList<>();
	Map<Country, List<Country>> mapCountry = new HashMap<>();
	Map<String, Integer> mapWinCount = new HashMap<>();
	Map<String, String> continentCountry = new HashMap<>();
	Set<String> setContinent = new HashSet<>();
	boolean uniqueCountryCount;
	int labelCount; 
	int countryCount;
	Set<String> setCountry = new HashSet<>();
	Map<String, List<Country>> mapContinentCountry = new HashMap<>();
	boolean visitedContinentMapFlag;
	
	/**
	 * method to get the neighbors of this particular country
	 * @return listCountry : List of Country
	 */
	public List<Country> getListCountry() {
		return listCountry;
	}

	/**
	 * method to set the list of Country
	 * @param listCountry : list of Country
	 */
	public void setListCountry(List<Country> listCountry) {
		this.listCountry = listCountry;
	}

	/**
	 * method to get the Country and list of neighboring Countries
	 * @return mapCountry : map of Country and List of Countries
	 */
	public Map<Country, List<Country>> getMapCountry() {
		return mapCountry;
	}
	
	/**
	 * method to set Country and list of neighboring Countries
	 * @param mapCountry : map of Country and List of Countries
	 */
	public void setMapCountry(Map<Country, List<Country>> mapCountry) {
		this.mapCountry = mapCountry;
	}

	/**
	 * method to get the Label Count for Map, Continent and Territories labels
	 * @return labelCount : Number of Labels present in the Map file
	 */
	public int getLabelCount() {
		return labelCount;
	}

	/**
	 * method to set the Label Count for Map, Continent and Territories labels
	 * @param labelCount : Number of Labels present in the Map file
	 */
	public void setLabelCount(int labelCount) {
		this.labelCount = labelCount;
	}

	/**
	 * method to get the Continent and Number of Territories needs to win the continent
	 * @return mapWinCount : Continent Name and Win Count for each continent
	 */
	public Map<String, Integer> getMapWinCount() {
		return mapWinCount;
	}

	/**
	 * method to set the Continent and Number of Territories needs to win the continent
	 * @param mapWinCount : Continent Name and Win Count for each continent
	 */
	public void setMapWinCount(Map<String, Integer> mapWinCount) {
		this.mapWinCount = mapWinCount;
	}

	/**
	 * method to get the Set of unique Continents
	 * @return setContinent : Set of Continents
	 */
	public Set<String> getSetContinent() {
		return setContinent;
	}

	/**
	 * method to set the Set of unique Continents
	 * @param setContinent : Set of Continents
	 */
	public void setSetContinent(Set<String> setContinent) {
		this.setContinent = setContinent;
	}

	/**
	 * method to get Country and Continent that belongs to
	 * @return continentCountry : Country and Continent
	 */
	public Map<String, String> getContinentCountry() {
		return continentCountry;
	}

	/**
	 * method to set Country and Continent that belongs to
	 * @param continentCountry : Country and Continent
	 */
	public void setContinentCountry(Map<String, String> continentCountry) {
		this.continentCountry = continentCountry;
	}

	/**
	 * method to get Unique Country Count to check if it exists in list or not
	 * @return uniqueCountryCount : true or false according the input list
	 */
	public boolean getUniqueCountryCount() {
		return uniqueCountryCount;
	}
	
	/**
	 * method to set Unique Country Count to check if it exists in list or not
	 * @param uniqueCountryCount : true or false according the input list
	 */
	public void setUniqueCountryCount(boolean uniqueCountryCount) {
		this.uniqueCountryCount = uniqueCountryCount;
	}

	/**
	 * method to get number of unique country
	 * @return countryCount : Number of Unique countries
	 */
	public int getCountryCount() {
		return countryCount;
	}

	/**
	 * method to set number of unique country
	 * @param countryCount : Number of Unique countries
	 */
	public void setCountryCount(int countryCount) {
		this.countryCount = countryCount;
	}

	/**
	 * method to get the set of unique countries
	 * @return setCountry : Set of unique countries
	 */
	public Set<String> getSetCountry() {
		return setCountry;
	}

	/**
	 * method to set the set of unique countries
	 * @param setCountry : Set of unique countries
	 */
	public void setSetCountry(Set<String> setCountry) {
		this.setCountry = setCountry;
	}
	
	/**
	 * method to get the Continent and belonging countries
	 * @return mapContinentCountry : Continent and belonging Countries
	 */
	public Map<String, List<Country>> getMapContinentCountry() {
		return mapContinentCountry;
	}

	/**
	 * method to set the Continent and belonging countries
	 * @param mapContinentCountry : Continent and belonging Countries
	 */
	public void setMapContinentCountry(Map<String, List<Country>> mapContinentCountry2) {
		this.mapContinentCountry = mapContinentCountry2;
	}

	/**
	 * method to get if continent is visited or not
	 * @return visitedContinentMapFlag : True, if continent is visited, otherwise false
	 */
	public boolean getVisitedContinentMapFlag() {
		return visitedContinentMapFlag;
	}

	/**
	 * method to set if continent is visited or not
	 * @param visitedContinentMapFlag : True, if continent is visited, otherwise false
	 */
	public void setVisitedContinentMapFlag(boolean visitedContinentMapFlag) {
		this.visitedContinentMapFlag = visitedContinentMapFlag;
	}
	
}
