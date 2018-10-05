package com.concordian.riskGame.Model;

import java.util.List;
import java.util.Map;

import com.concordia.riskGame.entity.Continent;
import com.concordia.riskGame.entity.Country;
import com.concordian.riskGame.Model.MapContents;

public class MapOperations {

	public void writeMapFile(MapContents mapContents,String fileName) {
		StringBuilder builder = new StringBuilder();
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
}
