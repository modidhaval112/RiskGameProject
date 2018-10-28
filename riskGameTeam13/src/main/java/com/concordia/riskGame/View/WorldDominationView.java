/**
 * 
 */
package com.concordia.riskGame.View;

import java.util.Observable;
import java.util.Observer;

import com.concordia.riskGame.model.Player.Player;

/**
 * @author saich
 *
 */
public class WorldDominationView implements Observer {

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
	       Player player = (Player) arg0;
	       System.out.println("In Domination View ");
			System.out.println("Phase View " + player.phasePrint);

		
	}

}
