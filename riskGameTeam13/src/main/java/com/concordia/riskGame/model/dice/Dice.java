package com.concordia.riskGame.model.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {
	int numberOfDice;
	List<Integer> results = new ArrayList<>();
	public static final int max = 6;
	public static final int min = 1;
	
	
	
	public List<Integer> rollDice(int numberOfDice){
		this.numberOfDice = numberOfDice;
		while(numberOfDice>0) {
			Random random = new Random();
			int result = random.nextInt(max - min + 1) + min;
			results.add(result);
			numberOfDice--;
		}
		
		return results;
	}
}
