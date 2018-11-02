package com.concordia.riskGame.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;
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
	List<Country> listCountry, listCountry1;
	MapContents mapContents;
	
	private String initiateValue;
    private String country1;
    private String country2;
    private String endValue;
    
    @Rule
    public final TextFromStandardInputStream systemInMock
      = TextFromStandardInputStream.emptyStandardInputStream();
	
	/**
	 * before method for initializing objects
	 */
	@Before
	public void before () {
		p1 = new Player("p1");
		p2 = new Player("p2");
		c1 = new Country("c1");
		c2 = new Country("c2");
		c3 = new Country("c3");
		c4 = new Country("c4");
		listCountry = new ArrayList<>();
		listCountry1 = new ArrayList<>();
		mapContents = MapContents.getInstance();
		
		p1.setTotalArmies(40);
		p2.setTotalArmies(40);
		c1.setArmies(10);
		c2.setArmies(10);
		c3.setArmies(10);
		c4.setArmies(10);
		
		listCountry.add(c2);
		listCountry.add(c3);
		c1.setNeighbouringCountries(listCountry);
		mapContents.getCountryAndNeighbors().put(c1, listCountry);
		
		listCountry = new ArrayList<>();
		
		listCountry.add(c3);
		listCountry.add(c4);
		c2.setNeighbouringCountries(listCountry);
		mapContents.getCountryAndNeighbors().put(c2, listCountry);
		
		listCountry = new ArrayList<>();
		
		listCountry.add(c4);
		listCountry.add(c1);
		c3.setNeighbouringCountries(listCountry);
		mapContents.getCountryAndNeighbors().put(c3, listCountry);
		
		listCountry = new ArrayList<>();
		
		listCountry.add(c1);
		listCountry.add(c2);
		c4.setNeighbouringCountries(listCountry);
		mapContents.getCountryAndNeighbors().put(c4, listCountry);
		
		listCountry = new ArrayList<>();
		
		listCountry.add(c1);
		listCountry.add(c2);
		p1.setAssignedCountries(listCountry);
						
		listCountry1.add(c3);
		listCountry1.add(c4);
		p2.setAssignedCountries(listCountry1);
		
	}
	
	@Test
	public void testFromAndToCountryCanNotBeTheSameNegative() {
			
		for(int i=0; i < p2.getAssignedCountries().size(); i++) {
			p2.getAssignedCountries().get(i).setArmies(10);
		}
		
		systemInMock.provideLines("yes","c1","c1","c2","1");
	    p1.forfeitPhase(p1);
		assertEquals("Armies have been moved between countries", p1.getErrorMesage());
		
	}
	
	@Test
	public void testCountriesAreNotNeighbourNegative() {

		for(int i=0; i < p2.getAssignedCountries().size(); i++) {
			p2.getAssignedCountries().get(i).setArmies(10);
		}		
		
		systemInMock.provideLines("yes","c3","c2","c4","1");
	    p2.forfeitPhase(p2);
		assertEquals("Armies have been moved between countries", p2.getErrorMesage());
		
	}
	
	@Test
	public void testMoveOnlyLeftOneArmyNegative() {

		for(int i=0; i < p2.getAssignedCountries().size(); i++) {
			p2.getAssignedCountries().get(i).setArmies(1);
		}
		
	    systemInMock.provideLines("yes","c3","1","no");
	    p2.forfeitPhase(p2);
		assertEquals("You cannot move the only army from this Country", p2.getErrorMesage());
		
	}
	
}
