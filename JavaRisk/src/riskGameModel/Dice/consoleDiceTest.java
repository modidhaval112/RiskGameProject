package riskGameModel.Dice;
import java.util.Scanner;


public class consoleDiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		Dice diceBox = new Dice();
		System.out.println("enter the number of dice to use");
		Scanner s = new Scanner(System.in);
		
		i= s.nextInt();
	//	diceBox = Dice.rollDice(i);
		
		
		
		for (int j=0;j<i;j++)
		System.out.println(diceBox.getDiceRollResult());
		
		
	}

}
