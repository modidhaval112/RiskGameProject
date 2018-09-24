package riskGameModel.Map;

import java.util.HashMap;
import java.util.List;

import riskGameModel.location.Continent;
import riskGameModel.location.Country;

public class MapContents {

	private HashMap<Country, List<Country>> countryAndNeighbors = new HashMap<>();
	private HashMap<Continent, List<Country>> continentAndItsCountries = new  HashMap<>();
}
