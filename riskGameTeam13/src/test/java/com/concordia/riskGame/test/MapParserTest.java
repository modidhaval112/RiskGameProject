package com.concordia.riskGame.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.concordia.riskGame.Controller.MapParseController;
/**
 * Test to check if reading the map is functioning properly
 * 
 */
public class MapParserTest {

	private String filePath;
	private MapParseController mapParserObject;
	@Before
	public void setUp() throws Exception {
	    filePath="src/main/resources/Africa.map";
		mapParserObject = new MapParseController();		
	}
	

	@Test
	public void mapParsertest() {
		mapParserObject.mapParser(filePath);
		int numberOfContinents= mapParserObject.getContinentList().size();
		
		assertEquals(7,numberOfContinents);
	}
	
	
		

}
