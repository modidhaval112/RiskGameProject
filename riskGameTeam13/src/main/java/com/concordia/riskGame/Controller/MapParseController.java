package com.concordia.riskGame.Controller;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

import com.concordia.riskGame.Model.MapContents;
import com.concordia.riskGame.View.GameDriver;
import com.concordia.riskGame.entity.Continent;
import com.concordia.riskGame.entity.Country;
import com.concordia.riskGame.entity.Player;
import com.concordia.riskGame.exception.InvalidMapFileException;
import com.concordia.riskGame.util.MapValidator;
import com.concordia.riskGame.util.RandomAssignment;
import com.concordia.riskGame.util.ReadConfiguration;



/**
 * This Class has the implementation of reading the .map file and  setting countryAndNeighbors
 * and continentAndItsCountries fields in the MapContents class
 * 
 * @author Dheeraj As  - Team 13
 * @author Darwin Anirudh G  - Team 13
 */
public class MapParseController {

	private File file;
	private String currentLine;
	private String filePath;
	private File fileObject;
	private BufferedReader bufferReaderForFile;
	private MapContents mapContentsObj;
	private String mapAuthorName;
	private int numberOfPlayers;
	private String[] splitUtllityString;
	private List<Continent> contitentList;
	private List<Country> countryList;
	private List<Country> adjCountry;
	private Continent continentObejct;
	private Country countryObject;
	private MapContents mapContentObject;
	private HashMap<Country, List<Country>> countryAndNeighbors;
	private HashMap<Continent, List<Country>> continentAndItsCountries;
	private GameDriver gameDriverObject;
	private Player playerObject;
	private ReadConfiguration readConfigurationObject;
	private List<Player> playerList;
	private int initialArmies;
	
	
	
	
	

