package com.concordia.riskGame.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Player.Player;


/**
 * This class implements the test cases for testing the computation of
 * fortification phase
 * @author D_Modi
 *
 */
public class FortificationTest {

	Player p1, p2;
	Country c1,c2,c3,c4;
	List<Country> listCountry;
	
	/**
	 * before method for initializing objects
	 */
	@Before
	public void before () {
		p1 = new Player();
		p2 = new Player();
		c1 = new Country();
		c2 = new Country();
		c3 = new Country();
		c4 = new Country();
		listCountry = new ArrayList<>();
		
		listCountry.add(c2);
		listCountry.add(c3);
		c1.setNeighbouringCountries(listCountry);
		
		listCountry.clear();
		
		listCountry.add(c3);
		listCountry.add(c4);
		c2.setNeighbouringCountries(listCountry);
		
		listCountry.clear();
		
		listCountry.add(c4);
		listCountry.add(c1);
		c3.setNeighbouringCountries(listCountry);
		
		listCountry.clear();
		
		listCountry.add(c1);
		listCountry.add(c2);
		c4.setNeighbouringCountries(listCountry);
		
		listCountry.clear();
		
		listCountry.add(c1);
		listCountry.add(c2);
		p1.setAssignedCountries(listCountry);
		
		listCountry.clear();
		
		listCountry.add(c3);
		listCountry.add(c4);
		p2.setAssignedCountries(listCountry);
	}
	
}
