package riskGameModel.Map;

import java.util.HashMap;
import java.util.List;

import riskGameModel.location.Continent;
import riskGameModel.location.Country;

public class MapContents {

	private HashMap<Country, List<Country>> countryAndNeighbors = new HashMap<>();
	private HashMap<Continent, List<Country>> continentAndItsCountries = new  HashMap<>();
	/**
	 * This method is to get Hashmap of countries and its list of neighbors.
	 * @return  countryAndNeighbors Hashmap of countries and its list of neighbors.
	 */
	public HashMap<Country, List<Country>> getCountryAndNeighbors() {
		return countryAndNeighbors;
	}
	/**
	 * This method is to set Hashmap of countries and its list of neighbors.
	 * @param countryAndNeighbors Hashmap of countries and its list of neighbors.
	 */
	public void setCountryAndNeighbors(HashMap<Country, List<Country>> countryAndNeighbors) {
		this.countryAndNeighbors = countryAndNeighbors;
	}
	/**
	 * Method to get Continent and its countries
	 * @return  continentAndItsCountries Hashmap of continents and its list of countries.
	 */
	public HashMap<Continent, List<Country>> getContinentAndItsCountries() {
		return continentAndItsCountries;
	}
	/**
	 * Method to set Continent and its countries
	 * @param continentAndItsCountries Hashmap of continents and its list of countries.
	 */
	public void setContinentAndItsCountries(HashMap<Continent, List<Country>> continentAndItsCountries) {
		this.continentAndItsCountries = continentAndItsCountries;
	}
}
