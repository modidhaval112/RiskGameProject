package com.concordia.riskGame.model.Card;

import java.util.List;
import java.util.Random;

public class Card {

	public  List<Card> cardList;
	public static final int max = 3;
	public static final int min = 1;
	public static final String firstCard = "INFANTRY";
	public static final String secondCard = "CAVALRY";
	public static final String thirdCard = "ARTILERRY";
	public String cardName;
	
	public static Card getFirstCard() {
		Card card = new Card();
		card.setCardName(firstCard); 
		return card;
	}

	public static Card getSecondCard() {
		Card card = new Card();
		card.setCardName(secondCard); 
		return card;
	}
	
	public static Card getThirdCard() {
		Card card = new Card();
		card.setCardName(thirdCard); 
		return card;
	}
	
	public Card getCarrdInfo(Card card) {
		Random random = new Random();
		int result = random.nextInt(max - min + 1) + min;
		if(result==1) {
			card.setCardName(firstCard);
		}
		else if(result==2) {
			card.setCardName(secondCard);
		}
		else {
			card.setCardName(thirdCard);
		}
		
		return card;
	}
	
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	@Override
    public boolean equals(Object o) { 
  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Card)) { 
            return false; 
        } 
          
        // typecast o to Card so that we can compare data members  
        Card c = (Card) o; 
          
        // Compare the data members and return accordingly  
        return cardName.equals(c.getCardName()); 
    } 
} 

