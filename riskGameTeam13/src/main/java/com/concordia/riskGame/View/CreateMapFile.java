package com.concordia.riskGame.View;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.concordia.riskGame.entity.Continent;
import com.concordia.riskGame.entity.Country;
import com.concordia.riskGame.model.MapContents;
import com.concordia.riskGame.model.MapOperations;

/**
 * This class is for the user driven creation of the Map File
 * @author sande
 *
 */
public class CreateMapFile {

	private static final int List = 0;
	private int numberOfContinents;
	private int numberOfCountries;	
	private List<String> nameOfContinents = new ArrayList<>();
	private List<String> nameOfCountries = new ArrayList<>();
	private HashMap<Continent, List<Country>> continentsWithItsCountries= new HashMap<>();
	private HashMap<Country, List<Country>> countriesWithItsNeighbours = new HashMap<>();
	private List<Country> countries = new ArrayList<>() ;
	
	/**
	 * In this method we start the creation of the Map by asking the inputs from the user.
	 * 
	 */
	public CreateMapFile() {
		System.out.println("Enter the number of Continents");
		Scanner scanner = new Scanner(System.in);
		numberOfContinents = scanner.nextInt();
		try {
		for(int i=1;i<=numberOfContinents;i++) {
			System.out.println("Enter the name of the Continent"+ i);
			nameOfContinents.add(scanner.next());
		}
		for(int i=0;i<numberOfContinents;i++) {
			System.out.println("Enter the number of Countries in the Continent : " + nameOfContinents.get(i));
			int numberOfCountries = scanner.nextInt(); // add number format exception
			System.out.println("Enter the countries that belong to the Continent : "+nameOfContinents.get(i));
			for(int j=0;j<numberOfCountries;j++) {
				 String countryName = scanner.next();
				 nameOfCountries.add(countryName);
				 Country country = new Country(countryName, 0, 0, nameOfContinents.get(i));
				 countries.add(country);
			}
			scanner.nextLine();
			Continent continent = new Continent(nameOfContinents.get(i));
			continentsWithItsCountries.put(continent, countries);
		}
		for(int i=0;i<countries.size();i++) {
			System.out.println("Enter the neighbouring countries to the country\""  + countries.get(i).getCountryName() + "\"in \",\"(comma) seperated values " );
			String[] neighbouringCountries = scanner.nextLine().split(",");
			List<Country> neighbourCountries = new ArrayList<>();
			boolean errorWhileReadingCountry = false;
			for(String neighbour : neighbouringCountries) {
				if(neighbour.equals(countries.get(i).getCountryName())) {
					errorWhileReadingCountry = true;
					neighbourCountries=reenterCountries(countries.get(i).getCountryName(),scanner,countries.get(i).getBelongsToContinent());
				}else if(!nameOfCountries.contains(neighbour)) {
					errorWhileReadingCountry = true;
					neighbourCountries=reenterCountries(countries.get(i).getCountryName(),scanner,countries.get(i).getBelongsToContinent());
				}else {
					if(!errorWhileReadingCountry) {
					Country neighbouringCountry= new Country(neighbour, 0, 0, countries.get(i).getBelongsToContinent());
					neighbourCountries.add(neighbouringCountry);
					}
				}
			}
			countriesWithItsNeighbours.put(countries.get(i), neighbourCountries);
		}
		}catch (Exception InputMismatchException) {
			System.out.println("Please enter a valid input ");
			//main(null);
		} 
			
		
		
		System.out.println("Please Enter the Name of The Map that you want to create");
       // Scanner scanMapFileName = new Scanner(System.in);
        String fileName = scanner.nextLine();
        MapContents mapContents = new MapContents();
        mapContents.setContinentAndItsCountries(continentsWithItsCountries);
        mapContents.setCountryAndNeighbors(countriesWithItsNeighbours);
		MapOperations mapOperations = new MapOperations();
		try {
			mapOperations.writeMapFile(mapContents, fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method ask user countries one more time after giving the wrong information
	 * @param countryName name of the countries
	 * @param scanner Scanner object
	 * @param continent Continent name
	 * @return neighbourCountries list of neighbor countries
	 */
	private List<Country> reenterCountries(String countryName,Scanner scanner,String continent) {
		System.out.println("A country cannot be neighbour to itself. Please enter the neighbouring countries again ");
		String[] neighbouringCountries = scanner.nextLine().split(",");
		List<Country> neighbourCountries = new ArrayList<>();
		boolean errorWhileReadingCountry = false;
		for(String neighbour : neighbouringCountries) {
			if(neighbour.equals(countryName)) {
				reenterCountries(countryName,scanner,continent);
			}else if(!nameOfCountries.contains(neighbour)) {
				errorWhileReadingCountry = true;
				neighbourCountries=reenterCountries(countryName,scanner,continent);
			}else {
				Country neighbouringCountry= new Country(neighbour, 0, 0, continent);
				neighbourCountries.add(neighbouringCountry);
			}
		}	
		return neighbourCountries;
	}
}	

