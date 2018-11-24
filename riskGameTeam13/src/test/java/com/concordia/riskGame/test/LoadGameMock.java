package com.concordia.riskGame.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.concordia.riskGame.control.GameDriver;
import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.Map.MapParseProcessor;

public class LoadGameMock {

	public MapContents readSavedMapContent(String filePath)
	{
		
		MapContents mapContentObject = null;

		try {
			System.out.println("##### Calling readSavedMapContent() #########");
			System.out.println("##### file path is  ######### : "+ filePath );
			FileInputStream saveFile = new FileInputStream(filePath);
			ObjectInputStream restore = new ObjectInputStream(saveFile);
			Object obj = restore.readObject();
			System.out.println("####### Saved file object is ####### : "+obj.toString());
			
			mapContentObject = (MapContents)obj;
			System.out.println("####### mapContentObject file object is ####### : "+mapContentObject.toString());
			
			System.out.println("##########  Loaded Saved game File #######");
			System.out.println("##########  Map Content Number of Player       ####### : "+mapContentObject.getPlayerList().size());
			System.out.println("##########  Map Content Number of Countries  ####### : "+mapContentObject.getCountryList().size());
	
			int rotateValue = mapContentObject.getRotateCount();
			System.out.println("######### The roate count value is ######## : "+rotateValue);
			List<com.concordia.riskGame.model.Player.Player> playerListLoadGame = new ArrayList();
			playerListLoadGame = mapContentObject.getPlayerList();
			Collections.rotate(playerListLoadGame, playerListLoadGame.size() - rotateValue);
			mapContentObject.setPlayerList(playerListLoadGame);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mapContentObject;
	}

}