package com.concordia.riskGame.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test Suite class to run all the test case files
 * @author Dhaval
 *
 */
@RunWith(Suite.class)
@SuiteClasses({CreateMapFileTest.class,MapParserTest.class, MapValidatorTest.class,RandomAssignmentTest.class})
public class TestSuite {

}