	public MapContents mapParser(String filePath,String numberCombo) {
		try {

			fileObject = new File(filePath);

			numberOfPlayers = Integer.parseInt(numberCombo);
			
			
			System.out.println("File Path " + filePath);

			bufferReaderForFile = new BufferedReader(new FileReader(fileObject));
			
			System.out.println("##### The combo box selected number is ##### :"+Integer.parseInt(numberCombo));

			MapValidator mapValidator = new MapValidator();
			mapValidator.init(fileObject);
			if(!mapValidator.getValidMapFlag()) {
				throw new InvalidMapFileException("Invalid Map File");
			}
			
			readMapElements(bufferReaderForFile);

				
			countryAndNeighboursMap();
			
			contitentAndCountriesMap();
			
			mapContentObject = new MapContents();
			
			mapContentObject.setContinentAndItsCountries(continentAndItsCountries);
			mapContentObject.setCountryAndNeighbors(countryAndNeighbors);
			
			RandomAssignment randonAssignment = new RandomAssignment();
			playerObject = randonAssignment.randonAssignmentMethod(Integer.parseInt(numberCombo), countryList);
			
			
				if(Integer.parseInt(numberCombo) == 3)
				initialArmies = 35; 
				
				if(Integer.parseInt(numberCombo) == 4)
				initialArmies = 30; 
					
				if(Integer.parseInt(numberCombo) == 5)
				initialArmies = 25; 
				playerList = new ArrayList();
			
			for ( Player key : playerObject.getPlayerAssign().keySet() ) {
			    System.out.println("Player Name : " +  key.getName() );
			    System.out.println("     Assigned Of Countries  :"+key.getAssignedCountries().size());
			   
			    initialArmies = initialArmies;
			    key.setTotalArmies(initialArmies);
			    System.out.println("     Assigned Armies            :"+initialArmies);
			    for (int i = 0; i < key.getAssignedCountries().size(); i++) {
					System.out.println("     Assigned Of Countries : " + playerObject.getPlayerAssign().get(key).get(i).getCountryName());
					
				}
			    
			    playerList.add(key);
			    System.out.println("");
			}

			
			/*displayPlayerDetails();*/
			
			/*playerNameAssignment();*/
			
			
			
				gameDriverObject = new GameDriver();
				gameDriverObject.gamePhase(playerList,countryAndNeighbors);
	
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return mapContentObject;

	}

	private void readMapElements(BufferedReader bufferReader) {
		try {
			contitentList = new ArrayList<>();
			countryList = new ArrayList<>();
			countryAndNeighbors = new HashMap<>();

			while ((currentLine = bufferReader.readLine()) != null) {

				if (currentLine.contains("[Map]")) {
					while ((currentLine = bufferReader.readLine()) != null && !currentLine.contains("[")) {
						if (currentLine.contains("author")) {
							splitUtllityString = currentLine.split("=");
							System.out.println("splitUtllityString :" + splitUtllityString[1]);
							mapAuthorName = splitUtllityString[1];
						}

					}
					bufferReader.mark(0);
				}

				bufferReader.reset();

				if (currentLine.contains("[Continents]")) {
					while ((currentLine = bufferReader.readLine()) != null && !currentLine.contains("[")) {
						System.out.println(currentLine);
						if (!currentLine.isEmpty()) {
							String[] continentValues = currentLine.split("=");
							Continent continentObject = new Continent(continentValues[0],
									Integer.parseInt(continentValues[1]));
							contitentList.add(continentObject);
						}
					}
					bufferReader.mark(0);
				}

				bufferReader.reset();

				if (currentLine.contains("[Territories]")) {

					while ((currentLine = bufferReader.readLine()) != null) {
						if (!currentLine.isEmpty()) {
							System.out.println(currentLine);
							String[] territoryValues = currentLine.split(",", 2);
							String[] adjCountries = territoryValues[1].split(",", 2);
							String[] adjCountries2 = adjCountries[1].split(",", 2);
							String[] adjCountries3 = adjCountries2[1].split(",", 2);
							System.out.println("###### The value of split one is ###### :" + territoryValues[0]);
							System.out.println(
									"###### The value of adj split adjCountries3[0] is ###### :" + adjCountries3[0]);
							System.out.println(
									"###### The value of adj split adjCountries3[1] is ###### :" + adjCountries3[1]);

							Country cc = new Country(territoryValues[0], adjCountries3[0]);
							adjCountry = new ArrayList<Country>();

							StringTokenizer st = new StringTokenizer(adjCountries3[1], ", ");

							

							while (st.hasMoreTokens()) {

								Country adjC = new Country(st.nextToken(","));

								adjCountry.add(adjC);

							}
							cc.setNeighbouringCountries(adjCountry);
							countryList.add(cc);
							countryAndNeighbors.put(cc, adjCountry);
							/*mapContentObject.setCountryAndNeighbors(countryAndNeighbors);*/
							
							System.out.println("The country List size is "+countryList.size());

						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void countryAndNeighboursMap()
	{
		try
		{
			System.out.println("######  countryAndNeighbors Map size     #############"+countryAndNeighbors.size());
			
			for (Map.Entry<Country, List<Country>> entry : countryAndNeighbors.entrySet())
			{
				
				System.out.println("##### Key is ##### :"+entry.getKey().getCountryName() + "value size is   "+entry.getValue().size() );
				
				
				
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	public void contitentAndCountriesMap()
	{
		try
		{
			System.out.println("##### Reading countryList ######");
			continentAndItsCountries = new HashMap<>();
			
			Collections.shuffle(contitentList);
			
			
			for(Continent continentInstance : contitentList)
			{
				System.out.println("Continent Name is "+continentInstance.getContinentName());
				Continent contientObj = new Continent(continentInstance.getContinentName());
				List<Country> counList = new ArrayList<Country>();
				
				for (Country countryInstance : countryList)
				{
					if(countryInstance.getBelongsToContinent().equals(continentInstance.getContinentName()))
					{
						counList.add(countryInstance);
					}
				}
				
				continentAndItsCountries.put(contientObj, counList);
				
			}
			
			for (Map.Entry<Continent, List<Country>> entry : continentAndItsCountries.entrySet())
			{
				System.out.println("##### Key is ##### :"+entry.getKey().getContinentName() + "value size is   "+entry.getValue().size() );
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public List<Country> getContinentCountries(String belongsToContinent) {
		try
		{
			List<Country> couList = new ArrayList<Country>(); 
			
			for (int i =0 ;i <countryList.size();i++ )
			{
				if (belongsToContinent.equals(countryList.get(i).getBelongsToContinent()))
				{
					
					System.out.println("###### Country name is ###########      :"+countryList.get(i).getCountryName());
					couList.add(countryList.get(i));
					
				}
				
			}
			
			return couList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
	
	
	
	/*public void displayContinentCountryMap()
	{
		try
		{
			for (Map.Entry<Continent, List<Country>> entry : continentAndItsCountries.entrySet())
			{
				System.out.println("##### Key is ##### :"+entry.getKey().getContinentName() + "value size is   "+entry.getValue().size() );
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}*/

/*	public void displayContinent() {
		try {

			for (Continent continentObejct : contitentList) {
				System.out.println(
						"###### The Continent Name is            ######### :" + continentObejct.getContinentName());
				System.out.println("###### The Continent Control value is ######### :"
						+ continentObejct.getContinentControlValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*public void displayCountry() {
		try {
			for (Country countryObject : countryList) {
				System.out.println(
						"###### The Country Name is            ######### :" + countryObject.getCountryName());
				System.out.println(
						"###### The Country belongs value is ######### :" + countryObject.getBelongsToContinent());
				System.out.println("###### The size of the adjacent country #######  :"
						+ countryObject.getNeighbouringCountries().size());
				
				  for (int i =0;i <countryObject.getNeighbouringCountries().size();i++ ) {
				  System.out.println("##### The neighbouring country of "+countryObject.
				  getCountryName() +"is #### :"
				  +countryObject.getNeighbouringCountries().get(i).getCountryName()); }
				 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public void playerNameAssignment()
	{
		readConfigurationObject = new ReadConfiguration();
		System.out.println("##### Number of Players are                     ###### :"+readConfigurationObject.getPlayerCount());
		playerList = new ArrayList();
		
		
		for (int i=0;i < numberOfPlayers ;i++ )
		{
			playerObject = new Player();
			playerObject.setName("Player"+"-"+i);
			
			playerList.add(playerObject);
			System.out.println("####### The player name is #########"+playerObject.getName());
			
		}
		randomPlayerAndCountries();
	}
	
	

	
	
	public void randomPlayerAndCountries()
	{
		List<Country> countryListClone = new ArrayList();
		countryListClone = countryList;
		List<Player> playerListClone = new ArrayList();
		playerListClone = playerList;
		int count = numberOfPlayers -1;
		int index;
		for (Country country : countryList)
		{
		
			System.out.println("The country name is :"+country.getCountryName());
			
			index = count;
			if(count ==0)
			{
				count = getRandomInteger(numberOfPlayers,1);
				
				
			}
			
			System.out.println("The index is "+index);
			Player playerInstance = playerListClone.get(index);
			
			
			List<Country> assignedCountryList = new ArrayList();
			if(playerInstance.getAssignedCountries()!=null)
			{
			assignedCountryList = playerInstance.getAssignedCountries();
			}
			assignedCountryList.add(country);
			playerInstance.setAssignedCountries(assignedCountryList);
			Collections.replaceAll(playerList,playerList.get(index),playerInstance);
		
			count = count - 1;
			
			
			
			
			
			
			
			
		}
		
		System.out.println("##### The players and their assgned countries are ####");
		
		//zero random assignment needs to be checked as well
		
		
		
		
		for (Player player : playerList)
		{
			System.out.println("The players name is "+player.getName());
			
			if(player.getAssignedCountries()!=null )
			{
			System.out.println("The player assigned list size is : "+player.getAssignedCountries().size());
			
			for(int i =0; i <player.getAssignedCountries().size();i++ )
			{
				System.out.println("Assigned country of "+player.getName() +" is "  +player.getAssignedCountries().get(i).getCountryName());
			}
		}
			

			/*gameDriverObject = new  GameDriver();
			gameDriverObject.gamePhase();*/

			//gameDriverObject = new  GameDriver();
			//gameDriverObject.gamePhase();
			
	}			
		
		
		
		
		
		
		
		
	}
	
	
	
	public  int getRandomInteger(int maximum, int minimum)
	{ 
		return ((int) (Math.random()*(maximum - minimum))) + minimum; 
	}

		
public int getRandomNumber() {
	
	int randomInt = new Random().nextInt((5-1) + 1);
	
	if(randomInt == 0)
	{
		getRandomNumber();
	}
	
	System.out.println("Random Int"+randomInt);
	return randomInt;
}
	
	public Country getRandomCountryList(List<Country> list) {

	    
	    int index = ThreadLocalRandom.current().nextInt(list.size());		
	    
	    return list.get(index);
	    
	}
	
	
public Player getRandomPlayerList(List<Player> list) {

	    
	    int index = ThreadLocalRandom.current().nextInt(list.size());		
	    
	    return list.get(index);
	    
	}

public int getRandomPlayerListIndex(List<Player> list) {

    
    int index = ThreadLocalRandom.current().nextInt(list.size());		
    
    return index;
    
}
	

}
