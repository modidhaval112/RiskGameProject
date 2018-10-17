package com.concordia.riskGame.util;

import java.util.ArrayList;
import java.util.List;

import com.concordia.riskGame.entity.Country;
import com.concordia.riskGame.entity.Player;

public class IntialArmyAssignment {
	
	private List<Player> playerListClone;
	
	public List<Player> armyAssignment(List<Player> listPlayer)
	{
	
		playerListClone  = new ArrayList();
		
		int assignedArmies;
		
		
		for (Player player : listPlayer )
		{
			System.out.println("######### The name of the player is ######### :"+player.getName());
			System.out.println("    ");
			System.out.println("######### The assigned armies are  ######### :"+player.getTotalArmies());
			Player playerObject = new Player();
			playerObject = player;
			assignedArmies = playerObject.getTotalArmies();
			
			for (Country countryObject : playerObject.getAssignedCountries())
			{
				countryObject.setArmies(1);
				assignedArmies = assignedArmies - 1;
			}
			playerObject.setTotalArmies(assignedArmies);
			playerListClone.add(playerObject);
		}
				
		
		
		
		return playerListClone;
	}

	
	
	public void displayPlayerCountries(List<Player>  gmPlayerList)
	{
		
		for(Player player : gmPlayerList)
		{
			System.out.println("##### The player name is         ###### "+player.getName());
			System.out.println("##### The assigned armies are ##### "+player.getTotalArmies());
			
			for(Country countryObject : player.getAssignedCountries() )
			{
				System.out.println("			###### The assigned country name is ###### 		:"+countryObject.getCountryName());
				System.out.println("			###### The assigned army count  is 	 ######	    :"+countryObject.getArmies());
			}
		}
	
		
	}
	
	
}
