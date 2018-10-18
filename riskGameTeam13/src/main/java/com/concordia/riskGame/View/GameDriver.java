package com.concordia.riskGame.View;

import java.util.Scanner;

import com.concordia.riskGame.View.PlayerCount;
import com.concordia.riskGame.util.IntialArmyAssignment;
import com.concordia.riskGame.util.ReadConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.concordia.riskGame.entity.Country;
import com.concordia.riskGame.entity.Player;

import com.concordia.riskGame.View.PlayerCount;
import com.concordia.riskGame.util.ReadConfiguration;

/**
 * This class starts the game phases
 * 
 * @author Sande
 *
 */
public class GameDriver {

	private PlayerCount playerCountOBject;
	private ReadConfiguration readConfigObject;
	private Scanner scanner;
	private String[] nameArmiesSpilt;
	private String countryName;
	private String armiesCount;
	private IntialArmyAssignment intialArmyAssignObject;
	private List<Player> gmPlayerList;
	private HashMap<Country, List<Country>>  gmcountryAndNeighbours;
	private List<Player> updatedPlayerList = new ArrayList();
	private int assignedArmies;
	
	
	public void gamePhase(List<Player> player,HashMap<Country, List<Country>>  countryAndConnected) {

		readConfigObject = new ReadConfiguration();
		List<Player> playerList = new ArrayList();
		gmcountryAndNeighbours = new HashMap();
		gmcountryAndNeighbours = countryAndConnected;
		intialArmyAssignObject = new IntialArmyAssignment();
		
		playerList = intialArmyAssignObject.armyAssignment(player);

		
		
/*		Player p = player.get(0);
		reinforcePhase(p);
		forfeitPhase();
*/		

		for (Player p : player ) {
	
		Player playerInstance = new Player();
		
		playerInstance=reinforcePhase(p);
			
		/*playerInstance=attackPhase(playerInstance);*/
		
		
		playerInstance = forfeitPhase(playerInstance);
		updatedPlayerList.add(playerInstance);
		}
		
	
	}
	
	
	
	private Player forfeitPhase(Player playerObject) {

		Player player = new Player();
		player = playerObject;
		System.out.println(player.getName() +"is in fortify phase " );
		System.out.println("##### Forfeitication begins ######");
		String[] utilString;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("#### List of countries owned by the player #####");
		
		for (Country  countryObj : player.getAssignedCountries() )
		{
			
			System.out.print( countryObj.getCountryName() +",");
			
		}
		System.out.println("Enter source country and destination country");
		utilString = sc.nextLine().split(",");
		String fromCountry = utilString[0];
		String toCountry = utilString[1];
		
		if(fromCountry.equals(toCountry)) {
			System.out.println("xxxxxxx----From and to country cannot be the same----xxxxxx");
			forfeitPhase(player);
			
		}
		
		System.out.println("Enter the number of armies to be moved");
		int movingArmies= sc.nextInt();
		
		System.out.println("###########    Source country      	 ############### :"+fromCountry);
		System.out.println("###########  Destination Country   	 ############### :"+toCountry);
		System.out.println("###########   Armies to be moved    ############### :"+movingArmies);
		
		List<Country> connectedCountries = new ArrayList(); 
			
		System.out.println("#### Displaying country and its neighbouring countries #####");
		
		
		
/*		for(Map.Entry<Country, List<Country>> entry : gmcountryAndNeighbours.entrySet())
		{
			
			if(entry.getKey().getCountryName().equals(fromCountry))
				
			{
				System.out.println("#### Source country is matched #######");
				System.out.println("#### Matched Country name is ######## :"+entry.getKey().getCountryName());
				connectedCountries = entry.getValue();
				
			
				
			}
			
			
			
		}
*/		
		
			System.out.println("Displaying player armies count before forfeit");
		
		for(Country country : player.getAssignedCountries() )
		{
			
			
			
			System.out.println("######## The country name is ###### "+country.getCountryName());
			System.out.println("######## The country armies is ###### "+country.getArmies());
		}
		
		int destArmies = 0;
		int sourcesArmies = 0;
		
		List<Country> assignedCountriesClone = new ArrayList();
		List<Country> assignedCountriesClone2 = new ArrayList();
		
		if(isNeighbour(fromCountry,toCountry))
		{
		for(Country countryInstance : player.getAssignedCountries())
		{
			if(countryInstance.getCountryName().equalsIgnoreCase(fromCountry))
			{
				Country sourceCountry = new Country();
				sourceCountry = countryInstance;
				sourcesArmies = sourceCountry.getArmies();
				sourcesArmies = sourcesArmies - movingArmies;
				sourceCountry.setArmies(sourcesArmies);
				
			}
			assignedCountriesClone.add(countryInstance);
			
			
		}
		
		for(Country countryInstance : player.getAssignedCountries())
		{
			if(countryInstance.getCountryName().equalsIgnoreCase(toCountry))
			{
				Country destCountry = new Country();
				destCountry = countryInstance;
				destArmies = destCountry.getArmies();
				destArmies = destArmies + movingArmies;
				destCountry.setArmies(destArmies);
			}
			
			assignedCountriesClone2.add(countryInstance);
			
		}
		
		
	
		for (Country  x : assignedCountriesClone2){
			   if (!assignedCountriesClone.contains(x))
				   assignedCountriesClone.add(x);
			}
		
		
		System.out.println("Displaying player armies count after forfeit");
		
		for(Country country : assignedCountriesClone )
		{
			System.out.println("######## The country name is ###### "+country.getCountryName());
			System.out.println("######## The country armies is ###### "+country.getArmies());
		}
		
		
		
		player.setAssignedCountries(connectedCountries);
		}
		else
		{
			System.out.println("##### IsNeighbour returned false ######");
		}
		
		System.out.println("##### End of forfeit ###### ");;
		
/*		if(connectedCountriesString.contains("toCountry"))
		{
			System.out.println("#### The country is one of the connected countries ######");
			
			
			
		}
		else
		{
			System.out.println("###### Both the countries are not connected #######");
		}
*/		
		
		/*for(Map.Entry<Country, List<Country>> entry : gmcountryAndNeighbours.entrySet())
		{
			
			System.out.println("##### The country name is                     ##### "+entry.getKey().getCountryName());
			System.out.println("##### The  neighbouring countries are  #####  ");
			
			for (Country countryInstance : entry.getValue())
			{
				System.out.print(countryInstance.getCountryName() +" ,");;
			}
			
			
			
		}*/
		
		/*System.out.println("#### The list of owned countries is : #####");
		
		for (Country  countryObj : player.getAssignedCountries() )
		{
			
			System.out.print( "");
			
		}*/
		
		return player;
		
	}

