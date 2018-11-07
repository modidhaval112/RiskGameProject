package com.concordia.riskGame.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Map.Entry;

import com.concordia.riskGame.model.Card.Card;
import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Player.Player;


public class CardView implements Observer{

	
	
	public int exchangeCards(Player player) throws Exception {
		int armiesToBeGiven =0;
		Scanner scanner = new Scanner(System.in);
		HashMap<String, Integer> cardCount = new HashMap<>();
		int cardTypes = 0;
		player.setCardExchangeCount(0);
		player.setCardExchangeAppearingMoreThanThrice("");
		String cardExchangeChoice;
		boolean cardExchangePossible = false;
		String cardAppearingMoreThanThrice = null;
		System.out.println("The cards with this player are :");
		for (Card card : player.getCardList()) {
			if (!cardCount.containsKey(card.getType())) {
				cardCount.put(card.getType(), 1);
				cardTypes++;
			} else {
				int c = cardCount.get(card.getType());
				c++;
				cardCount.put(card.getType(), c);
			}
			System.out.print(card.getType() + ", ");
		}

		if (cardTypes == 3) {
			cardExchangePossible = true;
		}
		for (Entry<String, Integer> cardVal : cardCount.entrySet()) {
			if (cardVal.getValue() >= 3) {
				cardExchangePossible = true;
				cardAppearingMoreThanThrice = cardVal.getKey();
			}
		}

		if (player.getCardList().size() < 3) {
			System.out.println();
			System.out.println("Not enough cards to exchange ,continuing with the reinforcement phase");
		} else if (cardExchangePossible) {
			if (player.getCardList().size() < 5) {
				System.out.println("Do you want to exchange the cards to armies(yes/no");
				cardExchangeChoice = scanner.nextLine();
				if (cardExchangeChoice.equals("yes")) {
					int i=0;
					for (Card card : player.getCardList()) {
						System.out.println(card.getName() + " : "+ (++i));
					}
					System.out.println("Enter the numbers of card you want to exchange in comma seperated values");
					String[] cardsList = scanner.nextLine().split(",");
					List<Card> exchangeCards = new ArrayList<>();
					List<Integer> cardNumbers = new ArrayList<>();
					for(String card  : cardsList) {
						cardNumbers.add(Integer.parseInt(card));
					}
					if(cardNumbers.size()<3) {
						System.out.println("Please enter at least 3 numbers for exchanging cards.");
						throw new Exception();
					}
					for(int c : cardNumbers) {
						exchangeCards.add(player.getCardList().get(c-1));
					}
					if(!player.checkCardDifferentTypes(exchangeCards,cardTypes) && !player.checkCardSameType(exchangeCards,cardAppearingMoreThanThrice)) {
						System.out.println("Please enter numbers of same cards appearing thrice or three cards which are different.");
						throw new Exception();
					}
					if(player.getCardExchangeTypeCount()<3 && (player.getCardExchangeAppearingMoreThanThrice()==null && player.getCardExchangeAppearingMoreThanThrice().isEmpty())){
						System.out.println("Please enter numbers of same cards appearing thrice or three cards which are different.");
						throw new Exception();
					}
					player.exchangeCards(player.getCardExchangeTypeCount(), player.getCardExchangeAppearingMoreThanThrice(), player,cardNumbers);
					int count = player.getCardExchangeCount();
					armiesToBeGiven = (count + 1) * 5;
					
					
					
					
					player = player.exChangeCardTerritoryExist(exchangeCards,player);
					
					System.out.println("####### Country and Armies after exchange card territory reinforcements ######## ");
					
					for(Country c : player.getAssignedCountries())
					{
						System.out.println("######## Army Assignment after card exchange ###### ");
						System.out.println("######## Country Name  ########## :"+c.getCountryName());
						System.out.println("######## Country Armies #########   :"+c.getArmies());
						
					}
					
					
					System.out.println("Player recieves " + armiesToBeGiven + " armies for exchanging the cards");
					player.setCardExchangeCount(player.getCardExchangeCount() + 1);
				} else {
					System.out.println("Not Exchanging the cards to armies");
				}
			} else {
				int i=0;
				for (Card card : player.getCardList()) {
					System.out.println(card.getName() + " : "+ (++i));
				}
				System.out.println("Enter the numbers of card you want to exchange in comma seperated values");
				String[] cardsList = scanner.nextLine().split(",");
				List<Card> exchangeCards = new ArrayList<>();
				List<Integer> cardNumbers = new ArrayList<>();
				for(String card  : cardsList) {
					cardNumbers.add(Integer.parseInt(card));
				}
				if(cardNumbers.size()<3) {
					System.out.println("Please enter at least 3 numbers for exchanging cards.");
					throw new Exception();
				}
				for(int c : cardNumbers) {
					exchangeCards.add(player.getCardList().get(c-1));
				}
				if(!player.checkCardDifferentTypes(exchangeCards,cardTypes) && !player.checkCardSameType(exchangeCards,cardAppearingMoreThanThrice)) {
					System.out.println("Please enter numbers of same cards appearing thrice or three cards which are different.");
					throw new Exception();
				}
				if(player.getCardExchangeTypeCount()<3 && (player.getCardExchangeAppearingMoreThanThrice()!=null && !player.getCardExchangeAppearingMoreThanThrice().isEmpty())){
					System.out.println("Please enter numbers of same cards appearing thrice or three cards which are different.");
					throw new Exception();
				}
				player.exchangeCards(player.getCardExchangeTypeCount(), player.getCardExchangeAppearingMoreThanThrice(), player,cardNumbers);
				int count = player.getCardExchangeCount();
				armiesToBeGiven = (count + 1) * 5;
				
				player = player.exChangeCardTerritoryExist(exchangeCards,player);
				
				System.out.println("####### Country and Armies after exchange card territory reinforcements ######## ");
				
				for(Country c : player.getAssignedCountries())
				{
					System.out.println("######## Army Assignment after card exchange ###### ");
					System.out.println("######## Country Name  ########## :"+c.getCountryName());
					System.out.println("######## Country Armies #########   :"+c.getArmies());
					
				}
				
				
				System.out.println("Player recieves " + armiesToBeGiven + " armies for exchanging the cards");
				player.setCardExchangeCount(player.getCardExchangeCount() + 1);
			}
		} else if (!cardExchangePossible) {
			System.out.println("Not enough cards to exchange , moving to reinforcement");
		}
		return armiesToBeGiven;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
       Player player = (Player) arg0;
       if(player != null && Player.reinforcePhase.equals(player.getCurrentPhase()) && !(player.getCardList()==null) && player.getExchanged()) {
       
		System.out.println("**************In Card View*************** ");
		List<Card> cardList = player.getCardList();
		System.out.println("Cards after exchanging the cards");

		for(Card card : cardList) {
			System.out.println(card.getName());
		}

	}

	}

}
