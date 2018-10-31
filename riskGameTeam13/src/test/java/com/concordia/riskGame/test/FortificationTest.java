package com.concordia.riskGame.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.concordia.riskGame.control.GameDriver;


/**
 * This class implements the test cases for testing the computation of
 * reinforcement armies
 * @author Dheeraj As
 *
 */
public class FortificationTest {
	int noOfCountries1;
	int noOfCountries2;
	int noOfCountries3;
	int result;
	@Before
	public void setUp () {
		noOfCountries1= 10;
		noOfCountries2= 2;
		noOfCountries3= 21;
		
		result=0;
	}
	
	/**
	 * This method test if the calculation of armies is correct when number of 
	 * countries is greater than 3
	 */
	@Test
	public void reinforcementArmyCalculationTest1() {
		
		GameDriver gameDriverObject= new GameDriver();
		
		result= gameDriverObject.calculateReiforcementArmies(noOfCountries1);
		assertEquals(3,result);
		
		result= gameDriverObject.calculateReiforcementArmies(noOfCountries3);
		assertEquals(7,result);
		
	}
	
	/**
	 * This method tests if the This method test if the calculation of armies is correct when number of 
	 * countries is less than 3
	 */
	@Test
	public void reinforcementArmyCalculationTest2() {
		GameDriver gameDriverObject= new GameDriver();
		
		result= gameDriverObject.calculateReiforcementArmies(noOfCountries2);
		assertEquals(3,result);
	}
	
	
}
