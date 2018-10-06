package com.concordian.riskGame.Model;

import java.util.List;
import java.util.Map;

import com.concordia.riskGame.entity.Continent;
import com.concordia.riskGame.entity.Country;
import com.concordian.riskGame.Model.MapContents;

public class MapOperations {
	
	private StringBuilder builder;
	
	public void writeMapFile(MapContents mapContents,String fileName) {
		builder = new StringBuilder();
		builder.append("[MAP]");
		builder.append("\n");
		builder.append("[Continents]\n");
		for(Continent continent : mapContents.getContinentAndItsCountries().keySet()) {
			builder.append(continent.getContinentName());
			builder.append("=");
			builder.append(continent.getNumberOfCountries());
		}
		builder.append("\n[Territories]\n");
		for(Map.Entry<Country, List<Country>> countryAndNeighbours : mapContents.getCountryAndNeighbors().entrySet()) {
			builder.append("\n"+countryAndNeighbours.getKey().getCountryName()+",");
			builder.append("0,0");
			List<Country> neighbours = countryAndNeighbours.getValue();
			for(Country country : neighbours) {
			builder.append(","+country.getCountryName());
			}
		}
		
		System.out.println(builder);
	}
/**
 * This method returns the builder 
 * this is being used in CreateMapFileTest to test if the map is built as per the format
 * @return
 */
	public StringBuilder getBuilder() {
		return builder;
		
	}
}
