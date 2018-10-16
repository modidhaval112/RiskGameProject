package com.concordia.riskGame.entity;

import java.util.Random;

/**
 * This Class implements the Dice rolling functionality.
 * 
 * @author Dheeraj As
 */
public class Dice {

	private int numberOfDice;
	private int[] resultOfDice = new int[3];
	private static int diceBound = 7;

	/**
	 * This Method implements the random dice rolling
	 * 
	 * @param numberOfDice
	 * @return diceBox Dice Object
	 */
	public static Dice rollDice(int numberOfDice) {

		Dice diceBox = new Dice();
		for (int i = 0; i < numberOfDice; i++) {
			Random rollRandom = new Random();
			diceBox.resultOfDice[i] = rollRandom.nextInt(diceBound - 1) + 1; // (diceBound-1)+1 is to exclude zero and
																				// also to make 6 inclusive
		}
		return diceBox;

	}

	/**
	 * This method sets number of Dice
	 * @param numberOfDice
	 */
	public void setNumberOfDice(int numberOfDice) {
		this.numberOfDice = numberOfDice;
	}

	/**
	 * This method returns number of dice.
	 * @return noberOfDice
	 */
	public int getNumberOfDice() {
		return numberOfDice;
	}

	/**
	 * This method returns integer array pf resulted dice.
	 * @return resultOfDice Result Dice Array
	 */
	public int[] getDiceRollResult() {
		return resultOfDice;
	}

}
