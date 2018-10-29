package com.concordia.riskGame.View;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.concordia.riskGame.model.Card.Card;
import com.concordia.riskGame.model.Player.Player;


public class CardView implements Observer{


	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
       Player player = (Player) arg0;
       if(player != null && Player.reinforcePhase.equals(player.getCurrentPhase())) {
       
		System.out.println("**************In Card View*************** ");
		List<Card> cardList = player.getCardList();
		System.out.println("Cards after exchanging the cards");

		for(Card card : cardList) {
			System.out.println(card.getCardName());
		}

	}

	}

}
