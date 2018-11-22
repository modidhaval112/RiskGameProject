package com.concordia.riskGame.model.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.concordia.riskGame.model.Map.MapContents;
import com.concordia.riskGame.model.Player.Player;

public class RiskSaveGame {

	private Date date;
	
	public void saveGame(MapContents mapContObj)
	{
		
		try {
		System.out.println("########## SAVE GAME  ##########");
		date = new Date();
		
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHH:mm:ss");
		String dateString = sdf.format(date);
		
		dateString = dateString.replaceAll(":", "");
		System.out.println("########## Date String is  ########## : "+dateString );
		String filePath =  "C:\\SaveGame\\"+dateString;
		File file = new File(filePath);
		
		file.setReadable(true);
		file.setWritable(true);
		file.setExecutable(true);
		
		System.out.println("##### The rotate value inside save game file is ###### : "+mapContObj.getRotateCount());
		
		
		
		FileOutputStream fileOutStream = new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(fileOutStream); 
        out.writeObject(mapContObj); 
        out.close(); 
        fileOutStream.close(); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
