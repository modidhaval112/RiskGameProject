package com.concordia.riskGame.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public List<Country> getListCountry() {
		return listCountry;
	}

	public void setListCountry(List<Country> listCountry) {
		this.listCountry = listCountry;
	}

	public Map<Country, List<Country>> getMapCountry() {
		return mapCountry;
	}

	public void setMapCountry(Map<Country, List<Country>> mapCountry) {
		this.mapCountry = mapCountry;
	}

	public int getLabelCount() {
		return labelCount;
	}

	public void setLabelCount(int labelCount) {
		this.labelCount = labelCount;
	}

	public Map<String, Integer> getMapWinCount() {
		return mapWinCount;
	}

	public void setMapWinCount(Map<String, Integer> mapWinCount) {
		this.mapWinCount = mapWinCount;
	}

	public Set<String> getSetContinent() {
		return setContinent;
	}

	public void setSetContinent(Set<String> setContinent) {
		this.setContinent = setContinent;
	}

	public Map<String, String> getContinentCountry() {
		return continentCountry;
	}

	public void setContinentCountry(Map<String, String> continentCountry) {
		this.continentCountry = continentCountry;
	}

	public boolean getUniqueCountryCount() {
		return uniqueCountryCount;
	}

	public void setUniqueCountryCount(boolean uniqueCountryCount) {
		this.uniqueCountryCount = uniqueCountryCount;
	}

	public int getCountryCount() {
		return countryCount;
	}

	public void setCountryCount(int countryCount) {
		this.countryCount = countryCount;
	}

	public Set<String> getSetCountry() {
		return setCountry;
	}

	public void setSetCountry(Set<String> setCountry) {
		this.setCountry = setCountry;
	}

	public Map<String, List<Country>> getMapContinentCountry() {
		return mapContinentCountry;
	}

	public void setMapContinentCountry(Map<String, List<Country>> mapContinentCountry2) {
		this.mapContinentCountry = mapContinentCountry2;
	}

	public boolean getVisitedContinentMapFlag() {
		return visitedContinentMapFlag;
	}

	public void setVisitedContinentMapFlag(boolean visitedContinentMapFlag) {
		this.visitedContinentMapFlag = visitedContinentMapFlag;
	}
	
}
