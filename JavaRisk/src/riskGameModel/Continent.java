package riskGameModel;

import java.util.List;

/**
 * Continent Class go get and set all properties of a continent.
 * @author sande
 *
 */
public class Continent {
	private String continentName;
	private List<Country> countries;
	
	/**
	 * method to get the countries which belong to this continent
	 * @return String
	 */
	public List<Country> getCountries() {
		return countries;
	}
	/**
	 * method to set the countries which belong to this continent
	 * @param countries
	 */
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	/**
	 * method to get the name of the continent
	 * @return String
	 */
	public String getContinentName() {
		return continentName;
	}
	/**
	 * method to set the name of the continent
	 * @param continentName
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
}
