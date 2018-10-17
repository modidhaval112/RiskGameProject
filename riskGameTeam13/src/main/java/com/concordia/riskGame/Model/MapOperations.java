package com.concordia.riskGame.Model;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.concordia.riskGame.entity.Continent;
import com.concordia.riskGame.entity.Country;

public class MapOperations {
	
	private StringBuilder mapFileContents;
	private static final String[] ILLEGAL_CHARACTERS = { "/", "\n", "\r", "\t", "\0", "\f", "`", "?", "*", "\\", "<", ">", "|", "\"", ":" };
	
	public String writeMapFile(MapContents mapContents,String fileName) throws FileNotFoundException {
		mapFileContents = new StringBuilder();
		mapFileContents.append("[MAP]");
		mapFileContents.append("\n");
		mapFileContents.append("[Continents]\n");
		for(Continent continent : mapContents.getContinentAndItsCountries().keySet()) {
			mapFileContents.append(continent.getContinentName());
			mapFileContents.append("=");
			mapFileContents.append(continent.getNumberOfCountries());
			mapFileContents.append("\n");
		}
		mapFileContents.append("\n[Territories]\n");
		for(Map.Entry<Country, List<Country>> countryAndNeighbours : mapContents.getCountryAndNeighbors().entrySet()) {
			mapFileContents.append("\n"+countryAndNeighbours.getKey().getCountryName()+",");
			mapFileContents.append("0,0");
			
			List<Country> neighbours = countryAndNeighbours.getValue();
			mapFileContents.append("," + neighbours.get(0).getBelongsToContinent());
			for(Country country : neighbours) {
			mapFileContents.append("," + country.getCountryName());
			}
		}
		fileName = fileName + ".map";
		for (int i = 0; i < ILLEGAL_CHARACTERS.length; i++) {
			if(fileName.contains(ILLEGAL_CHARACTERS[i]))
			{
				throw new FileNotFoundException();
			}
		}
		
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            out.print(mapFileContents);
            out.close();
        }
		catch (FileNotFoundException e) {
			throw new FileNotFoundException();
        } 
		catch (Exception e) {
            e.printStackTrace();
        }
		return fileName;
	}
	
	public StringBuilder getMapFileContents() {
		return mapFileContents;
	}
}
