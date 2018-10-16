package com.concordia.riskGame.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * this class reads Input put stream
 * @author Dhaval
 *
 */
public class ReadConfiguration {

	private InputStream inputStream;
	private String playerCount;

	/**
	 * this method is to read configurations file
	 */
	public ReadConfiguration() {
		try {
			Properties prop = new Properties();
			String propFileName = "application.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property File :'" + propFileName + "' not found in the classpath");
			}

			playerCount = prop.getProperty("playerCount");
			setPlayerCount(playerCount);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * This method returns Input Stream
	 * @return inputStream Input Stream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * This method sets Input Stream
	 * @param inputStream Input Stream
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * This method returns number of players
	 * @return playCount number of players
	 */
	public String getPlayerCount() {
		return playerCount;
	}

	/**
	 * This method sets number of Player
	 * @param playerCount number of Players
	 */
	public void setPlayerCount(String playerCount) {
		this.playerCount = playerCount;
	}

}
