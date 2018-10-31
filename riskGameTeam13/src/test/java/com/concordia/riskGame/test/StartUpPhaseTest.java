package com.concordia.riskGame.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.concordia.riskGame.model.Map.MapParseProcessor;


public class StartUpPhaseTest {
	
	int noOfPlayers2;
	int noOfPlayers3;
	int	noOfPlayers4;
	int	noOfPlayers5;
	int	noOfPlayers6;
	int armiesGot;
	@Before
	public void setUp() {
		noOfPlayers2= 2;
		noOfPlayers3= 3;
		noOfPlayers4= 4;
		noOfPlayers5= 5;
		noOfPlayers6= 6;
		
	}
	@Test
	public void validInitialArmyAssignmentTest() {
		
		MapParseProcessor processorObj= new MapParseProcessor();
		armiesGot=processorObj.initialArmyAssignment(noOfPlayers2);
		assertEquals(40,armiesGot);
		
		armiesGot=processorObj.initialArmyAssignment(noOfPlayers3);
		assertEquals(35,armiesGot);
		
		armiesGot=processorObj.initialArmyAssignment(noOfPlayers4);
		assertEquals(30,armiesGot);
		
		armiesGot=processorObj.initialArmyAssignment(noOfPlayers5);
		assertEquals(25,armiesGot);
		
		armiesGot=processorObj.initialArmyAssignment(noOfPlayers6);
		assertEquals(20,armiesGot);
		
				
		
	}

}
