package com.concordia.riskGame.View;

import java.util.Scanner;

import com.concordia.riskGame.View.PlayerCount;
import com.concordia.riskGame.util.ReadConfiguration;

public class GameDriver {

	private PlayerCount playerCountOBject;
	private ReadConfiguration readConfigObject;
	
	
	public void gamePhase()
	{
		System.out.println("####### Concordia Conquest Begins ####### :");
		
		readConfigObject = new ReadConfiguration();
		
		System.out.println("####### The number of players is ########## :"+readConfigObject.getPlayerCount());
		
		for (int i=0;i < Integer.parseInt(readConfigObject.getPlayerCount()) ;i++ )
		{
			
			reinforcePhase(i);
			attackPhase(i);
			forfeitPhase(i);
			
		}
		
	}
	
	private  void forfeitPhase(int i) {
		
		System.out.println("Player "+i  +" forfeits");
		
	}

	private  void attackPhase(int i) {
		
		
		
		System.out.println("Do you wish to attack : yes/no");
		String choice = null;
		Scanner sc =  new Scanner(System.in);
		choice = sc.nextLine();
		
		if (choice.equalsIgnoreCase("yes"))
		{
			System.out.println("Player "+i  +" attacks");
			attackPhase(i);
		}
		else
		{
			System.out.println("Invalid Option");
		}
	}

	private  void reinforcePhase(int i) {
		
		System.out.println("Player "+i  +" reinforces");
		
	}

	
}
