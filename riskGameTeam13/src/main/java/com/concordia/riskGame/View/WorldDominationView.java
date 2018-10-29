/**
 * 
 */
package com.concordia.riskGame.View;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import com.concordia.riskGame.model.Country.Country;
import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.Player.Player;

/**
 * @author saich
 *
 */
public class WorldDominationView implements Observer {

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	float noOfCountries= MapContents.getInstance().getCountryAndNeighbors().keySet().size();
	StringBuilder sb1 = new StringBuilder(""); 
	DecimalFormat df = new DecimalFormat();
	@Override
	public void update(Observable arg0, Object arg1) {
		Player player = (Player) arg0;
		System.out.println("###### World  domination view ######");
		List<Player> playerList=MapContents.getInstance().getPlayerList();
		DecimalFormat df = new DecimalFormat("#.##");

		for (int i = 0; i < playerList.size(); i++) {
			String percentage = df.format(((playerList.get(i).getAssignedCountries().size())*100)/noOfCountries);
			List<Country> countryList= playerList.get(i).getAssignedCountries();
			int countryArmies=0;
			for (Country country : countryList) {
				countryArmies=countryArmies+country.getArmies();

			}
			System.out.println(playerList.get(i).getName() +" Percentage of Map Contolled-"+percentage+" Total army player has "+countryArmies);
			//Map<Player, List<Country>> playerData=player.getPlayerAssign();


		}

		/*Map<Player, List<Country>> playerData=player.getPlayerAssign();
		for(Map.Entry<Player, List<Country>> playerData: player1.getPlayerAssign().entrySet())
		{

			noOfCountries=noOfCountries+playerData.getValue().size();
			System.out.println(noOfCountries);
			sb1.append("Player " +playerData.getKey() + "Percentage of map Controlled" +(playerData.getValue().size()/noOfCountries)*100);

		}
		System.out.println("In Domination View ");
		System.out.println("Phase View " + player1.phasePrint);
		 */
		System.out.println("##############################");
	}

}
