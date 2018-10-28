/**
 * 
 */
package com.concordia.riskGame.View;

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

	Map<String,String> Player =new HashMap<String, String>(); 
	int noOfCountries= MapContents.getInstance().getCountryAndNeighbors().keySet().size();
	StringBuilder sb1 = new StringBuilder(""); 	
	@Override
	public void update(Observable arg0, Object arg1) {
		Player player = (Player) arg0;

		//Map<Player, List<Country>> playerData=player.getPlayerAssign();
		for(Map.Entry<Player, List<Country>> playerData: player.getPlayerAssign().entrySet())
		{

			noOfCountries=noOfCountries+playerData.getValue().size();
			sb1.append("Player " +playerData.getKey() + "Percentage of map Controlled" +(playerData.getValue().size()/noOfCountries)*100);

		}
		System.out.println("In Domination View ");
		System.out.println("Phase View " + player.phasePrint);


	}

}
