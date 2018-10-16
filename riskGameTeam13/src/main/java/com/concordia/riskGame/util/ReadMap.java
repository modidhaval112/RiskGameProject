package com.concordia.riskGame.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.concordia.riskGame.entity.Country;
import com.concordia.riskGame.entity.GameMap;

/**
 * This class reads the .map file and Returns the GameMap object
 * @author d_modi
 */

public class ReadMap {

	/**
	 * method to read the .map file and along with that setting all the 
	 * attributes of GameMap Object
	 * @param file File Object
	 * @return gameMap GameMap Object
	 */
	public GameMap readMapFile(File file) {

		String line;
		int labelCount = 0;
		List<Country> listCountry = new ArrayList<>();
		Set<String> setContinent = new HashSet<>();
		Set<String> setCountry = new HashSet<>();
		Map<String, String> continentCountry = new HashMap<>();
		Map<String, List<Country>> mapContinentCountry = new HashMap<>();
		Map<Country, List<Country>> mapCountry = new HashMap<>();
		Map<String, Integer> mapWinCount = new HashMap<>();
		GameMap gameMap = new GameMap();

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {

				if (line.contains("[Map]") && labelCount == 0) {
					labelCount = 1;
				} else if (line.contains("[Continents]") && labelCount == 1) {
					labelCount = 2;
					line = br.readLine();

				} else if (line.contains("[Territories]") && labelCount == 2) {
					labelCount = 3;
					line = br.readLine();
				}

				if (line != null && labelCount == 2 && line.length() > 0 && !line.isEmpty() && !line.trim().equals("")
						&& !line.trim().equals("\n")) {

					String[] continentdetails = line.split("=");
					if (continentdetails.length <= 1) {
						mapWinCount.put(continentdetails[0], 0);
					} else {
						mapWinCount.put(continentdetails[0], Integer.parseInt(continentdetails[1]));
					}
					gameMap.setMapWinCount(mapWinCount);
				}

				if (line != null && labelCount == 3 && line.length() > 0 && !line.isEmpty() && !line.trim().equals("")
						&& !line.trim().equals("\n")) {

					String[] countryDetails = line.split(",");
					Country country = new Country(countryDetails[0].trim(), Integer.parseInt(countryDetails[1].trim()),
							Integer.parseInt(countryDetails[2].trim()), countryDetails[3].trim());
					if(!setCountry.contains(countryDetails[0].trim()))
					{
						listCountry.add(country);
					}
					setContinent.add(countryDetails[3].trim());

					if (continentCountry.keySet().contains(countryDetails[0].trim()) && !continentCountry
							.get(countryDetails[0].trim()).equalsIgnoreCase((countryDetails[3].trim()))) {
						gameMap.setUniqueCountryCount(true);
					}

					continentCountry.put(countryDetails[0].trim(), countryDetails[3].trim());

					List<Country> listAdjCountries = new ArrayList<>();
					for (int i = 4; i < countryDetails.length; i++) {
						listAdjCountries.add(new Country(countryDetails[i], 0, 0, null));
					}

					setCountry.add(countryDetails[0].trim());
					mapCountry.put(country, listAdjCountries);

					if (mapContinentCountry.keySet().contains(countryDetails[3].trim())) {
						mapContinentCountry.get(countryDetails[3].trim()).add(country);
					} else {
						List<Country> tempCountryList = new ArrayList<>();
						tempCountryList.add(country);
						mapContinentCountry.put(countryDetails[3].trim(), tempCountryList);
					}

				}
			}
			gameMap.setMapContinentCountry(mapContinentCountry);
			gameMap.setContinentCountry(continentCountry);
			gameMap.setLabelCount(labelCount);
			System.out.println("Label Count  : " + labelCount);
			if (!setContinent.isEmpty()) {
				gameMap.setSetContinent(setContinent);
			}
			gameMap.setCountryCount(setCountry.size());
			if (!listCountry.isEmpty()) {
				gameMap.setListCountry(listCountry);
			}
			if (!mapCountry.isEmpty()) {
				gameMap.setMapCountry(mapCountry);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return gameMap;
	}

}