	private Player attackPhase(Player player) {

		
		System.out.println("Do you wish to attack : yes/no");
		Player pObject = new Player();
		pObject = player;
		
		String choice = null;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextLine();

		if (choice.equalsIgnoreCase("yes")) {
			System.out.println("Player attacks");
			attackPhase(player);
		} 
		else if (choice.equalsIgnoreCase("no")) {
			System.out.println("Player enter into forfeits phase");
			
		}
		else {
			System.out.println("Invalid Option");
		}
		
		return pObject;
	}

	private Player reinforcePhase(Player player) {

		scanner = new Scanner(System.in);
		gmPlayerList = new ArrayList();
		int additionalArmies;
		System.out.println("######## Player  reinforces ########");
		System.out.println("#### The player name is #####" + player.getName());
		System.out.println("#### The total number of armies are #### " + player.getTotalArmies());

		System.out.println("##### Adding armies based on countries owned ######");
		
		assignedArmies=  player.getAssignedCountries().size()/3;
		if(assignedArmies<3) {
			assignedArmies=3;
		}
		additionalArmies = player.getTotalArmies() + assignedArmies;
		
		player.setTotalArmies(additionalArmies);

		System.out.println("#### The total number of armies after adding additional armies is  #### " + player.getTotalArmies());
		
		System.out.println("### Countries Owned #####");
		for (Country country : player.getAssignedCountries()) {
			System.out.print(country.getCountryName() + " ,");
		}

		int counter = player.getTotalArmies();
		int armiesCounter;
		while (counter > 0) {

			System.out.println("Select the country name and armies(comma , seperated ) in which you want assign armies");

			System.out.println("##### The totoal number of armies are ##### " + counter);

			nameArmiesSpilt = scanner.nextLine().split(",");
			countryName = nameArmiesSpilt[0];
			armiesCount = nameArmiesSpilt[1];

			System.out.println("The selected country name is" + countryName);

			armiesCounter = Integer.parseInt(armiesCount);
			System.out.println("The give reinforcement armies are " + armiesCounter);
			System.out.println("The value if counter is  " + counter);
			int armyCount =0;
			
			if (armiesCounter <= counter) {

				for (Country country : player.getAssignedCountries()) {
					if (countryName.equalsIgnoreCase(country.getCountryName())) {
						System.out.println("Country Matched");
						Country c = new Country();
						c = country;
						System.out.println(" Country get armies is         ###### :"+c.getArmies());
						System.out.println(" Country armies counter is  ###### :"+armiesCounter);
						
						armyCount = country.getArmies() + armiesCounter;
						c.setArmies(armyCount);
						System.out.println(" armyCount is  ###### :"+armyCount);
						Collections.replaceAll(player.getAssignedCountries(), country, c);
						player.setTotalArmies(armyCount);
					} 
				}
				
				System.out.println("The counter value is "+counter);
				System.out.println("the armiesCounter value is "+armiesCounter);
				counter = counter - armiesCounter;
				System.out.println("The total value after subracting "+counter);

			} else {
				System.out.println("The entered army counter is greater than the reamaining armies");
			}
			
		}
		gmPlayerList.add(player);
		
		for(Player play : gmPlayerList)
		{
			System.out.println("##### The player name is         ###### "+play.getName());
			System.out.println("##### The assigned armies are ##### "+play.getTotalArmies());
			
			for(Country countryObject : play.getAssignedCountries() )
			{
				System.out.println("			###### The assigned country name is ###### 		:"+countryObject.getCountryName());
				System.out.println("			###### The assigned army count  is 	 ######	    :"+countryObject.getArmies());
			}
		}
		
		System.out.println("Repeat the process to reinforce  untill armies are zero");
		return player;
		
	}

	public  boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	
	
	public boolean isNeighbour(String sourceCountry,String destCountry)
	{
		System.out.println("##### Checking the country if it neoghbour of the country or not #####");;
		System.out.println("##### Source country is        : ##### :"+sourceCountry);
		System.out.println("##### Destination country is : ##### :"+destCountry);

		List<Country> connectedCountries = new ArrayList();
		boolean returnValue = false;
		
		for(Map.Entry<Country, List<Country>> entry : gmcountryAndNeighbours.entrySet())
		{
			
			System.out.println("##### The country name is                     ##### "+entry.getKey().getCountryName());
			if(entry.getKey().getCountryName().equalsIgnoreCase(sourceCountry))
				
			{
				connectedCountries = entry.getValue();
				
				for(Country countryInstance : connectedCountries )
				{
					
					if(countryInstance.getCountryName().equalsIgnoreCase(destCountry));
					System.out.println("#### Country is a neighbour ######");
					returnValue =  true;
					
				}
			}
			
			
			
			
			
			
		}
		return returnValue;
		
		
		
		
		
	}
	
	
}
