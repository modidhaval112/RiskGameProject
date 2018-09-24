package riskGameModel.location;

import java.util.List;

/**
 * Country Class go get and set all properties of a country.
 * @author sande
 *
 */


public class Country {

	private String countryName;
	private List<Country> adjacentCountries;
	private Continent belongsToContinent;
	private int startPixel;
	private int endPixel;
	
	/**
	 * method to get the name of the country
	 * @return countryName :  Name of the country
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * method to set the name of the country
	 * @param countryName :  Name of the country
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * method to get the neighbors of this particular country
	 * @return adjacentCountries : List of neighboring countries
	 */
	public List<Country> getAdjacentCountries() {
		return adjacentCountries;
	}
	/**
	 * method to set the neighbors of this particular country
	 * @param adjacentCountries : List of neighboring countries
	 */
	public void setAdjacentCountries(List<Country> adjacentCountries) {
		this.adjacentCountries = adjacentCountries;
	}
	/**
	 * method to get the continent to which the country belongs
	 * @return belongsToContinent : Name of the continent to which the country belongs
	 */
	public Continent getBelongsToContinent() {
		return belongsToContinent;
	}
	/**
	 * method to set the continent to which the country belongs
	 * @param belongsToContinent : Name of the continent to which the country belongs
	 */
	public void setBelongsToContinent(Continent belongsToContinent) {
		this.belongsToContinent = belongsToContinent;
	}
	/**
	 * method to get the startPixel
	 * @return startPixel : Start Coordinate of the country
	 */
	public int getStartPixel() {
		return startPixel;
	}
	/**
	 * method to set the startPixel
	 * @param startPixel : Start Coordinate of the country
	 */
	public void setStartPixel(int startPixel) {
		this.startPixel = startPixel;
	}
	/**
	 * method to get the startPixel
	 * @return endPixel : End coordinate of the country
	 */
	public int getEndPixel() {
		return endPixel;
	}
	/**
	 * method to set the startPixel
	 * @param endPixel : End coordinate of the country
	 */
	public void setEndPixel(int endPixel) {
		this.endPixel = endPixel;
	}
}
