 package com.concordia.riskGame.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfiguration   
{

	private InputStream inputStream;
	private String playerCount;

	
	
	public ReadConfiguration()  
	{
		
		

		try 
		{
			Properties prop = new Properties();
			String propFileName = "application.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null)
			{
				prop.load(inputStream);
			} else 
			{
				throw new FileNotFoundException("Property File :'" + propFileName + "' not found in the classpath");
			}

			
			playerCount = prop.getProperty("playerCount");
			setPlayerCount(playerCount);
			
			
		} catch (Exception e) 
		{
			System.out.println("Exception: " + e);
		}
		finally 
		{
			try 
			{
				inputStream.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}

	}



	private void setPlayerCount(int playerCount2) 
	{
		// TODO Auto-generated method stub
		
	}



	public InputStream getInputStream() 
	{
		return inputStream;
	}



	public void setInputStream(InputStream inputStream) 
	{
		this.inputStream = inputStream;
	}



	public String getPlayerCount() 
	{
		return playerCount;
	}



	public void setPlayerCount(String playerCount) 
	{
		this.playerCount = playerCount;
	}
	
	
	
	

}
